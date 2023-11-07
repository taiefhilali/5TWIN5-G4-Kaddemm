package tn.esprit.spring.kaddem;

import java.util.Arrays; // Import Arrays

import org.junit.jupiter.api.Test;
import java.util.List; // Make sure to import the correct List class
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.Specialite;
import tn.esprit.spring.kaddem.repositories.ContratRepository;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.services.ContratServiceImpl;
import tn.esprit.spring.kaddem.services.DepartementServiceImpl;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class DepartementServiceImplIntegrationTest {

    @Mock
    private DepartementRepository departementRepository;

    @InjectMocks
    private DepartementServiceImpl departementService;

    @Test
    void testAddDepartement() {
        Departement departementToAdd = new Departement();
        departementToAdd.setNomDepart("departement1");


        when(departementRepository.save(Mockito.any(Departement.class))).thenReturn(departementToAdd);

        Departement addeddepartement = departementService.addDepartement(departementToAdd);

        assertNull(addeddepartement.getIdDepart());
        assertEquals(departementToAdd.getNomDepart(), addeddepartement.getNomDepart());

    }

    @Test
    void testUpdateDepartement() {
        Departement departementToUpdate = new Departement();
        departementToUpdate.setIdDepart(1); // Set a valid ID

        departementToUpdate.setNomDepart("departement2");



        when(departementRepository.findById(1)).thenReturn(Optional.of(departementToUpdate));
        when(departementRepository.save(Mockito.any(Departement.class))).thenReturn(departementToUpdate);

        Departement updateddepartement = departementService.updateDepartement(departementToUpdate);

        assertNotNull(updateddepartement);
    }

    @Test
    void testRetrieveAllDepartements() {
        when(departementRepository.findAll()).thenReturn(Arrays.asList(new Departement(), new Departement())); // Mock data
        List<Departement> result = departementService.retrieveAllDepartements();

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void testRetrieveDepartement() {
        Departement departement = new Departement();
        departement.setIdDepart(1); // Set a valid ID
        when(departementRepository.findById(1)).thenReturn(Optional.of(departement));

        Departement result = departementService.retrieveDepartement(1);

        assertNotNull(result);
    }

    @Test
    void testRemoveDepartement() {
        Departement departement = new Departement();
        departement.setIdDepart(1); // Set a valid ID

        when(departementRepository.findById(1)).thenReturn(Optional.of(departement));

        departementService.retrieveDepartement(1);

        assertNull(departementService.retrieveDepartement(1));
    }
}
