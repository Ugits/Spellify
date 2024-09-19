package org.jonas.spellify.repository;

import org.jonas.spellify.model.entity.Spell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SpellRepository extends JpaRepository<Spell, Long> {
    @Modifying
    @Query(value = "TRUNCATE TABLE spells RESTART IDENTITY", nativeQuery = true)
    void truncateTable();
}
