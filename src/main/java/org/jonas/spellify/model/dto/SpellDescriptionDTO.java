package org.jonas.spellify.model.dto;

import jakarta.validation.constraints.Size;

public record SpellDescriptionDTO(

        @Size(max = 1000, message = "Description cannot exceed 1000 characters")
        String description
) {
}
