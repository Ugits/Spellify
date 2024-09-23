package org.jonas.spellify.controller;

import org.jonas.spellify.model.dto.SpellDTO;
import org.jonas.spellify.model.entity.Spell;
import org.jonas.spellify.service.ClientSpellService;
import org.jonas.spellify.service.validation.SpellValidationHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/spells")
@Validated
public class ClientSpellController {

    private final ClientSpellService clientSpellService;
    private final SpellValidationHandler spellValidationHandler;

    @Autowired
    public ClientSpellController(ClientSpellService clientSpellService, SpellValidationHandler spellValidationHandler) {
        this.clientSpellService = clientSpellService;
        this.spellValidationHandler = spellValidationHandler;
    }

    @GetMapping
    public ResponseEntity<List<Spell>> getSpells() {
        return ResponseEntity.ok(clientSpellService.getAllSpells());
    }

    @GetMapping("/{name}")
    public ResponseEntity<Spell> getSpell(@PathVariable String name) {
        spellValidationHandler.validateDTO(SpellDTO.createWithName(name));
        return ResponseEntity.ok(clientSpellService.getSpellByName(name));
    }

    @GetMapping("/level/{level}")
    public ResponseEntity<List<Spell>> getSpellsByLevel(@PathVariable("level") Integer level) {
        spellValidationHandler.validateDTO(SpellDTO.createWithLevel(level));
        return ResponseEntity.ok(clientSpellService.getSpellsByLevel(level));
    }

    @GetMapping("/usable/{level}")
    public ResponseEntity<List<Spell>> getSpellsByMaxLevel(@PathVariable Integer level) {
        spellValidationHandler.validateDTO(SpellDTO.createWithLevel(level));
        return ResponseEntity.ok(clientSpellService.getSpellsByMaxLevel(level));
    }

    @GetMapping("/ritual/{ritual}")
    public ResponseEntity<List<Spell>> getSpellsByRitualAndMaxLevel(
            @PathVariable("ritual") Boolean ritual,
            @RequestParam(required = false, name = "max-level", defaultValue = "9")  Integer maxLevel
    ) {
        spellValidationHandler.validateDTO(SpellDTO.createWithRitualAndLevel(ritual, maxLevel));
        return ResponseEntity.ok(clientSpellService.getSpellsByRitualAndMaxLevel(ritual, maxLevel));
    }

    @GetMapping("/casting-time/{casting-time}")
    public ResponseEntity<List<Spell>> getSpellsByCastingTimeAndMaxLevel(
            @PathVariable("casting-time") String castingTime,
            @RequestParam(required = false, name = "max-level", defaultValue = "9") Integer maxLevel
    ) {
        spellValidationHandler.validateDTO(SpellDTO.createWithCastingTimeAndLevel(castingTime, maxLevel));
        return ResponseEntity.ok(clientSpellService.getSpellsByCastingTimeAndMaxLevel(castingTime, maxLevel));
    }

    @GetMapping("/concentration/{concentration}")
    public ResponseEntity<List<Spell>> getSpellsByConcentrationAndMaxLevel(
            @PathVariable("concentration") Boolean concentration,
            @RequestParam(required = false, name = "max-level", defaultValue = "9") Integer maxLevel
    ) {
        spellValidationHandler.validateDTO(SpellDTO.createWithConcentrationAndLevel(concentration, maxLevel));
        return ResponseEntity.ok(clientSpellService.getSpellsByConcentrationAndMaxLevel(concentration, maxLevel));
    }

    @GetMapping("/duration/{duration}")
    public ResponseEntity<List<Spell>> getSpellsByDurationAndMaxLevel(
            @PathVariable("duration") String duration,
            @RequestParam(required = false, name = "max-level", defaultValue = "9") Integer maxLevel
    ) {
        spellValidationHandler.validateDTO(SpellDTO.createWithDurationAndLevel(duration, maxLevel));
        return ResponseEntity.ok(clientSpellService.getSpellsByDurationAndMaxLevel(duration, maxLevel));
    }

    @GetMapping("/range/{range}")
    public ResponseEntity<List<Spell>> getSpellByRangeAndMaxLevel(
            @PathVariable("range") String range,
            @RequestParam(required = false, name = "max-level", defaultValue = "9") Integer maxLevel
    ) {
        spellValidationHandler.validateDTO(SpellDTO.createWithRangeAndLevel(range, maxLevel));
        return ResponseEntity.ok(clientSpellService.getSpellsByRangeAndMaxLevel(range, maxLevel));
    }
}
