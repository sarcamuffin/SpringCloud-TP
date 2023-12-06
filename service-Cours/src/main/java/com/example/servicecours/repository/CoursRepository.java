package com.example.servicecours.repository;

import com.example.servicecours.entity.Cours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "cours")
public interface CoursRepository extends JpaRepository<Cours, Long>{
}
