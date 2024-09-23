package org.jonas.spellify.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "spell_descriptions")
public class SpellDescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 1000)
    private String description;

    @ManyToOne
    @JoinColumn(name = "spell_id", nullable = false)
    @JsonBackReference
    private Spell spell;

    public SpellDescription() {}

    public SpellDescription(String description, Spell spell) {
        this.description = description;
        this.spell = spell;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Spell getSpell() {
        return spell;
    }

    public void setSpell(Spell spell) {
        this.spell = spell;
    }


}
