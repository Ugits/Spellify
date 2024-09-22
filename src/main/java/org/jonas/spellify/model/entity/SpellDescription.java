package org.jonas.spellify.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "spell_descriptions")
public class SpellDescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Unique identifier for each description

    @Column(length = 1000) // Adjust the length as needed
    private String description; // The description text

    @ManyToOne // Many descriptions can belong to one spell
    @JoinColumn(name = "spell_id", nullable = false) // Foreign key to the spells table
    @JsonBackReference // Prevents circular references during serialization
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
