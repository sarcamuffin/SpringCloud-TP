package com.example.serviceinscription.service;

import com.example.serviceinscription.entity.Inscription;
import com.example.serviceinscription.repository.InscriptionRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static com.example.serviceinscription.service.InscriptionService2.stringToInscription;

@Service
public class InscriptionService {

    RestTemplate restTemplate = new RestTemplate();
    private final InscriptionRepository inscriptionRepository;

    public InscriptionService(InscriptionRepository inscriptionRepository) {
        this.inscriptionRepository = inscriptionRepository;
    }

    public Inscription assignElevetoCours(Long idEleve, Long idCours) throws JsonProcessingException {

        String urlEleve = "http://localhost:8088/etudiants";
        String urlCours = "http://localhost:8089/cours";

        String newEleveString = restTemplate.getForObject(urlEleve + idEleve.toString(), String.class);
        String newCoursString = restTemplate.getForObject(urlCours + idCours.toString(), String.class);

        return stringToInscription(newEleveString, newCoursString, inscriptionRepository);
    }
}