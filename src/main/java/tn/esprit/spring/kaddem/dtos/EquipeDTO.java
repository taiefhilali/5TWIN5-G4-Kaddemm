package tn.esprit.spring.kaddem.dtos;

import tn.esprit.spring.kaddem.entities.Niveau;

public class EquipeDTO {
    private Integer idEquipe;
    private String nomEquipe;
    private Niveau niveau;

    // Constructors, getters, and setters

    public EquipeDTO() {
    }

    public EquipeDTO(Integer idEquipe, String nomEquipe, Niveau niveau) {
        this.idEquipe = idEquipe;
        this.nomEquipe = nomEquipe;
        this.niveau = niveau;
    }

    // Getters and setters
    public Integer getIdEquipe() {
        return idEquipe;
    }

    public void setIdEquipe(Integer idEquipe) {
        this.idEquipe = idEquipe;
    }

    public String getNomEquipe() {
        return nomEquipe;
    }

    public void setNomEquipe(String nomEquipe) {
        this.nomEquipe = nomEquipe;
    }

    public Niveau getNiveau() {
        return niveau;
    }

    public void setNiveau(Niveau niveau) {
        this.niveau = niveau;
    }

}