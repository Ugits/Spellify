package org.jonas.spellify.controller;

import org.jonas.spellify.model.dto.SpellDTO;
import org.jonas.spellify.model.entity.Spell;
import org.jonas.spellify.service.ClientSpellService;
import org.jonas.spellify.model.dto.validation.ValidationHandler;
import org.jonas.spellify.service.SpellFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/spells")
@CrossOrigin(origins = "*")
public class ClientSpellController {

    private final ClientSpellService clientSpellService;
    private final SpellFileService spellFileService;
    private final ValidationHandler validationHandler;


    @Autowired
    public ClientSpellController(ClientSpellService clientSpellService, SpellFileService spellFileService, ValidationHandler validationHandler) {
        this.clientSpellService = clientSpellService;
        this.spellFileService = spellFileService;
        this.validationHandler = validationHandler;
    }

    @GetMapping("/sample/{index1}/{index2}/{index3}")
    public ResponseEntity<List<Spell>> sampleSpell(
            @PathVariable String index1,
            @PathVariable String index2,
            @PathVariable String index3) {

        return ResponseEntity.ok(clientSpellService.getSpellsByIndexes(index1, index2, index3));
    }


    @GetMapping("/all-names")
    public ResponseEntity<List<String>> getSpellNames() {
        List<String> spellNames = spellFileService.getSpellNames();
        return ResponseEntity.ok(spellNames);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Spell>> getSpells() {
        System.out.println("ENTER all SPELLS in WS CONTROLLER");
        return ResponseEntity.ok(clientSpellService.getAllSpells());
    }

    @GetMapping("/{name}")
    public ResponseEntity<Spell> getSpell(@PathVariable String name) {
        validationHandler.validateSpellDTO(SpellDTO.createWithName(name));
        return ResponseEntity.ok(clientSpellService.getSpellByName(name));
    }

    @GetMapping("/level/{level}")
    public ResponseEntity<List<Spell>> getSpellsByLevel(@PathVariable("level") Integer level) {
        validationHandler.validateSpellDTO(SpellDTO.createWithLevel(level));
        return ResponseEntity.ok(clientSpellService.getSpellsByLevel(level));
    }

    @GetMapping("/usable/{level}")
    public ResponseEntity<List<Spell>> getSpellsByMaxLevel(@PathVariable Integer level) {
        validationHandler.validateSpellDTO(SpellDTO.createWithLevel(level));
        return ResponseEntity.ok(clientSpellService.getSpellsByMaxLevel(level));
    }

    @GetMapping("/ritual/{ritual}")
    public ResponseEntity<List<Spell>> getSpellsByRitualAndMaxLevel(
            @PathVariable("ritual") Boolean ritual,
            @RequestParam(required = false, name = "max-level", defaultValue = "9") Integer maxLevel
    ) {
        validationHandler.validateSpellDTO(SpellDTO.createWithRitualAndLevel(ritual, maxLevel));
        return ResponseEntity.ok(clientSpellService.getSpellsByRitualAndMaxLevel(ritual, maxLevel));
    }

    @GetMapping("/casting-time/{casting-time}")
    public ResponseEntity<List<Spell>> getSpellsByCastingTimeAndMaxLevel(
            @PathVariable("casting-time") String castingTime,
            @RequestParam(required = false, name = "max-level", defaultValue = "9") Integer maxLevel
    ) {
        validationHandler.validateSpellDTO(SpellDTO.createWithCastingTimeAndLevel(castingTime, maxLevel));
        return ResponseEntity.ok(clientSpellService.getSpellsByCastingTimeAndMaxLevel(castingTime, maxLevel));
    }

    @GetMapping("/concentration/{concentration}")
    public ResponseEntity<List<Spell>> getSpellsByConcentrationAndMaxLevel(
            @PathVariable("concentration") Boolean concentration,
            @RequestParam(required = false, name = "max-level", defaultValue = "9") Integer maxLevel
    ) {
        validationHandler.validateSpellDTO(SpellDTO.createWithConcentrationAndLevel(concentration, maxLevel));
        return ResponseEntity.ok(clientSpellService.getSpellsByConcentrationAndMaxLevel(concentration, maxLevel));
    }

    @GetMapping("/duration/{duration}")
    public ResponseEntity<List<Spell>> getSpellsByDurationAndMaxLevel(
            @PathVariable("duration") String duration,
            @RequestParam(required = false, name = "max-level", defaultValue = "9") Integer maxLevel
    ) {
        validationHandler.validateSpellDTO(SpellDTO.createWithDurationAndLevel(duration, maxLevel));
        return ResponseEntity.ok(clientSpellService.getSpellsByDurationAndMaxLevel(duration, maxLevel));
    }

    @GetMapping("/range/{range}")
    public ResponseEntity<List<Spell>> getSpellByRangeAndMaxLevel(
            @PathVariable("range") String range,
            @RequestParam(required = false, name = "max-level", defaultValue = "9") Integer maxLevel
    ) {
        validationHandler.validateSpellDTO(SpellDTO.createWithRangeAndLevel(range, maxLevel));
        return ResponseEntity.ok(clientSpellService.getSpellsByRangeAndMaxLevel(range, maxLevel));
    }
}
