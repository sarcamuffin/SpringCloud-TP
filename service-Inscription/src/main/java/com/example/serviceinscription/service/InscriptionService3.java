package com.example.serviceinscription.service;

import com.example.serviceinscription.DTO.CoursDTO;
import com.example.serviceinscription.DTO.EtudiantDTO;
import com.example.serviceinscription.entity.Inscription;
import com.example.serviceinscription.repository.InscriptionRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.stereotype.Service;

@EnableFeignClients
@Service
public class InscriptionService3 {

    private final FeignApiClient feignApiClient;
    private final InscriptionRepository inscriptionRepository;

    @Autowired
    public InscriptionService3(FeignApiClient feignApiClient, InscriptionRepository inscriptionRepository) {
        this.feignApiClient = feignApiClient;
        this.inscriptionRepository = inscriptionRepository;
    }

    public Inscription assignElevetoCours3(Long idEleve, Long idCours) throws JsonProcessingException {

        // Use FeignApiClient to get information about the student
        String responseEleve = String.valueOf(feignApiClient.getEtudiantById(idEleve));

        // Use FeignApiClient to get information about the course
        String responseCours = String.valueOf(feignApiClient.getCoursById(idCours));

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
