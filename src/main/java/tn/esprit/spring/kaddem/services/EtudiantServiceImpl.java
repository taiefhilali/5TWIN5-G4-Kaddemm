package tn.esprit.spring.kaddem.services;
import javax.persistence.EntityNotFoundException;


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
		return etudiantRepository.findById(idEtudiant)
				.orElseThrow(() -> new EntityNotFoundException("Etudiant not found with id: " + idEtudiant));
	}


	public void removeEtudiant(Integer idEtudiant){
	Etudiant e=retrieveEtudiant(idEtudiant);
	etudiantRepository.delete(e);
	}

	public void assignEtudiantToDepartement(Integer etudiantId, Integer departementId) {
		Optional<Etudiant> etudiantOptional = etudiantRepository.findById(etudiantId);
		Optional<Departement> departementOptional = departementRepository.findById(departementId);

		if (!etudiantOptional.isPresent()) {
			throw new EntityNotFoundException("Etudiant not found with id: " + etudiantId);
		}

		if (!departementOptional.isPresent()) {
			throw new EntityNotFoundException("Departement not found with id: " + departementId);
		}

		Etudiant etudiant = etudiantOptional.get();
		Departement departement = departementOptional.get();

		etudiant.setDepartement(departement);
		etudiantRepository.save(etudiant);
	}




	@Transactional
	public Etudiant addAndAssignEtudiantToEquipeAndContract(Etudiant e, Integer idContrat, Integer idEquipe) {
		Contrat c = contratRepository.findById(idContrat)
				.orElseThrow(() -> new EntityNotFoundException("Contract not found with id: " + idContrat));
		Equipe eq = equipeRepository.findById(idEquipe)
				.orElseThrow(() -> new EntityNotFoundException("Equipe not found with id: " + idEquipe));

		c.setEtudiant(e);
		eq.getEtudiants().add(e);

		contratRepository.save(c);
		equipeRepository.save(eq);

		return e;
	}



	public 	List<Etudiant> getEtudiantsByDepartement (Integer idDepartement){
return  etudiantRepository.findEtudiantsByDepartement_IdDepart((idDepartement));
	}
}
