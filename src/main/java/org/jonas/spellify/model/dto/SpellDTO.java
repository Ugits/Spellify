package org.jonas.spellify.model.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import java.util.List;

public record SpellDTO(
        String index,
        String name,

        @Min(value = 0, message = "Level must be at least 0")
        @Max(value = 9, message = "Level must be at most 9")
        Integer level,

        @Size(max = 1000, message = "Description cannot exceed 1000 characters")
        List<String> description,

        String castingTime,
        String range,
        String duration,
        Boolean ritual,
        Boolean concentration
) {}