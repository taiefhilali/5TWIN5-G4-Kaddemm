package tn.esprit.spring.kaddem.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.kaddem.dtos.EtudiantDTO;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.services.IContratService;
import tn.esprit.spring.kaddem.services.IEquipeService;
import tn.esprit.spring.kaddem.services.IEtudiantService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/etudiant")
public class EtudiantRestController {
	@Autowired
	IEtudiantService etudiantService;
	@Autowired
	IContratService contratService;
	@Autowired
	IEquipeService equipeService;

	@GetMapping("/retrieve-all-etudiants")
	public List<Etudiant> getEtudiants() {
		return etudiantService.retrieveAllEtudiants();

	}
	@GetMapping("/retrieve-etudiant/{etudiant-id}")
	public Etudiant retrieveEtudiant(@PathVariable("etudiant-id") Integer etudiantId) {
		return etudiantService.retrieveEtudiant(etudiantId);
	}

	@PostMapping("/add-etudiant")
	public Etudiant addEtudiant(@RequestBody EtudiantDTO etudiantDTO) {
		Etudiant newEtudiant = new Etudiant();
		newEtudiant.setNomE(etudiantDTO.getNomE());
		newEtudiant.setPrenomE(etudiantDTO.getPrenomE());
		newEtudiant.setOp(etudiantDTO.getOp());
		return etudiantService.addEtudiant(newEtudiant);
	}


	@DeleteMapping("/remove-etudiant/{etudiant-id}")
	public void removeEtudiant(@PathVariable("etudiant-id") Integer etudiantId) {
		etudiantService.removeEtudiant(etudiantId);
	}

	@PutMapping("/update-etudiant")
	public Etudiant updateEtudiant(@RequestBody EtudiantDTO etudiantDTO) {
		Etudiant existingEtudiant = etudiantService.retrieveEtudiant(etudiantDTO.getIdEtudiant());

		existingEtudiant.setNomE(etudiantDTO.getNomE());
		existingEtudiant.setPrenomE(etudiantDTO.getPrenomE());
		existingEtudiant.setOp(etudiantDTO.getOp());

		return etudiantService.updateEtudiant(existingEtudiant);
	}


	@PutMapping(value="/affecter-etudiant-departement/{etudiantId}/{departementId}")
	public void affecterEtudiantToDepartement(@PathVariable("etudiantId") Integer etudiantId, @PathVariable("departementId")Integer departementId){
		etudiantService.assignEtudiantToDepartement(etudiantId, departementId);
    }

	@PostMapping("/add-assign-Etudiant/{idContrat}/{idEquipe}")
	public Etudiant addEtudiantWithEquipeAndContract(@RequestBody EtudiantDTO etudiantDTO, @PathVariable("idContrat") Integer idContrat, @PathVariable("idEquipe") Integer idEquipe) {

		Etudiant newEtudiant = new Etudiant();
		newEtudiant.setNomE(etudiantDTO.getNomE());
		newEtudiant.setPrenomE(etudiantDTO.getPrenomE());
		newEtudiant.setOp(etudiantDTO.getOp());

		Contrat contrat = contratService.retrieveContrat(idContrat);
		Equipe equipe = equipeService.retrieveEquipe(idEquipe);

		newEtudiant.getContrats().add(contrat);
		newEtudiant.getEquipes().add(equipe);

		return etudiantService.addAndAssignEtudiantToEquipeAndContract(newEtudiant, idContrat, idEquipe);
	}


	@GetMapping(value = "/getEtudiantsByDepartement/{idDepartement}")
	public List<Etudiant> getEtudiantsParDepartement(@PathVariable("idDepartement") Integer idDepartement) {

		return etudiantService.getEtudiantsByDepartement(idDepartement);
	}

}


