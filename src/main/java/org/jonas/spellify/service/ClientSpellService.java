package org.jonas.spellify.service;

import org.jonas.spellify.repository.SpellRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientSpellService {

    private final SpellRepository spellRepository;

    public ClientSpellService(SpellRepository spellRepository) {
        this.spellRepository = spellRepository;
    }

    
}
