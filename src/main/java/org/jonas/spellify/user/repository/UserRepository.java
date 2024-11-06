package org.jonas.spellify.user.repository;

import org.jonas.spellify.user.model.entity.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<CustomUser, Long> {

}
