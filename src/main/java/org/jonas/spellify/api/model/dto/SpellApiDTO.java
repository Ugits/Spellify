package org.jonas.spellify.api.model.dto;

import java.util.ArrayList;
import java.util.List;

public class SpellApiDTO {
    private String index;
    private String name;
    private Integer level;
    private List<SpellDescriptionApiDTO> description = new ArrayList<>();
    private String castingTime;
    private String range;
    private String duration;
    private Boolean ritual;
    private Boolean concentration;

    public SpellApiDTO() {
    }

    public SpellApiDTO(String index, String name, Integer level, List<SpellDescriptionApiDTO> description, String castingTime, String range, String duration, Boolean ritual, Boolean concentration) {
        this.index = index;
        this.name = name;
        this.level = level;
        this.description = description;
        this.castingTime = castingTime;
        this.range = range;
        this.duration = duration;
        this.ritual = ritual;
        this.concentration = concentration;
    }

    public void addDescription(SpellDescriptionApiDTO description) {
        this.description.add(description);
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public List<SpellDescriptionApiDTO> getDescription() {
        return description;
    }

    public void setDescription(List<SpellDescriptionApiDTO> description) {
        this.description = description;
    }

    public String getCastingTime() {
        return castingTime;
    }

    public void setCastingTime(String castingTime) {
        this.castingTime = castingTime;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Boolean getRitual() {
        return ritual;
    }

    public void setRitual(Boolean ritual) {
        this.ritual = ritual;
    }

    public Boolean getConcentration() {
        return concentration;
    }

    public void setConcentration(Boolean concentration) {
        this.concentration = concentration;
    }
}