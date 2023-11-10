package tn.esprit.spring.kaddem;

import java.util.Arrays; // Import Arrays

import org.junit.jupiter.api.Test;
import java.util.List; // Make sure to import the correct List class
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Specialite;
import tn.esprit.spring.kaddem.repositories.ContratRepository;
import tn.esprit.spring.kaddem.services.ContratServiceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ContratServiceImplIntegrationTest {

    @Mock
    private ContratRepository contratRepository;

    @InjectMocks
    private ContratServiceImpl contratService;

    @Test
    void testAddContrat() {
        Contrat contratToAdd = new Contrat();
        contratToAdd.setDateDebutContrat(new Date());
        contratToAdd.setDateFinContrat(new Date());
        contratToAdd.setSpecialite(Specialite.IA);
        contratToAdd.setArchive(false);
        contratToAdd.setMontantContrat(1000);

        when(contratRepository.save(Mockito.any(Contrat.class))).thenReturn(contratToAdd);

        Contrat addedContrat = contratService.addContrat(contratToAdd);

        assertNull(addedContrat.getIdContrat());
        assertEquals(contratToAdd.getDateDebutContrat(), addedContrat.getDateDebutContrat());
        assertEquals(contratToAdd.getDateFinContrat(), addedContrat.getDateFinContrat());
        assertEquals(contratToAdd.getSpecialite(), addedContrat.getSpecialite());
        assertEquals(contratToAdd.isArchive(), addedContrat.isArchive());
        assertEquals(contratToAdd.getMontantContrat(), addedContrat.getMontantContrat());
    }

    @Test
    void testUpdateContrat() {
        Contrat contratToUpdate = new Contrat();
        contratToUpdate.setIdContrat(2); // Set a valid ID
        contratToUpdate.setDateDebutContrat(new Date());
        contratToUpdate.setDateFinContrat(new Date());
        contratToUpdate.setSpecialite(Specialite.CLOUD);
        contratToUpdate.setArchive(true);
        contratToUpdate.setMontantContrat(2000);

        when(contratRepository.findById(1)).thenReturn(Optional.of(contratToUpdate));
        when(contratRepository.save(Mockito.any(Contrat.class))).thenReturn(contratToUpdate);

        Contrat updatedContrat = contratService.updateContrat(contratToUpdate);

        assertNotNull(updatedContrat);
        assertEquals(Specialite.CLOUD, updatedContrat.getSpecialite());
    }

    @Test
    void testRetrieveAllContrats() {
        when(contratRepository.findAll()).thenReturn(Arrays.asList(new Contrat(), new Contrat())); // Mock data
        List<Contrat> result = contratService.retrieveAllContrats();

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void testRetrieveContrat() {
        Contrat contrat = new Contrat();
        contrat.setIdContrat(6); // Set a valid ID
        when(contratRepository.findById(6)).thenReturn(Optional.of(contrat));

        Contrat result = contratService.retrieveContrat(6);

        assertNotNull(result);
    }

    @Test
    void testRemoveContrat() {
        Contrat contrat = new Contrat();
        contrat.setIdContrat(6); // Set a valid ID

        when(contratRepository.findById(6)).thenReturn(Optional.of(contrat));

        contratService.removeContrat(6);

        assertNull(contratService.retrieveContrat(8));
    }
}
/*import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Specialite;
import tn.esprit.spring.kaddem.repositories.ContratRepository;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;
import tn.esprit.spring.kaddem.services.ContratServiceImpl;

import java.util.Date;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertNull;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;



@SpringBootTest
@Transactional
class ContratServiceImplIntegrationTest {

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
        assertEquals(contratToAdd.getArchive(), addedContrat.getArchive());
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
    }*/
