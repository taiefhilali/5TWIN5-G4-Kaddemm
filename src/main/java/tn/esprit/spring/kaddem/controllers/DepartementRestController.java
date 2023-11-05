package tn.esprit.spring.kaddem.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.kaddem.dtos.DepartementDTO;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.services.IDepartementService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/departement")
public class DepartementRestController {
	IDepartementService departementService;
	@GetMapping("/retrieve-all-departements")
	public List<Departement> getDepartements() {
		return departementService.retrieveAllDepartements();
	}
	@GetMapping("/retrieve-departement/{departement-id}")
	public Departement retrieveDepartement(@PathVariable("departement-id") Integer departementId) {
		return departementService.retrieveDepartement(departementId);
	}

	@PostMapping("/add-departement")
	public Departement addDepartement(@RequestBody DepartementDTO departementDTO) {
		Departement newDepartement = new Departement();
		newDepartement.setNomDepart(departementDTO.getNomDepart());
		return departementService.addDepartement(newDepartement);
	}


	@DeleteMapping("/remove-departement/{departement-id}")
	public void removeDepartement(@PathVariable("departement-id") Integer departementId) {
		departementService.deleteDepartement(departementId);
	}

	@PutMapping("/update-departement")
	public Departement updateDepartement(@RequestBody DepartementDTO departementDTO) {
		Departement existingDepartement = departementService.retrieveDepartement(departementDTO.getIdDepart()); // Assuming you have a method to get a department by ID
		if (existingDepartement == null) {
			return null;
		}
		existingDepartement.setNomDepart(departementDTO.getNomDepart());
		return departementService.updateDepartement(existingDepartement);
	}

}


