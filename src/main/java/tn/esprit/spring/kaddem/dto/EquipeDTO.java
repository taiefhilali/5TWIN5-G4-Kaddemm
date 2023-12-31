package tn.esprit.spring.kaddem.dto;

import tn.esprit.spring.kaddem.entities.Niveau;

public class EquipeDTO {
    private Integer idEquipe;
    private String nomEquipe;
    private Niveau niveau;

    public EquipeDTO() {super();
    }

    public EquipeDTO(Integer idEquipe, String nomEquipe, Niveau niveau) {
        this.idEquipe = idEquipe;
        this.nomEquipe = nomEquipe;
        this.niveau = niveau;
    }
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
