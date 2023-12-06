package com.example.serviceinscription.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class CoursDTO {

    private String nomCours;

    @JsonProperty("nomCours")
    public String getNomCours() {
        return nomCours;
    }

    public void setNomCours(String nomCours) {
        this.nomCours = nomCours;
    }

    public CoursDTO(String nomCours) {
        this.nomCours = nomCours;
    }

    public CoursDTO(){}
}
