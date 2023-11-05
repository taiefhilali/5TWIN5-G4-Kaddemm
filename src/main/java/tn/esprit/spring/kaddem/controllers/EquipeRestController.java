package tn.esprit.spring.kaddem.controllers;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.kaddem.dtos.EquipeDTO;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.services.IEquipeService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/equipe")
public class EquipeRestController {
	IEquipeService equipeService;
	@GetMapping("/retrieve-all-equipes")
	public List<Equipe> getEquipes() {
		return equipeService.retrieveAllEquipes();
	}
	@GetMapping("/retrieve-equipe/{equipe-id}")
	public Equipe retrieveEquipe(@PathVariable("equipe-id") Integer equipeId) {
		return equipeService.retrieveEquipe(equipeId);
	}


	@PostMapping("/add-equipe")
	public Equipe addEquipe(@RequestBody EquipeDTO equipeDTO) {
		Equipe newEquipe = new Equipe();
		newEquipe.setNomEquipe(equipeDTO.getNomEquipe());
		newEquipe.setNiveau(equipeDTO.getNiveau());
		return equipeService.addEquipe(newEquipe);
	}


	@DeleteMapping("/remove-equipe/{equipe-id}")
	public void removeEquipe(@PathVariable("equipe-id") Integer equipeId) {
		equipeService.deleteEquipe(equipeId);
	}

	@PutMapping("/update-equipe")
	public Equipe updateEquipe(@RequestBody EquipeDTO equipeDTO) {
		Equipe existingEquipe = equipeService.retrieveEquipe(equipeDTO.getIdEquipe());
		existingEquipe.setNomEquipe(equipeDTO.getNomEquipe());
		existingEquipe.setNiveau(equipeDTO.getNiveau());
		return equipeService.updateEquipe(existingEquipe);
	}


	@Scheduled(cron="0 0 13 * * *")
	@PutMapping("/faireEvoluerEquipes")
	public void faireEvoluerEquipes() {
		 equipeService.evoluerEquipes() ;
	}
}


