package com.example.serviceinscription.service;

import com.example.serviceinscription.DTO.CoursDTO;
import com.example.serviceinscription.DTO.EtudiantDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("test")
public interface FeignApiClient {

    @GetMapping("/inscription/{id}")
    String getInscriptionById(@PathVariable("id") Long id);
    @GetMapping("/etudiants/{id}")
    EtudiantDTO getEtudiantById(@PathVariable("id") Long id);
    @GetMapping("/cours/{id}")
    CoursDTO getCoursById(@PathVariable("id") Long id);
}

