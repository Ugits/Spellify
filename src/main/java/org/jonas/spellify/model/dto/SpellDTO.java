package org.jonas.spellify.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import org.jonas.spellify.model.entity.SpellDescription;

import java.util.List;

public record SpellDTO(
        String index,
        String name,

        @Min(value = 0, message = "Level must be at least 0")
        @Max(value = 9, message = "Level must be at most 9")
        Integer level,

        List<SpellDescriptionDTO> description,

        @JsonProperty("casting_time")
        String castingTime,

        String range,
        String duration,
        Boolean ritual,
        Boolean concentration
) {}