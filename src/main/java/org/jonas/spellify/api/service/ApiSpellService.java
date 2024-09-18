package org.jonas.spellify.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ApiSpellService {

    private final WebClient webClient;

    @Autowired
    public ApiSpellService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }


}
