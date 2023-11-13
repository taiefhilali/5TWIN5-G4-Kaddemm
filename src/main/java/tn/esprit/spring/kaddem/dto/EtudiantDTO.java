package tn.esprit.spring.kaddem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.esprit.spring.kaddem.entities.Option;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EtudiantDTO {
    private Integer idEtudiant;
    private String nomE;
    private String prenomE;
    private Option op;





}
