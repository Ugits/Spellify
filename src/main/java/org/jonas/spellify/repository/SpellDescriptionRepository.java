package org.jonas.spellify.repository;

import org.jonas.spellify.model.entity.SpellDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpellDescriptionRepository extends JpaRepository<SpellDescription, Long> {

    void deleteBySpellId(Long SpellId);
}
