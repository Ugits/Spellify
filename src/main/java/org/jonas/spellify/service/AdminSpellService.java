package org.jonas.spellify.service;

import org.jonas.spellify.exception.SpellNotFoundException;
import org.jonas.spellify.model.entity.Spell;
import org.jonas.spellify.repository.SpellRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminSpellService {

    private final SpellRepository spellRepository;

    public AdminSpellService(SpellRepository spellRepository) {
        this.spellRepository = spellRepository;
    }

    public List<Spell> saveAllSpells(List<Spell> spells) {
        return spellRepository.saveAll(spells);
    }

    public Spell saveSpell(Spell spell) {
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
