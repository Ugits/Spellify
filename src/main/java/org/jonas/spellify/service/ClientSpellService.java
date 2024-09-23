package org.jonas.spellify.service;

import org.jonas.spellify.exception.SpellNotFoundException;
import org.jonas.spellify.model.entity.Spell;
import org.jonas.spellify.repository.SpellRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientSpellService {

    private final SpellRepository spellRepository;

    public ClientSpellService(SpellRepository spellRepository) {
        this.spellRepository = spellRepository;
    }

    public List<Spell> getAllSpells() {
        List<Spell> spells = spellRepository.findAll();
        if (spells.isEmpty()) throw new SpellNotFoundException(
                "No spells found");
        return spells;
    }

    public Spell getSpellByName(String name) {
        return spellRepository.findSpellByNameIgnoreCase(name)
                .orElseThrow(() -> new SpellNotFoundException(
                        "Spell not found with name: " + "[" + name + "]"));
    }

    public List<Spell> getSpellsByLevel(Integer level) {
        List<Spell> spells = spellRepository.findSpellsByLevel(level);
        if (spells.isEmpty()) throw new SpellNotFoundException(
                "No spells found with level: " + "[" + level + "]");
        return spells;
    }

    public List<Spell> getSpellsByMaxLevel(Integer level) {
        List<Spell> spells = spellRepository.findSpellsByLevelLessThanEqual(level);
        if (spells.isEmpty()) throw new SpellNotFoundException(
                "No spells found with level: " + "[" + level + "]" + ", or below level");
        return spells;
    }

    public List<Spell> getSpellsByRitualAndMaxLevel(Boolean ritual, Integer maxLevel) {
        List<Spell> spells = spellRepository.findSpellsByRitualAndLevelIsLessThanEqual(ritual, maxLevel);
        if (spells.isEmpty()) {
            throw new SpellNotFoundException(
                    "No spells found with ritual: " + "[" + ritual + "]" + " and max level: " + "[" + maxLevel + "]");
        }
        return spells;
    }

    public List<Spell> getSpellsByCastingTimeAndMaxLevel(String castingTime, Integer maxLevel) {
        List<Spell> spells = spellRepository.findSpellsByCastingTimeAndLevelIsLessThanEqual(castingTime, maxLevel);
        if (spells.isEmpty()) {
            throw new SpellNotFoundException(
                    "No spells found with casting time: " + "[" + castingTime + "]" + " and max level: " + "[" + maxLevel + "]");
        }
        return spells;
    }

    public List<Spell> getSpellsByConcentrationAndMaxLevel(Boolean concentration, Integer maxLevel) {
        List<Spell> spells = spellRepository.findSpellsByConcentrationAndLevelIsLessThanEqual(concentration, maxLevel);
        if (spells.isEmpty()) {
            throw new SpellNotFoundException(
                    "No spells found with concentration: " + "[" + concentration + "]" + " and max level: " + "[" + maxLevel + "]");
        }
        return spells;
    }

    public List<Spell> getSpellsByDurationAndMaxLevel(String duration, Integer maxLevel) {
        List<Spell> spells = spellRepository.findSpellsByDurationAndLevelIsLessThanEqual(duration, maxLevel);
        if (spells.isEmpty()) {
            throw new SpellNotFoundException(
                    "No spells found with duration: " + "[" + duration + "]" + " and max level: " + "[" + maxLevel + "]");
        }
        return spells;
    }

    public List<Spell> getSpellsByRangeAndMaxLevel(String range, Integer maxLevel) {
        List<Spell> spells = spellRepository.findSpellsByRangeAndLevelIsLessThanEqual(range, maxLevel);
        if (spells.isEmpty()) {
            throw new SpellNotFoundException(
                    "No spells found with range: " + "[" + range + "]" + " and max level: " + "[" + maxLevel + "]");
        }
        return spells;
    }

}
