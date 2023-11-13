package tn.esprit.spring.kaddem.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.services.IDepartementService;
import tn.esprit.spring.kaddem.dto.DepartementDTO;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/departement")
public class DepartementRestController {
	IDepartementService departementService;
	// http://localhost:8089/Kaddem/departement/retrieve-all-departements
	@GetMapping("/retrieve-all-departements")
	public List<Departement> getDepartements() {
		return departementService.retrieveAllDepartements();
	}
	// http://localhost:8089/Kaddem/departement/retrieve-departement/8
	@GetMapping("/retrieve-departement/{departement-id}")
	public Departement retrieveDepartement(@PathVariable("departement-id") Integer departementId) {
		return departementService.retrieveDepartement(departementId);
	}


	@GetMapping("/{id}")
	public DepartementDTO getDepartement(@PathVariable("id") Integer id) {
		Departement departement = departementService.getDepartementById(id);
		return convertToDepartementDTO(departement);
	}
	private DepartementDTO convertToDepartementDTO(Departement departement) {
		DepartementDTO departementDTO = new DepartementDTO();
		departementDTO.setIdDepart(departement.getIdDepart());
		departementDTO.setNomDepart(departement.getNomDepart());
		// Set other properties as needed
		return departementDTO;
	}

	// http://localhost:8089/Kaddem/departement/remove-departement/1
	@DeleteMapping("/remove-departement/{departement-id}")
	public void removeDepartement(@PathVariable("departement-id") Integer departementId) {
		departementService.deleteDepartement(departementId);
	}

	// http://localhost:8089/Kaddem/departement/update-departement
	@PutMapping("/update-departement")
	public DepartementDTO updateDepartement(@RequestBody DepartementDTO departementDTO) {
		// Convert DTO to entity if necessary
		Departement departement = new Departement();
		departement.setNomDepart(departementDTO.getNomDepart());
		departement.setIdDepart(departementDTO.getIdDepart());

		// Call the service method to update the departement
		departement = departementService.updateDepartement(departement);

		// Convert the updated entity back to DTO
		DepartementDTO updatedDTO = new DepartementDTO();
		updatedDTO.setNomDepart(departement.getNomDepart());
		updatedDTO.setIdDepart(departement.getIdDepart());

		return updatedDTO;
	}

}


