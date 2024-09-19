package org.jonas.spellify.repository;

import jakarta.annotation.Nullable;
import org.jonas.spellify.model.entity.Spell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpellRepository extends JpaRepository<Spell, Long> {

    Optional<Spell> findByIndex(String index);

    Optional<Spell> findSpellByNameIgnoreCase(String name);

    List<Spell> findSpellsByLevel(Integer level);

    List<Spell> findSpellsByLevelLessThanEqual(Integer level);

    @Query("SELECT s FROM Spell s WHERE s.castingTime = :casting_time AND (:level IS NULL OR s.level <= :level)")
    List<Spell> findSpellsByCastingTimeAndLevelIsLessThanEqual(@Param("casting_time") String castingTime, @Nullable @Param("level") Integer level);

    @Query("SELECT s FROM Spell s WHERE s.concentration = :concentration AND (:level IS NULL OR s.level <= :level)")
    List<Spell> findSpellsByConcentrationAndLevelIsLessThanEqual(@Param("concentration") Boolean concentration, @Nullable @Param("level") Integer level);

    @Query("SELECT s FROM Spell s WHERE s.duration = :duration AND (:level IS NULL OR s.level <= :level)")
    List<Spell> findSpellsByDurationAndLevelIsLessThanEqual(@Param("duration") String duration, @Nullable @Param("level") Integer level);

    @Query("SELECT s FROM Spell s WHERE s.ritual = :ritual AND (:level IS NULL OR s.level <= :level)")
    List<Spell> findSpellsByRitualAndLevelIsLessThanEqual(@Param("ritual") Boolean ritual, @Nullable @Param("level") Integer level);

    @Query("SELECT s FROM Spell s WHERE s.range = :range AND (:level IS NULL OR s.level <= :level)")
    List<Spell> findSpellsByRangeAndLevelIsLessThanEqual(@Param("range") String range, @Nullable @Param("level") Integer level);

    @Modifying
    @Query(value = "TRUNCATE TABLE spells RESTART IDENTITY", nativeQuery = true)
    void truncateTable();
}
