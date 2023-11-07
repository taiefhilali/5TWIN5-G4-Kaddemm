package tn.esprit.spring.kaddem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.repositories.ContratRepository;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EtudiantServiceImpl implements IEtudiantService{
	@Autowired
	EtudiantRepository etudiantRepository ;
	@Autowired
	ContratRepository contratRepository;
	@Autowired
	EquipeRepository equipeRepository;
	@Autowired
	DepartementRepository departementRepository;
	public List<Etudiant> retrieveAllEtudiants(){
		return (List<Etudiant>) etudiantRepository.findAll();
	}

	public Etudiant addEtudiant (Etudiant e){
		return etudiantRepository.save(e);
	}

	public Etudiant updateEtudiant (Etudiant e){
		return etudiantRepository.save(e);
	}

	public Etudiant retrieveEtudiant(Integer idEtudiant) {
		Optional<Etudiant> etudiantOptional = etudiantRepository.findById(idEtudiant);

		if (etudiantOptional.isPresent()) {
			return etudiantOptional.get();
		} else {
			// Handle the case when Etudiant with the given ID is not found
			// You can throw an exception or return a default value, or take any other appropriate action.
			return null; // Example: returning null
		}
	}


	public void removeEtudiant(Integer idEtudiant){
		Etudiant e=retrieveEtudiant(idEtudiant);
		etudiantRepository.delete(e);
	}

	public void assignEtudiantToDepartement(Integer etudiantId, Integer departementId) {
		Optional<Etudiant> etudiantOptional = etudiantRepository.findById(etudiantId);
		Optional<Departement> departementOptional = departementRepository.findById(departementId);

		if (etudiantOptional.isPresent() && departementOptional.isPresent()) {
			Etudiant etudiant = etudiantOptional.get();
			Departement departement = departementOptional.get();
			etudiant.setDepartement(departement);
			etudiantRepository.save(etudiant);
		} else {
			// Handle the case when either Etudiant or Departement is not found.
			// You can throw an exception or take any other appropriate action.
			// For example, you can log an error message or notify the user.
		}
	}

	@Transactional
	public Etudiant addAndAssignEtudiantToEquipeAndContract(Etudiant e, Integer idContrat, Integer idEquipe) {
		Contrat c = contratRepository.findById(idContrat).orElse(null);
		Equipe eq = equipeRepository.findById(idEquipe).orElse(null);

		if (c != null && eq != null) {
			c.setEtudiant(e);
			eq.getEtudiants().add(e);
			return e;
		} else {
			// Handle the case when either Contrat or Equipe is not found.
			// You can throw an exception or take any other appropriate action.
			// For example, you can log an error message or notify the user.
			return null; // Or return some other value indicating failure.
		}
	}



	public 	List<Etudiant> getEtudiantsByDepartement (Integer idDepartement){
		return  etudiantRepository.findEtudiantsByDepartement_IdDepart((idDepartement));
	}
}