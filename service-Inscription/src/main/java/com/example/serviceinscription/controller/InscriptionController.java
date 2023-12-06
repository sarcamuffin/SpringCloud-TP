package com.example.serviceinscription.controller;

import com.example.serviceinscription.service.InscriptionService;
import com.example.serviceinscription.service.InscriptionService2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InscriptionController {

    private final InscriptionService inscriptionService;

    private final InscriptionService2 inscriptionService2;


    public InscriptionController(InscriptionService inscriptionService, InscriptionService2 inscriptionService2) {
        this.inscriptionService = inscriptionService;
        this.inscriptionService2 = inscriptionService2;
    }

    /*@PostMapping("/inscriptions/{idEleve}/{idCours}")
    public ResponseEntity<String> addInscription(@PathVariable("idEleve") Long idEleve,
                                                 @PathVariable("idCours") Long idCours){
        try {
            inscriptionService.assignElevetoCours(idEleve,idCours);
            return ResponseEntity.ok("Inscription ajoutée avec succès");
        } catch (Exception e) {

            // Retournez une réponse d'erreur
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur interne du serveur");
        }
    }*/

    @PostMapping("/inscriptions/{idEleve}/{idCours}")
    public ResponseEntity<String> addInscription2(@PathVariable("idEleve") Long idEleve,
                                                  @PathVariable("idCours") Long idCours){
        try {
            inscriptionService.assignElevetoCours(idEleve,idCours);
            return ResponseEntity.ok("Inscription ajoutée avec succès");
        } catch (Exception e) {

            // Retournez une réponse d'erreur
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur interne du serveur");
        }
    }
    @GetMapping("/inscriptions")
    public ResponseEntity<?> getAllInscriptions(){
        try {
            return ResponseEntity.ok(inscriptionService2.getAllInscriptions());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur interne du serveur");
        }
    }

}
