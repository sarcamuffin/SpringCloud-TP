package com.example.serviceetudiant.repository;

import com.example.serviceetudiant.entity.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "etudiants")
public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
}