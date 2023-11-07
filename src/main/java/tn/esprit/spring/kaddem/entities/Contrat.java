package tn.esprit.spring.kaddem.entities;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Contrat implements Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer idContrat;
    @Temporal(TemporalType.DATE)
    private Date dateDebutContrat;
    @Temporal(TemporalType.DATE)
    private Date dateFinContrat;
    @Enumerated(EnumType.STRING)
    private Specialite specialite;
    private Boolean archive;
    private Integer montantContrat;
    @ManyToOne(cascade = CascadeType.ALL)
    private Etudiant etudiant;



    public Contrat(Date dateDebutContrat, Date dateFinContrat, Specialite specialite, Boolean archive,
                   Integer montantContrat) {
        super();
        this.dateDebutContrat = dateDebutContrat;
        this.dateFinContrat = dateFinContrat;
        this.specialite = specialite;
        this.archive = archive;
        this.montantContrat = montantContrat;
    }


    public boolean isArchive() {
        return archive;
    }

}
