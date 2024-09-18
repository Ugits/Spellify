package org.jonas.spellify.api.controller;

import org.jonas.spellify.api.model.SpellApi;
import org.jonas.spellify.api.model.SpellCatalog;
import org.jonas.spellify.api.service.ApiSpellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/spells")
public class ApiSpellController {

    private final ApiSpellService apiSpellService;

    @Autowired
    public ApiSpellController(ApiSpellService apiSpellService) {
        this.apiSpellService = apiSpellService;
    }

    @GetMapping
    public Mono<ResponseEntity<SpellCatalog>> getSpells() {
        return apiSpellService.fetchSpellsFromApi()
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.NO_CONTENT).build());
    }

    @GetMapping("/{index}")
    public Mono<ResponseEntity<SpellApi>> getSpell(@PathVariable("index") String index) {
        return apiSpellService.fetchSpellByIndex(index)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/fetch-all")
    public Mono<ResponseEntity<List<SpellApi>>> fetchAllSpells() {
        return apiSpellService.fetchAllSpells()
                .map(spells -> {
                    if (spells.isEmpty()) {
                        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
                    }
                    return ResponseEntity.ok(spells);
                });
    }
}
