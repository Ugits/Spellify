package org.jonas.spellify.api.service;

import org.jonas.spellify.api.model.SpellApi;
import org.jonas.spellify.api.model.SpellShort;
import org.jonas.spellify.api.model.SpellCatalog;
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

    public Mono<SpellApi> fetchSpellByIndex(String index) {
        return webClient.get()
                .uri("/spells/" + URLEncoder.encode(index, StandardCharsets.UTF_8))
                .retrieve()
                .bodyToMono(SpellApi.class)
                .onErrorResume(error -> Mono.error(new SpellApiException("Error fetching spell details for: " + index + " - " + error.getMessage())));
    }

    public Mono<List<SpellApi>> fetchAllSpells() {
        return fetchSpellsFromApi()
                .flatMap(spellList -> {
                    List<String> spellIndexes = spellList.getResults().stream()
                            .map(SpellShort::getIndex)
                            .collect(Collectors.toList());
                    // kan jag returnera nonflux
                    return Flux.fromIterable(spellIndexes)
                            .flatMap(this::fetchSpellByIndex)
                            .collectList();
                })
                .onErrorResume(error -> Mono.error(new SpellApiException("Error fetching all spells: " + error.getMessage())));
        //        Mono<SpellCatalog> spellList = fetchSpellsFromApi();
//
//        List<SpellApi> test = spellList.map(object -> {
//            List<String> indexList = object.getResults().stream()
//                    .map(SpellShort::getIndex)
//                    .toList();
//
//            Mono<List<SpellApi>> spells = indexList.stream()
//                    .flatMap(index -> fetchSpellByName(index))
//                    .co;
//            return spells;
//        })
    }


}
