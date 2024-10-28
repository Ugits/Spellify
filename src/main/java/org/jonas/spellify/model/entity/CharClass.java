package org.jonas.spellify.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "classes")
public class CharClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String index;

    private String name;

    @ManyToMany(mappedBy = "classes")
    @JsonBackReference
    private List<Spell> spells = new ArrayList<>();

    public CharClass() {
    }

    public CharClass(String index, String name) {
        this.index = index;
        this.name = name;
        //this.spells = spells;
    }

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

    public List<Spell> getSpells() {
        return spells;
    }

    public void setSpells(List<Spell> spells) {
        this.spells = spells;
    }

    public void addSpell(Spell spell) {
        this.spells.add(spell);
        spell.getClasses().add(this); // Ensure bidirectional consistency
    }

    @Override
    public String toString() {
        return "CharClass{" +
                "id=" + id +
                ", index='" + index + '\'' +
                ", name='" + name + '\'' +
                ", spells=" + spells +
                '}';
    }
}
