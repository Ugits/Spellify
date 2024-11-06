package org.jonas.spellify.user.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class CustomUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;

    private String password;


}
