package org.jonas.spellify.api.model.dto;

public class SpellDescriptionApiDTO {

    private String description;

    public SpellDescriptionApiDTO(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
