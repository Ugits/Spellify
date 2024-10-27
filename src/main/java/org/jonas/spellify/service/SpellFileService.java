package org.jonas.spellify.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jonas.spellify.exception.SpellNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class SpellFileService {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${spell.names.file.path}")
    private String spellNamesFilePath;

    public List<String> getSpellNames() {
        File jsonFile = new File(spellNamesFilePath);
        try {
            return objectMapper.readValue(
                    jsonFile,
                    objectMapper.getTypeFactory().constructCollectionType(List.class, String.class)
            );
        } catch (IOException e) {
            throw new SpellNotFoundException("Failed to read the spells from the JSON file");
        }
    }

}
