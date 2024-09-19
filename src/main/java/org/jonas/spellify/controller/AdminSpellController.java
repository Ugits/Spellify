package org.jonas.spellify.controller;

import org.jonas.spellify.model.dto.SpellDTO;
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

    @PutMapping("/sync-api")
    public ResponseEntity<List<Spell>> syncSpellsFromApi() {
        List<Spell> updatedSpells = adminSpellService.syncSpellsFromApi();
        return ResponseEntity.ok(updatedSpells);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Spell> updateSpellById(
            @PathVariable("id") Long id,
            @RequestBody SpellDTO spellDTO
    ) {
        Spell updatedSpell = adminSpellService.updateSpell(id, spellDTO);
        return ResponseEntity.ok(updatedSpell);
    }

    @DeleteMapping("/truncate")
    public ResponseEntity<Void> truncateSpells() {
        adminSpellService.truncateSpellsTable();
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpellById(@PathVariable("id") Long id) {
        adminSpellService.deleteSpellById(id);
        return ResponseEntity.noContent().build();
    }
}
