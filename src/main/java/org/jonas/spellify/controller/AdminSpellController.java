package org.jonas.spellify.controller;

import org.jonas.spellify.model.dto.SpellDTO;
import org.jonas.spellify.model.dto.SpellIdDTO;
import org.jonas.spellify.model.dto.UpdateSpellDTO;
import org.jonas.spellify.model.entity.Spell;
import org.jonas.spellify.service.AdminSpellService;
import org.jonas.spellify.model.dto.validation.ValidationHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("admin/spells")
public class AdminSpellController {

    private final AdminSpellService adminSpellService;
    private final ValidationHandler validationHandler;

    @Autowired
    public AdminSpellController(AdminSpellService adminSpellService, ValidationHandler validationHandler) {
        this.adminSpellService = adminSpellService;
        this.validationHandler = validationHandler;
    }

    @PostMapping("/add-batch")
    public ResponseEntity<List<Spell>> addSpellsBatch(@RequestBody List<SpellDTO> spellsDTO) {

        spellsDTO.forEach(validationHandler::validateSpellDTO);
        List<Spell> spells = spellsDTO.stream()
                .map((adminSpellService::convertSpellDtoToEntity))
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.CREATED).body(adminSpellService.saveAllSpells(spells));
    }

    @PostMapping("/add")
    public ResponseEntity<Spell> addSpell(@RequestBody SpellDTO spellDTO) {

        validationHandler.validateSpellDTO(spellDTO);
        Spell spell = adminSpellService.convertSpellDtoToEntity(spellDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(adminSpellService.saveSpell(spell));
    }

    @PutMapping("/sync-api")
    public ResponseEntity<List<Spell>> syncSpellsFromApi() throws IOException {

        List<Spell> updatedSpells = adminSpellService.syncSpellsFromApi();
        return ResponseEntity.ok(updatedSpells);
    }

    @PatchMapping("/{index}")
    public ResponseEntity<Spell> updateSpellBySpellIndex(
            @PathVariable("index") String index,
            @RequestBody UpdateSpellDTO updateSpellDTO
    ) {
        validationHandler.validateSpellDTO(SpellDTO.createWithIndex(index));
        validationHandler.validateUpdateSpellDTO(updateSpellDTO);
        Spell updatedSpell = adminSpellService.updateSpell(index, updateSpellDTO);
        return ResponseEntity.ok(updatedSpell);
    }

    @DeleteMapping("/truncate")
    public ResponseEntity<Void> truncateSpells() {
        adminSpellService.truncateSpellsTable();
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpellById(@PathVariable("id") Long id) {

        validationHandler.validateSpellIdDTO(new SpellIdDTO(id));
        adminSpellService.deleteSpellById(id);
        return ResponseEntity.noContent().build();
    }
}
