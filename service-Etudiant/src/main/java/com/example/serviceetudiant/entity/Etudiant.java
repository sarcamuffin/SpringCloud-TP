package com.example.serviceetudiant.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Etudiant {
    @Id
    private Long id;
    private String name;
    // getters and setters
}