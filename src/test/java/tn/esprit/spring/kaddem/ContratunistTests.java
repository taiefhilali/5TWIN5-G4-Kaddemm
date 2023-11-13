package tn.esprit.spring.kaddem;

import java.text.SimpleDateFormat;
import java.util.Arrays; // Import Arrays

import org.junit.jupiter.api.Test;
import java.util.List; // Make sure to import the correct List class
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import tn.esprit.spring.kaddem.dto.EtudiantDTO;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.entities.Option;
import tn.esprit.spring.kaddem.entities.Specialite;
import tn.esprit.spring.kaddem.repositories.ContratRepository;
import tn.esprit.spring.kaddem.services.ContratServiceImpl;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@SpringBootTest
@Transactional
@Configuration
 class ContratunistTests {
    @Autowired
    private ContratRepository contratRepository;

    @Autowired
    private ContratServiceImpl contratService;
    @Test
    void testAddContrat() {
        Contrat contratToAdd = new Contrat();
        contratToAdd.setDateDebutContrat(new Date());
        contratToAdd.setDateFinContrat(new Date());
        contratToAdd.setSpecialite(Specialite.IA);
        contratToAdd.setArchive(false);
        contratToAdd.setMontantContrat(1000);

        Contrat addedContrat = contratService.addContrat(contratToAdd);

        assertNotNull(addedContrat.getIdContrat());
        assertEquals(contratToAdd.getDateDebutContrat(), addedContrat.getDateDebutContrat());
        assertEquals(contratToAdd.getDateFinContrat(), addedContrat.getDateFinContrat());
        assertEquals(contratToAdd.getSpecialite(), addedContrat.getSpecialite());
        assertEquals(contratToAdd.isArchive(), addedContrat.isArchive());
        assertEquals(contratToAdd.getMontantContrat(), addedContrat.getMontantContrat());
    }
    @Test
    void testUpdateContrat() {
        Contrat contratToUpdate = new Contrat();
        contratToUpdate.setDateDebutContrat(new Date());
        contratToUpdate.setDateFinContrat(new Date());
        contratToUpdate.setSpecialite(Specialite.CLOUD);
        contratToUpdate.setArchive(true);
        contratToUpdate.setMontantContrat(1500);
        Contrat savedContrat = contratRepository.save(contratToUpdate);
        savedContrat.setSpecialite(Specialite.IA);

        Contrat updatedContrat = contratService.updateContrat(savedContrat);

        assertNotNull(updatedContrat);
    }
    @Test
    void testRetrieveAllContrats() {
        List<Contrat> result = contratService.retrieveAllContrats();

        assertNotNull(result);
    }

    @Test
    void testRetrieveContrat() {
        Contrat contrat = new Contrat();
        contrat.setDateDebutContrat(new Date());
        contrat.setDateFinContrat(new Date());
        contrat.setSpecialite(Specialite.RESEAUX);
        contrat.setArchive(false);
        contrat.setMontantContrat(1200);
        contratRepository.save(contrat);

        Contrat result = contratService.retrieveContrat(contrat.getIdContrat());

        // Assertions
        assertNotNull(result);
    }
    @Test
    void testRemoveContrat() {
        Contrat contrat = new Contrat();
        contrat.setDateDebutContrat(new Date());
        contrat.setDateFinContrat(new Date());
        contrat.setSpecialite(Specialite.IA);
        contrat.setArchive(true);
        contrat.setMontantContrat(500);

        contratRepository.save(contrat);

        contratService.removeContrat(contrat.getIdContrat());

        Contrat removedContrat = contratRepository.findById(contrat.getIdContrat()).orElse(null);

        assertNull(removedContrat);

}



}
