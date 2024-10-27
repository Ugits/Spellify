package org.jonas.spellify.repository;

import org.jonas.spellify.model.entity.CharClass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassRepository extends JpaRepository<CharClass, Long> {



}
