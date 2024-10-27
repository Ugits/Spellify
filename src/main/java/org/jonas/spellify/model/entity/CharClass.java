package org.jonas.spellify.model.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "classes")
public class CharClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String index;

    String name;

    @ManyToMany(mappedBy = "classes")
    private List<Spell> Spells = new ArrayList<>();
    public CharClass() {
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
        return Spells;
    }

    public void setSpells(List<Spell> spells) {
        Spells = spells;
    }
}
