package org.jonas.spellify.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "spells")
public class Spell {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String index;

    private String name;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "spell_class",
            joinColumns = @JoinColumn(name = "spell_id"),
            inverseJoinColumns = @JoinColumn(name = "class_id")
    )
    @JsonManagedReference
    private List<CharClass> classes = new ArrayList<>();

    @OneToMany(
            mappedBy = "spell",
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SpellDescription> description = new ArrayList<>();

    @JsonProperty("casting_time")
    private String castingTime;

    private Integer level;

    private String range;

    private Boolean ritual;

    private String duration;

    private Boolean concentration;

    public Spell() {}

    public Long getId() {
        return id;
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

    public List<SpellDescription> getDescription() {
        return description;
    }

    public void setDescription(List<SpellDescription> description) {
        this.description = description;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public boolean isRitual() {
        return ritual;
    }

    public void setRitual(boolean ritual) {
        this.ritual = ritual;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public boolean isConcentration() {
        return concentration;
    }

    public void setConcentration(boolean concentration) {
        this.concentration = concentration;
    }

    public String getCastingTime() {
        return castingTime;
    }

    public void setCastingTime(String castingTime) {
        this.castingTime = castingTime;
    }


    public void addDescription(SpellDescription description) {
        description.setSpell(this); // Set the owning side of the relationship
        this.description.add(description); // Add the description to the collection
    }

    @Override
    public String toString() {
        return "Spell{" +
                "id=" + id +
                ", index='" + index + '\'' +
                ", name='" + name + '\'' +
                ", castingTime='" + castingTime + '\'' +
                ", description=" + description +
                ", level=" + level +
                ", range='" + range + '\'' +
                ", ritual=" + ritual +
                ", duration='" + duration + '\'' +
                ", concentration=" + concentration +
                '}';
    }

    public List<CharClass> getClasses() {
        return classes;
    }

    public void setClasses(List<CharClass> classes) {
        this.classes = classes;
    }
}
