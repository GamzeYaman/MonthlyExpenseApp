package com.app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "Citizen")
public class Citizen {

    @Id
    private String id;

    @Column(name = "CITIZEN_NAME", nullable = false)
    private String citizenName;

    @Column(name = "CITIZEN_SURNAME", nullable = false)
    private String citizenSurname;

    @Column(name = "USERNAME", nullable = false, unique = true)
    private String username;

    @Column(name = "PASSWORD", nullable = false)
    @Size(min = 8, message = "Şifre en az sekiz karakterli olmalıdır.")
    private String password;

    public Citizen() {
        this.setId(UUID.randomUUID().toString());
    }
}
