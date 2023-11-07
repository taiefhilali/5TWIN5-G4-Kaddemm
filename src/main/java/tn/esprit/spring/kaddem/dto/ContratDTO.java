package tn.esprit.spring.kaddem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.esprit.spring.kaddem.entities.Specialite;

import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContratDTO {
    Integer idContrat;
    Date dateDebutContrat;
    Date dateFinContrat;
    Specialite specialite;
    Boolean archive;
    Integer montantContrat;

}


