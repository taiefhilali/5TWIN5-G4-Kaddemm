package tn.esprit.spring.kaddem;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.Specialite;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.services.DepartementServiceImpl;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
 class DepartemmenistTests {
    @Autowired
    private DepartementRepository departementRepository;

    @Autowired
    private DepartementServiceImpl departementService;


    @Test
    void testadddepartement() {
        Departement departementToAdd = new Departement();
        departementToAdd.setNomDepart("departementinformatique");


        Departement addeddepartement = departementService.addDepartement(departementToAdd);

        assertNotNull(addeddepartement.getIdDepart());
        assertEquals(departementToAdd.getNomDepart(), addeddepartement.getNomDepart());



    }
    @Test
    void testUpdateDepartement() {
        Departement departementToUpdate = new Departement();
        departementToUpdate.setNomDepart("departementinformatique");

        Departement savedDepartement = departementRepository.save(departementToUpdate);

        Departement updatedDepartement = departementService.updateDepartement(savedDepartement);

        assertNotNull(updatedDepartement);
    }

    @Test
    void testRetrieveallDepartement() {
        List<Departement> result = departementService.retrieveAllDepartements();

        assertNotNull(result);
    }

    @Test
    void testRetrieveDepartment() {
        Departement departement = new Departement();
        departement.setNomDepart("departementinformatique");

        departementRepository.save(departement);

        Departement result = departementService.retrieveDepartement(departement.getIdDepart());

        // Assertions
        assertNotNull(result);
    }
    @Test
    void testRemoveDepartement() {
        Departement departement = new Departement();
        departement.setNomDepart("departementinformatique");


        departementRepository.save(departement);

        departementService.deleteDepartement(departement.getIdDepart());

        Departement removedDepartement = departementRepository.findById(departement.getIdDepart()).orElse(null);

        assertNull(removedDepartement);

    }

    @Test
    void testDepartementConstructor() {
        Departement departement = new Departement("Test Departement");
        assertNull(departement.getIdDepart()); // id should be null for a new entity
        assertEquals("Test Departement", departement.getNomDepart());
    }


    @Test
    void testUpdateDepartementController() {
        // Given
        Departement departement = new Departement();
        departement.setNomDepart("Original Name");
        departement = departementRepository.save(departement);

        Departement updatedDepartement = new Departement();
        updatedDepartement.setIdDepart(departement.getIdDepart());
        updatedDepartement.setNomDepart("Updated Name");

        // When
        Departement result = departementService.updateDepartement(updatedDepartement);

        // Then
        assertNotNull(result);
        assertEquals(updatedDepartement.getIdDepart(), result.getIdDepart());
        assertEquals(updatedDepartement.getNomDepart(), result.getNomDepart());
        // Add more assertions based on your specific requirements
    }
    @Test
    void testRemoveDepartementController() {
        // Given
        Departement departement = new Departement();
        departement.setNomDepart("Departement to remove");
        departement = departementRepository.save(departement);

        // When
        departementService.deleteDepartement(departement.getIdDepart());

        // Then
        Departement removedDepartement = departementRepository.findById(departement.getIdDepart()).orElse(null);
        assertNull(removedDepartement);
        // Add more assertions based on your specific requirements
    }
}
