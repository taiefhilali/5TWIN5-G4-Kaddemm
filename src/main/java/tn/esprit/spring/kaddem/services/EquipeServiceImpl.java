package tn.esprit.spring.kaddem.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.entities.Niveau;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@AllArgsConstructor
@Service
public class EquipeServiceImpl implements IEquipeService{
	EquipeRepository equipeRepository;


	public List<Equipe> retrieveAllEquipes(){
	return  (List<Equipe>) equipeRepository.findAll();
	}
	public Equipe addEquipe(Equipe e){
		return (equipeRepository.save(e));
	}

	public  void deleteEquipe(Integer idEquipe){
		Equipe e=retrieveEquipe(idEquipe);
		equipeRepository.delete(e);
	}

	public Equipe retrieveEquipe(Integer equipeId){
		Optional<Equipe> optionalEquipe = equipeRepository.findById(equipeId);
		return optionalEquipe.orElse(null);
	}

	public Equipe updateEquipe(Equipe e){
	return (	equipeRepository.save(e));
	}

	public void evoluerEquipes() {
		List<Equipe> equipes = (List<Equipe>) equipeRepository.findAll();
		for (Equipe equipe : equipes) {
			if (shouldEvoluer(equipe)) {
				evoluerNiveau(equipe);
			}
		}
	}

	private boolean shouldEvoluer(Equipe equipe) {
		if (equipe.getNiveau().equals(Niveau.JUNIOR) || equipe.getNiveau().equals(Niveau.SENIOR)) {
			List<Etudiant> etudiants = (List<Etudiant>) equipe.getEtudiants();
			int nbEtudiantsAvecContratsActifs = countEtudiantsWithActiveContrats(etudiants);
			return nbEtudiantsAvecContratsActifs >= 3;
		}
		return false;
	}

	private int countEtudiantsWithActiveContrats(List<Etudiant> etudiants) {
		int nbEtudiantsAvecContratsActifs = 0;
		for (Etudiant etudiant : etudiants) {
			Set<Contrat> contrats = etudiant.getContrats();
			if (hasActiveContrat(contrats)) {
				nbEtudiantsAvecContratsActifs++;
			}
			if (nbEtudiantsAvecContratsActifs >= 3) {
				break;
			}
		}
		return nbEtudiantsAvecContratsActifs;
	}

	private boolean hasActiveContrat(Set<Contrat> contrats) {
		Date dateSysteme = new Date();
		for (Contrat contrat : contrats) {
			if (contrat.getArchive() != null && !contrat.getArchive()) {
				long differenceInTime = dateSysteme.getTime() - contrat.getDateFinContrat().getTime();
				long differenceInYears = (differenceInTime / (1000L * 60 * 60 * 24 * 365));
				if (differenceInYears > 1) {
					return true;
				}
			}
		}
		return false;
	}

	private void evoluerNiveau(Equipe equipe) {
		if (equipe.getNiveau().equals(Niveau.JUNIOR)) {
			equipe.setNiveau(Niveau.SENIOR);
		} else if (equipe.getNiveau().equals(Niveau.SENIOR)) {
			equipe.setNiveau(Niveau.EXPERT);
		}
		equipeRepository.save(equipe);
	}

}