package tn.esprit.spring.kaddem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.kaddem.dto.ContratDTO;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.entities.Specialite;
import tn.esprit.spring.kaddem.repositories.ContratRepository;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class ContratServiceImpl implements IContratService{
	@Autowired
	ContratRepository contratRepository;
	@Autowired
	EtudiantRepository etudiantRepository;
	public List<Contrat> retrieveAllContrats(){
		return  contratRepository.findAll();
	}

	public Contrat updateContrat(ContratDTO ce) {
		// Conversion from DTO to entity should be implemented here
		// Assuming you have a method to convert DTO to entity
		Contrat contratToUpdate = convertDtoToEntity(ce);
		return contratRepository.save(contratToUpdate);
	}

	private Contrat convertDtoToEntity(ContratDTO contratDTO) {
		Contrat contrat = new Contrat();
		contrat.setIdContrat(contratDTO.getIdContrat());
		contrat.setDateDebutContrat(contratDTO.getDateDebutContrat());
		contrat.setDateFinContrat(contratDTO.getDateFinContrat());
		contrat.setSpecialite(contratDTO.getSpecialite());
		contrat.setArchive(contratDTO.getArchive());
		contrat.setMontantContrat(contratDTO.getMontantContrat());

	
		return contrat;
	}


	

	public  Contrat addContrat (Contrat ce){
		return contratRepository.save(ce);
	}

	public Contrat retrieveContrat (Integer  idContrat){
		return contratRepository.findById(idContrat).orElse(null);
	}

	public  void removeContrat(Integer idContrat){
		Contrat c=retrieveContrat(idContrat);
		contratRepository.delete(c);
	}



	public Contrat affectContratToEtudiant (Integer idContrat, String nomE, String prenomE){
		Etudiant e=etudiantRepository.findByNomEAndPrenomE(nomE, prenomE);
		Contrat ce=contratRepository.findByIdContrat(idContrat);
		Set<Contrat> contrats= e.getContrats();
		Integer nbContratssActifs=0;
		if (!contrats.isEmpty()) {
			for (Contrat contrat : contrats) {
				if (((!contrat.isArchive()) &&contrat.isArchive()))  {
					nbContratssActifs++;
				}
			}
		}
		if (nbContratssActifs<=4){
			ce.setEtudiant(e);
			contratRepository.save(ce);}
		return ce;
	}
	public 	Integer nbContratsValides(Date startDate, Date endDate){
		return contratRepository.getnbContratsValides(startDate, endDate);
	}

	public void retrieveAndUpdateStatusContrat(){
		List<Contrat>contrats=contratRepository.findAll();
		List<Contrat>contrats15j=null;
		List<Contrat>contratsAarchiver=null;
		for (Contrat contrat : contrats) {
			Date dateSysteme = new Date();
			if (!contrat.isArchive()) {
				long differenceInTime = dateSysteme.getTime() - contrat.getDateFinContrat().getTime();
				long differenceInDays = (differenceInTime / (1000 * 60 * 60 * 24)) % 365;
				if (differenceInDays==15){
					contrats15j.add(contrat);
					log.info(" Contrat : " + contrat);
				}
				if (differenceInDays==0) {
					contratsAarchiver.add(contrat);
					contrat.setArchive(true);
					contratRepository.save(contrat);
				}
			}
		}
	}
	public float getChiffreAffaireEntreDeuxDates(Date startDate, Date endDate){
		float differenceInTime = endDate.getTime() - (float)startDate.getTime();
		float differenceInDays = (differenceInTime / (1000 * 60 * 60 * 24)) % 365;
		float differenceInmonths =differenceInDays/30;
		List<Contrat> contrats=contratRepository.findAll();
		float chiffreAffaireEntreDeuxDates=0;
		for (Contrat contrat : contrats) {
			if (contrat.getSpecialite()== Specialite.IA){
				chiffreAffaireEntreDeuxDates+=(differenceInmonths*300);
			} else if (contrat.getSpecialite()== Specialite.CLOUD) {
				chiffreAffaireEntreDeuxDates+=(differenceInmonths*400);
			}
			else if (contrat.getSpecialite()== Specialite.RESEAUX) {
				chiffreAffaireEntreDeuxDates+=(differenceInmonths*350);
			}
			else
			{
				chiffreAffaireEntreDeuxDates+=(differenceInmonths*450);
			}
		}
		return chiffreAffaireEntreDeuxDates;


	}


}
