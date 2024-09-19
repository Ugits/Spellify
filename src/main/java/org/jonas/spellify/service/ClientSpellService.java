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
        return spellRepository.findAll();
    }

    public Spell getSpellByName(String name) {
        return spellRepository.findSpellByNameIgnoreCase(name)
                .orElseThrow(() -> new SpellNotFoundException("Spell not found with name: " + name));
    }

    public List<Spell> getSpellsByLevel(Integer level) {
        if (level == null || level < 0 || level > 9) {
            throw new IllegalArgumentException("Level must be a non-negative integer and between 0 and 9");
        }
        return spellRepository.findSpellsByLevel(level);
    }

    public List<Spell> getSpellsByMaxLevel(Integer level) {
        if (level == null || level < 0 || level > 9) {
            throw new IllegalArgumentException("Level must be a non-negative integer and between 0 and 9");
        }
        return spellRepository.findSpellsByLevelLessThanEqual(level);
    }

    public List<Spell> getSpellsByRitualAndMaxLevel(Boolean ritual, Integer maxLevel) {
        if (ritual == null) {
            throw new IllegalArgumentException("Ritual must not be null.");
        }
        if (maxLevel == null || maxLevel < 0 || maxLevel > 9) {
            throw new IllegalArgumentException("Level must be a non-negative integer and between 0 and 9");
        }
        List<Spell> spells = spellRepository.findSpellsByRitualAndLevelIsLessThanEqual(ritual, maxLevel);
        if (spells.isEmpty()) {
            throw new SpellNotFoundException("No spells found with ritual: " + ritual + " and max level: " + maxLevel);
        }
        return spells;
    }

    public List<Spell> getSpellsByCastingTimeAndMaxLevel(String castingTime, Integer maxLevel) {
        if (castingTime == null || castingTime.trim().isEmpty()) {
            throw new IllegalArgumentException("Casting time must not be null or empty.");
        }
        if (maxLevel == null || maxLevel < 0 || maxLevel > 9) {
            throw new IllegalArgumentException("Level must be a non-negative integer and between 0 and 9");
        }
        List<Spell> spells = spellRepository.findSpellsByCastingTimeAndLevelIsLessThanEqual(castingTime, maxLevel);
        if (spells.isEmpty()) {
            throw new SpellNotFoundException("No spells found with casting time: " + castingTime + " and max level: " + maxLevel);
        }
        return spells;
    }

    public List<Spell> getSpellsByConcentrationAndMaxLevel(Boolean concentration, Integer maxLevel) {
        if (concentration == null) {
            throw new IllegalArgumentException("Concentration must not be null.");
        }
        if (maxLevel == null || maxLevel < 0 || maxLevel > 9) {
            throw new IllegalArgumentException("Level must be a non-negative integer and between 0 and 9");
        }
        List<Spell> spells = spellRepository.findSpellsByConcentrationAndLevelIsLessThanEqual(concentration, maxLevel);
        if (spells.isEmpty()) {
            throw new SpellNotFoundException("No spells found with concentration: " + concentration + " and max level: " + maxLevel);
        }
        return spells;
    }

    public List<Spell> getSpellsByDurationAndMaxLevel(String duration, Integer maxLevel) {
        if (duration == null || duration.trim().isEmpty()) {
            throw new IllegalArgumentException("Duration must not be null or empty.");
        }
        if (maxLevel == null || maxLevel < 0 || maxLevel > 9) {
            throw new IllegalArgumentException("Level must be a non-negative integer and between 0 and 9");
        }
        List<Spell> spells = spellRepository.findSpellsByDurationAndLevelIsLessThanEqual(duration, maxLevel);
        if (spells.isEmpty()) {
            throw new SpellNotFoundException("No spells found with duration: " + duration + " and max level: " + maxLevel);
        }
        return spells;
    }

    public List<Spell> getSpellsByRangeAndMaxLevel(String range, Integer maxLevel) {
        if (range == null || range.trim().isEmpty()) {
            throw new IllegalArgumentException("Range must not be null or empty.");
        }
        if (maxLevel == null || maxLevel < 0 || maxLevel > 9) {
            throw new IllegalArgumentException("Level must be a non-negative integer and between 0 and 9");
        }
        List<Spell> spells = spellRepository.findSpellsByRangeAndLevelIsLessThanEqual(range, maxLevel);
        if (spells.isEmpty()) {
            throw new SpellNotFoundException("No spells found with range: " + range + " and max level: " + maxLevel);
        }
        return spells;
    }
}
