package tn.esprit.spring.kaddem.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.kaddem.dto.EtudiantDTO;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.services.IEtudiantService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/etudiant")
public class EtudiantRestController {
	@Autowired
	IEtudiantService etudiantService;
	// http://localhost:8089/Kaddem/etudiant/retrieve-all-etudiants
	@GetMapping("/retrieve-all-etudiants")
	public List<Etudiant> getEtudiants() {
		return etudiantService.retrieveAllEtudiants();
	}
	// http://localhost:8089/Kaddem/etudiant/retrieve-etudiant/8
	@GetMapping("/retrieve-etudiant/{etudiant-id}")
	public Etudiant retrieveEtudiant(@PathVariable("etudiant-id") Integer etudiantId) {
		return etudiantService.retrieveEtudiant(etudiantId);
	}

	// http://localhost:8089/Kaddem/etudiant/add-etudiant
	@PostMapping("/add-etudiant")

	public EtudiantDTO addEtudiant(@RequestBody EtudiantDTO etudiantDTO) {
		Etudiant etudiant = convertToEtudiant(etudiantDTO);
		etudiant = etudiantService.addEtudiant(etudiant);
		return convertToEtudiantDTO(etudiant);
	}

	private Etudiant convertToEtudiant(EtudiantDTO etudiantDTO) {
		Etudiant etudiant = new Etudiant();
		etudiant.setNomE(etudiantDTO.getNomE());
		etudiant.setPrenomE(etudiantDTO.getPrenomE());
		etudiant.setOp(etudiantDTO.getOp());
		// Set other properties as needed
		return etudiant;
	}

	private EtudiantDTO convertToEtudiantDTO(Etudiant etudiant) {
		EtudiantDTO etudiantDTO = new EtudiantDTO();
		etudiantDTO.setIdEtudiant(etudiant.getIdEtudiant());
		etudiantDTO.setNomE(etudiant.getNomE());
		etudiantDTO.setPrenomE(etudiant.getPrenomE());
		etudiantDTO.setOp(etudiant.getOp());
		// Set other properties as needed
		return etudiantDTO;
	}

	// http://localhost:8089/Kaddem/etudiant/remove-etudiant/1
	@DeleteMapping("/remove-etudiant/{etudiant-id}")
	public void removeEtudiant(@PathVariable("etudiant-id") Integer etudiantId) {
		etudiantService.removeEtudiant(etudiantId);
	}

	// http://localhost:8089/Kaddem/etudiant/update-etudiant
	@PutMapping("/update-etudiant")

	public EtudiantDTO updateEtudiant(@RequestBody EtudiantDTO etudiantDTO) {
		Etudiant etudiant = convertToEtudiant(etudiantDTO);
		etudiant = etudiantService.updateEtudiant(etudiant);
		return convertToEtudiantDTO(etudiant);
	}

	//@PutMapping("/affecter-etudiant-departement")
	@PutMapping(value="/affecter-etudiant-departement/{etudiantId}/{departementId}")
	public void affecterEtudiantToDepartement(@PathVariable("etudiantId") Integer etudiantId, @PathVariable("departementId")Integer departementId){
		etudiantService.assignEtudiantToDepartement(etudiantId, departementId);
	}
	//addAndAssignEtudiantToEquipeAndContract(Etudiant e, Integer idContrat, Integer idEquipe)
	/* Ajouter un étudiant tout en lui affectant un contrat et une équipe */
	@PostMapping("/add-assign-Etudiant/{idContrat}/{idEquipe}")
	@ResponseBody
	public Etudiant addEtudiantWithEquipeAndContract(@RequestBody EtudiantDTO etudiantDTO,
													 @PathVariable("idContrat") Integer idContrat,
													 @PathVariable("idEquipe") Integer idEquipe) {
		// Create and map an Etudiant entity based on the DTO and other parameters
		Etudiant etudiant = new Etudiant();
		etudiant.setNomE(etudiantDTO.getNomE());
		etudiant.setPrenomE(etudiantDTO.getPrenomE());
		// Set other relevant fields

		// Call your service method and pass the DTO values
		return etudiantService.addAndAssignEtudiantToEquipeAndContract(etudiant, idContrat, idEquipe);
	}



	@GetMapping(value = "/getEtudiantsByDepartement/{idDepartement}")
	public List<Etudiant> getEtudiantsParDepartement(@PathVariable("idDepartement") Integer idDepartement) {

		return etudiantService.getEtudiantsByDepartement(idDepartement);
	}

}