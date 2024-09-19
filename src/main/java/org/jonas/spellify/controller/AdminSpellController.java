package org.jonas.spellify.controller;

import org.jonas.spellify.model.entity.Spell;
import org.jonas.spellify.service.AdminSpellService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin/spells")
public class AdminSpellController {

    private final AdminSpellService adminSpellService;

    public AdminSpellController(AdminSpellService adminSpellService) {
        this.adminSpellService = adminSpellService;
    }

    @PostMapping("/add-batch")
    public ResponseEntity<List<Spell>> addSpellsBatch(@RequestBody List<Spell> spells) {
        List<Spell> savedSpells = adminSpellService.saveAllSpells(spells);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSpells);
    }

    @PostMapping("/add")
    public ResponseEntity<Spell> addSpell(@RequestBody Spell spell) {
        Spell savedSpell = adminSpellService.saveSpell(spell);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSpell);
    }

}
