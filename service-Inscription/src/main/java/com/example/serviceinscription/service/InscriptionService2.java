package com.example.serviceinscription.service;

import com.example.serviceinscription.DTO.CoursDTO;
import com.example.serviceinscription.DTO.EtudiantDTO;
import com.example.serviceinscription.entity.Inscription;
import com.example.serviceinscription.repository.InscriptionRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class InscriptionService2 {

    private final WebClient webClient1;
    private final WebClient webClient2;

    private final InscriptionRepository inscriptionRepository;


    public InscriptionService2(InscriptionRepository inscriptionRepository) {
        this.inscriptionRepository = inscriptionRepository;
        this.webClient1 = WebClient.create("http://localhost:8088");
        this.webClient2 = WebClient.create("http://localhost:8089");
    }

    public Inscription assignElevetoCours2(Long idEleve, Long idCours) throws JsonProcessingException {

        String responseEleve = webClient1.get()
                .uri("/etudiants/" + idEleve.toString())
                .retrieve()
                .bodyToMono(String.class)
                .block();

        String responseCours = webClient2.get()
                .uri("/cours/" + idCours.toString())
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return stringToInscription(responseEleve, responseCours, inscriptionRepository);
    }

    static Inscription stringToInscription(String responseEleve, String responseCours, InscriptionRepository inscriptionRepository) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        EtudiantDTO newEleveDTO = objectMapper.readValue(responseEleve, EtudiantDTO.class);
        CoursDTO newCoursDTO = objectMapper.readValue(responseCours, CoursDTO.class);

        Inscription newInscription = Inscription.builder()
                .firstName(newEleveDTO.getFirstName())
                .lastName(newEleveDTO.getLastName())
                .coursName(newCoursDTO.getNomCours())
                .build();

        return inscriptionRepository.save(newInscription);
    }

    public Object getAllInscriptions() {
        return inscriptionRepository.findAll();
    }
}
