package org.jonas.spellify.service;

import org.jonas.spellify.repository.SpellRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminSpellService {

    private final SpellRepository spellRepository;

    public AdminSpellService(SpellRepository spellRepository) {
        this.spellRepository = spellRepository;
    }



}
