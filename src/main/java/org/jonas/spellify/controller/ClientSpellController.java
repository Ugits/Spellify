package org.jonas.spellify.controller;

import org.jonas.spellify.model.entity.Spell;
import org.jonas.spellify.service.ClientSpellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/spells")
public class ClientSpellController {

    // color #E91E63 for docs
    private final ClientSpellService clientSpellService;

    @Autowired
    public ClientSpellController(ClientSpellService clientSpellService) {
        this.clientSpellService = clientSpellService;
    }

    @GetMapping
    public ResponseEntity<List<Spell>> getSpells() {
        return ResponseEntity.ok(clientSpellService.getAllSpells());
    }

    @GetMapping("/{name}")
    public ResponseEntity<Spell> getSpell(@PathVariable String name) {
        return ResponseEntity.ok(clientSpellService.getSpellByName(name));
    }

    @GetMapping("/level/{level}")
    public ResponseEntity<List<Spell>> getSpellsByLevel(@PathVariable("level") Integer level) {
        return ResponseEntity.ok(clientSpellService.getSpellsByLevel(level));
    }

    @GetMapping("/usable/{level}")
    public ResponseEntity<List<Spell>> getSpellsByMaxLevel(@PathVariable Integer level) {
        return ResponseEntity.ok(clientSpellService.getSpellsByMaxLevel(level));
    }

    @GetMapping("/ritual/{ritual}")
    public ResponseEntity<List<Spell>> getSpellsByRitualAndMaxLevel(
            @PathVariable("ritual") Boolean ritual,
            @RequestParam(required = false, name = "max-level", defaultValue = "9") Integer maxLevel
    ) {
        return ResponseEntity.ok(clientSpellService.getSpellsByRitualAndMaxLevel(ritual, maxLevel));
    }

    @GetMapping("/casting-time/{casting-time}")
    public ResponseEntity<List<Spell>> getSpellsByCastingTimeAndMaxLevel(
            @PathVariable("casting-time") String castingTime,
            @RequestParam(required = false, name = "max-level", defaultValue = "9") Integer maxLevel
    ) {
        return ResponseEntity.ok(clientSpellService.getSpellsByCastingTimeAndMaxLevel(castingTime, maxLevel));
    }

    @GetMapping("/concentration/{concentration}")
    public ResponseEntity<List<Spell>> getSpellsByConcentrationAndMaxLevel(
            @PathVariable("concentration") Boolean concentration,
            @RequestParam(required = false, name = "max-level", defaultValue = "9") Integer maxLevel
    ) {
        return ResponseEntity.ok(clientSpellService.getSpellsByConcentrationAndMaxLevel(concentration, maxLevel));
    }

    @GetMapping("/duration/{duration}")
    public ResponseEntity<List<Spell>> getSpellsByDurationAndMaxLevel(
            @PathVariable("duration") String duration,
            @RequestParam(required = false, name = "max-level", defaultValue = "9") Integer maxLevel
    ) {
        return ResponseEntity.ok(clientSpellService.getSpellsByDurationAndMaxLevel(duration, maxLevel));
    }

    @GetMapping("/range/{range}")
    public ResponseEntity<List<Spell>> getSpellByRangeAndMaxLevel(
            @PathVariable("range") String range,
            @RequestParam(required = false, name = "max-level", defaultValue = "9") Integer maxLevel
    ) {
        return ResponseEntity.ok(clientSpellService.getSpellsByRangeAndMaxLevel(range, maxLevel));
    }
}
