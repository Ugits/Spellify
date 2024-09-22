package org.jonas.spellify.service;

import org.jonas.spellify.api.model.SpellApi;
import org.jonas.spellify.api.model.dto.SpellApiDTO;
import org.jonas.spellify.api.service.ApiSpellService;
import org.jonas.spellify.exception.SpellNotFoundException;
import org.jonas.spellify.exception.SpellUpdateException;
import org.jonas.spellify.model.dto.SpellDTO;
import org.jonas.spellify.model.entity.Spell;
import org.jonas.spellify.model.entity.SpellDescription;
import org.jonas.spellify.repository.SpellRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminSpellService {

    private final SpellRepository spellRepository;
    private final ApiSpellService apiSpellService;

    public AdminSpellService(SpellRepository spellRepository, ApiSpellService apiSpellService) {
        this.spellRepository = spellRepository;
        this.apiSpellService = apiSpellService;
    }

    public List<Spell> saveAllSpells(List<Spell> spells) {
        return spellRepository.saveAll(spells);
    }

    public Spell saveSpell(Spell spell) {
        return spellRepository.save(spell);
    }

    @Transactional
    public List<Spell> syncSpellsFromApi() {
        List<SpellApiDTO> spellsFromApi = apiSpellService.fetchAllSpells().block();

        if (spellsFromApi == null || spellsFromApi.isEmpty()) {
            throw new SpellUpdateException("No spells returned from external API.");
        }

        return spellsFromApi.stream()
                .map(this::convertApiToEntity)
                .map(newSpell -> spellRepository.findByIndex(newSpell.getIndex())
                        .map(existingSpell -> {
                            BeanUtils.copyProperties(newSpell, existingSpell, "id");
                            return spellRepository.save(existingSpell);
                        })
                        .orElseGet(() -> spellRepository.save(newSpell)))
                .toList();
    }

    private Spell convertApiToEntity(SpellApiDTO spellApiDTO) {
        Spell spell = new Spell();
        BeanUtils.copyProperties(spellApiDTO, spell);

        // Clear previous descriptions and add new ones
        spell.setDescription(spellApiDTO.getDescription().stream()
                .map(descDTO -> new SpellDescription(descDTO.getDescription(), spell))
                .collect(Collectors.toList()));

        return spell;
    }

    public Spell updateSpell(Long id, SpellDTO spellDTO) {
        Spell spell = spellRepository.findById(id)
                .orElseThrow(() -> new SpellNotFoundException("Spell not found with id: " + id));

        if (spellDTO.index() != null) spell.setIndex(spellDTO.index());
        if (spellDTO.name() != null) spell.setName(spellDTO.name());
        if (spellDTO.level() != null) spell.setLevel(spellDTO.level());
        if (spellDTO.description() != null) spell.setDescription(spellDTO.description());
        if (spellDTO.castingTime() != null) spell.setCastingTime(spellDTO.castingTime());
        if (spellDTO.range() != null) spell.setRange(spellDTO.range());
        if (spellDTO.duration() != null) spell.setDuration(spellDTO.duration());
        if (spellDTO.ritual() != null) spell.setRitual(spellDTO.ritual());
        if (spellDTO.concentration() != null) spell.setConcentration(spellDTO.concentration());

        return spellRepository.save(spell);
    }

    @Transactional
    public void truncateSpellsTable() {
        spellRepository.truncateTable();
    }

    public void deleteSpellById(Long id) {
        Spell spell = spellRepository.findById(id)
                .orElseThrow(() -> new SpellNotFoundException("Spell not found with id: " + id));
        spellRepository.deleteById(id);
    }
}
