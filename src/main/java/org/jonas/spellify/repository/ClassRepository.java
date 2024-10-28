package org.jonas.spellify.repository;

import org.jonas.spellify.model.entity.CharClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClassRepository extends JpaRepository<CharClass, Long> {

    Optional<CharClass> findByIndex(String index);

}
