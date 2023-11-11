package tn.esprit.spring.kaddem.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.*;


@SuppressWarnings("SpellCheckingInspection")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Etudiant implements Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer idEtudiant;
    @NonNull
    private String nomE;
    @NonNull
    private String prenomE;
    @Enumerated(EnumType.STRING)
    @NonNull
    private Option op;
    @OneToMany(mappedBy="etudiant", cascade = CascadeType.ALL)
    @JsonIgnore
    @NonNull
    private Set<Contrat> contrats;
    @ManyToOne
    @JsonIgnore
    @NonNull
    private Departement departement;
    @ManyToMany(mappedBy="etudiants")

    @JsonIgnore
    @NonNull
    private List<Equipe> equipes ;

     public Etudiant(String nomE, String prenomE, Option op, Set<Contrat> contrats, Departement departement, List<Equipe> equipes) {
        this.nomE = nomE;
        this.prenomE = prenomE;
        this.op = op;
        this.contrats = contrats;
        this.departement = departement;
        this.equipes = equipes;
    }

}
