package org.jonas.spellify.api.service;

import org.jonas.spellify.api.model.SpellShort;
import org.jonas.spellify.api.model.SpellCatalog;
import org.jonas.spellify.api.model.dto.SpellApiDTO;
import org.jonas.spellify.exception.SpellApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApiSpellService {

    private final WebClient webClient;

    @Autowired
    public ApiSpellService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public Mono<SpellCatalog> fetchSpellsFromApi() {
        return webClient.get()
                .uri("/spells")
                .retrieve()
                .bodyToMono(SpellCatalog.class)
                .onErrorResume(error -> Mono.error(new SpellApiException("Error fetching spell list: " + error.getMessage())));
    }

    public Mono<SpellApiDTO> fetchSpellByIndex(String index) {
        return webClient.get()
                .uri("/spells/" + URLEncoder.encode(index, StandardCharsets.UTF_8))
                .retrieve()
                .bodyToMono(SpellApiDTO.class)
                //.map(this::mapToSpellApiDTO)
                .onErrorResume(error -> Mono.error(new SpellApiException("Error fetching spell details for: " + index + " - " + error.getMessage())));
    }

    public Mono<List<SpellApiDTO>> fetchAllSpells() {
        return fetchSpellsFromApi()
                .flatMap(spellList -> {
                    List<String> spellIndexes = spellList.getResults().stream()
                            .map(SpellShort::getIndex)
                            .collect(Collectors.toList());

                    return Flux.fromIterable(spellIndexes)
                            .flatMap(this::fetchSpellByIndex)
                            .collectList();
                })
                .onErrorResume(error -> Mono.error(new SpellApiException("Error fetching all spells: " + error.getMessage())));
    }

//    private SpellApiDTO mapToSpellApiDTO(SpellApi spellApi) {
//        SpellApiDTO spellApiDTO = new SpellApiDTO();
//
//        spellApiDTO.setIndex(spellApi.getIndex());
//        spellApiDTO.setName(spellApi.getName());
//        spellApiDTO.setCastingTime(spellApi.getCastingTime());
//        spellApiDTO.setLevel(spellApi.getLevel());
//        spellApiDTO.setRange(spellApi.getRange());
//        spellApiDTO.setRitual(spellApi.isRitual());
//        spellApiDTO.setDuration(spellApi.getDuration());
//        spellApiDTO.setConcentration(spellApi.isConcentration());
//
//        for (String desc : spellApi.getDescription()) {
//            SpellDescriptionApiDTO spellDescriptionDTO = new SpellDescriptionApiDTO(desc);
//            spellApiDTO.addDescription(spellDescriptionDTO);
//        }
//        return spellApiDTO;
//    }
}
