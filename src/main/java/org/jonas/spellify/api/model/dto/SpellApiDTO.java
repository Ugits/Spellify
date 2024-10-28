package org.jonas.spellify.api.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record SpellApiDTO(
        String index,
        String name,
        List<CharClassApiDTO> classes,
        @JsonProperty("desc")
        List<SpellDescriptionApiDTO> description,
        Integer level,
        @JsonProperty("casting_time")
        String castingTime,
        String range,
        String duration,
        Boolean ritual,
        Boolean concentration
) {
}
