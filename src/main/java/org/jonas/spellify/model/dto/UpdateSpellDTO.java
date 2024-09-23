package org.jonas.spellify.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import java.util.List;

public record UpdateSpellDTO(

        @Size(min = 1, max = 50, message = "Name must be between 1 and 50 characters.")
        String name,

        @Min(value = 0, message = "Level must be at least 0.")
        @Max(value = 9, message = "Level must be at most 9.")
        Integer level,

        List<SpellDescriptionDTO> description,

        @Size(min = 1, max = 50, message = "Casting time must be between 1 and 50 characters.")
        @JsonProperty("casting_time")
        String castingTime,

        @Size(min = 1, max = 50, message = "Range must be between 1 and 50 characters.")
        String range,

        @Size(min = 1, max = 50, message = "Duration must be between 1 and 50 characters.")
        String duration,

        Boolean ritual,

        Boolean concentration
) {
}
