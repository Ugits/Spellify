package org.jonas.spellify.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.util.Strings;
import org.jonas.spellify.api.model.dto.SpellApiDTO;
import org.jonas.spellify.api.service.ApiSpellService;
import org.jonas.spellify.exception.SpellAlreadyExists;
import org.jonas.spellify.exception.SpellNotFoundException;
import org.jonas.spellify.exception.SpellUpdateException;
import org.jonas.spellify.model.dto.SpellDTO;
import org.jonas.spellify.model.dto.SpellDescriptionDTO;
import org.jonas.spellify.model.dto.UpdateSpellDTO;
import org.jonas.spellify.model.entity.CharClass;
import org.jonas.spellify.model.entity.Spell;
import org.jonas.spellify.model.entity.SpellDescription;
import org.jonas.spellify.repository.ClassRepository;
import org.jonas.spellify.repository.SpellDescriptionRepository;
import org.jonas.spellify.repository.SpellRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminSpellService {

    private final SpellRepository spellRepository;
    private final ClassRepository charClassRepository;
    private final SpellDescriptionRepository spellDescriptionRepository;
    private final ApiSpellService apiSpellService;

    public AdminSpellService(SpellRepository spellRepository, ClassRepository classRepository, ApiSpellService apiSpellService, SpellDescriptionRepository spellDescriptionRepository) {
        this.spellRepository = spellRepository;
        this.charClassRepository = classRepository;
        this.apiSpellService = apiSpellService;
        this.spellDescriptionRepository = spellDescriptionRepository;
    }

    public List<Spell> saveAllSpells(List<Spell> spells) {
        List<Spell> existingSpells = spells.stream()
                .filter(spell -> spellRepository.findByIndex(spell.getIndex()).isPresent())
                .toList();

        if (!existingSpells.isEmpty()) {
            throw new SpellAlreadyExists(
                    "Spells with the following indexes already exists: "
                            + existingSpells.stream()
                            .map(Spell::getIndex).collect(Collectors.joining(", ")));
        }
        return spellRepository.saveAll(spells);
    }

    public Spell saveSpell(Spell spell) {

        Optional<Spell> spellOptional = spellRepository.findByIndex(spell.getIndex());
        if (spellOptional.isPresent()) {
            throw new SpellAlreadyExists("The spell with index " + "[" + spell.getIndex() + "]" + " already exists");
        }
        return spellRepository.save(spell);
    }

    @Transactional
    public List<Spell> syncSpellsFromApi() throws IOException {
        List<SpellApiDTO> spellsFromApi = apiSpellService.fetchAllSpells().block();

        if (spellsFromApi == null || spellsFromApi.isEmpty()) {
            throw new SpellUpdateException("No spells returned from external API.");
        }

        saveSpellNamesToJsonFile(spellsFromApi);

        return spellsFromApi.stream()
                .map(this::convertSpellApiDtoToEntity)
                .map(newSpell -> spellRepository.findByIndex(newSpell.getIndex())
                        .map(existingSpell -> {
                            updateExistingSpell(existingSpell, newSpell);
                            return spellRepository.save(existingSpell);
                        })
                        .orElseGet(() -> spellRepository.save(newSpell))
                )
                .collect(Collectors.toList());
    }

    private void saveSpellNamesToJsonFile(List<SpellApiDTO> spellsFromApi) throws IOException {

        List<String> spellNames = spellsFromApi
                .stream()
                .map(SpellApiDTO::name)
                .toList();

        ObjectMapper objectMapper = new ObjectMapper();

        try {

            File jsonFile = new File("src/main/resources/spell_names.json");
            objectMapper.writeValue(jsonFile, spellNames);

            System.out.println("Spells successfully saved to spells.json");

        } catch (IOException e) {
            throw new RuntimeException("Failed to save spells to JSON file", e);
        }
    }

    private void updateExistingSpell(Spell existingSpell, Spell newSpell) {

        existingSpell.setName(newSpell.getName());
        existingSpell.setIndex(newSpell.getIndex());
        existingSpell.setCastingTime(newSpell.getCastingTime());
        existingSpell.setLevel(newSpell.getLevel());
        existingSpell.setRange(newSpell.getRange());
        existingSpell.setRitual(newSpell.isRitual());
        existingSpell.setDuration(newSpell.getDuration());
        existingSpell.setConcentration(newSpell.isConcentration());

        saveSpellDescriptions(existingSpell, newSpell.getDescription());
    }

    private void saveSpellDescriptions(Spell spell, List<SpellDescription> newDescriptions) {

        if (newDescriptions != null && !newDescriptions.isEmpty()) {

            spellDescriptionRepository.deleteBySpellId(spell.getId());

            newDescriptions.forEach(desc -> {
                desc.setSpell(spell);
                spell.addDescription(desc);
            });
            spellDescriptionRepository.saveAll(newDescriptions);
        }
    }

    private Spell convertSpellApiDtoToEntity(SpellApiDTO spellApiDTO) {
        // Create a new Spell entity
        Spell spell = new Spell();
        spell.setName(spellApiDTO.name());
        spell.setIndex(spellApiDTO.index());
        spell.setCastingTime(spellApiDTO.castingTime());
        spell.setLevel(spellApiDTO.level());
        spell.setRange(spellApiDTO.range());
        spell.setRitual(spellApiDTO.ritual());
        spell.setDuration(spellApiDTO.duration());
        spell.setConcentration(spellApiDTO.concentration());

        // Handle classes: convert CharClassApiDTO to CharClass and associate with Spell
        if (spellApiDTO.classes() != null && !spellApiDTO.classes().isEmpty()) {
            List<CharClass> classes = spellApiDTO.classes().stream()
                    .map(charClassDTO -> {
                        // Check if the CharClass already exists
                        Optional<CharClass> existingClass = charClassRepository.findByIndex(charClassDTO.index());
                        return existingClass.orElseGet(() -> new CharClass(charClassDTO.index(), charClassDTO.name()));
                    }) // Create CharClass instances
                    .peek(charClass -> charClass.addSpell(spell)) // Ensure bidirectional relationship
                    .distinct()
                    .toList();
            spell.setClasses(classes);
        }

        // Handle descriptions: convert SpellDescriptionApiDTO to SpellDescription and associate with Spell
        if (spellApiDTO.description() != null && !spellApiDTO.description().isEmpty()) {
            List<SpellDescription> descriptions = spellApiDTO.description().stream()
                    .map(desc -> new SpellDescription(desc.getDescription(), spell)) // Create SpellDescription instances
                    .toList();
            spell.setDescription(descriptions);
        }

        return spell;
    }

    public Spell updateSpell(String index, UpdateSpellDTO updateSpellDTO) {
        Optional<Spell> spell = spellRepository.findByIndex(index);
        if (spell.isEmpty()) {
            throw new SpellNotFoundException("No spell with index " + "[" + index + "]");
        }
        Spell updatedSpell = getUpdatedSpell(updateSpellDTO, spell.get());

        return spellRepository.save(updatedSpell);
    }

    private static Spell getUpdatedSpell(UpdateSpellDTO updateSpellDTO, Spell spell) {

        if (updateSpellDTO.name() != null) spell.setName(updateSpellDTO.name());
        if (updateSpellDTO.level() != null) spell.setLevel(updateSpellDTO.level());
        if (updateSpellDTO.castingTime() != null) spell.setCastingTime(updateSpellDTO.castingTime());
        if (updateSpellDTO.range() != null) spell.setRange(updateSpellDTO.range());
        if (updateSpellDTO.duration() != null) spell.setDuration(updateSpellDTO.duration());
        if (updateSpellDTO.ritual() != null) spell.setRitual(updateSpellDTO.ritual());
        if (updateSpellDTO.concentration() != null) spell.setConcentration(updateSpellDTO.concentration());

        if (updateSpellDTO.description() != null) {

            spell.getDescription().clear();

            for (SpellDescriptionDTO descDTO : updateSpellDTO.description()) {
                SpellDescription newDesc = new SpellDescription(descDTO.description(), spell);
                spell.getDescription().add(newDesc);
            }
        }
        return spell;
    }

    @Transactional
    public void truncateSpellsTable() {
        spellRepository.truncateTable();
    }

    public void deleteSpellById(Long id) {
        Optional<Spell> spell = spellRepository.findById(id);
        if (spell.isEmpty()) {
            throw new SpellNotFoundException("Spell not found with id: " + "[" + id + "]");
        }
        spellRepository.deleteById(id);
    }

    public Spell convertSpellDtoToEntity(SpellDTO spellDTO) {
        Spell spell = new Spell();
        spell.setName(spellDTO.name());
        spell.setIndex(spellDTO.index());
        spell.setCastingTime(spellDTO.castingTime());
        spell.setLevel(spellDTO.level());
        spell.setRange(spellDTO.range());
        spell.setRitual(spellDTO.ritual());
        spell.setDuration(spellDTO.duration());
        spell.setConcentration(spellDTO.concentration());

        // Handle classes: convert CharClassDTO to CharClass and associate with Spell
        if (spellDTO.classes() != null && !spellDTO.classes().isEmpty()) {
            List<CharClass> classes = spellDTO.classes().stream()
                    .map(charClassDTO -> {
                        // Check if the CharClass already exists
                        Optional<CharClass> existingClass = charClassRepository.findByIndex(charClassDTO.index());
                        return existingClass.orElseGet(() -> new CharClass(charClassDTO.index(), charClassDTO.name()));
                    })
                    .peek(charClass -> charClass.addSpell(spell)) // Ensure bidirectional relationship
                    .distinct() // Remove duplicates if using a list
                    .toList();
            spell.setClasses(classes);
        }

        if (spellDTO.description() != null) {
            spellDTO.description().forEach(descDTO -> {
                SpellDescription description = new SpellDescription(descDTO.description(), spell);
                spell.addDescription(description);
            });
        }
        return spell;
    }
}

