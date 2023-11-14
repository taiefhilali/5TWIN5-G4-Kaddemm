package tn.esprit.spring.kaddem;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.services.DepartementServiceImpl;

import java.util.List;

import static org.junit.Assert.*;

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
        assertNull(departement.getIdDepart());
        assertEquals("Test Departement", departement.getNomDepart());
    }


    @Test
    void testUpdateDepartementController() {
        Departement departement = new Departement();
        departement.setNomDepart("Original Name");
        departement = departementRepository.save(departement);

        Departement updatedDepartement = new Departement();
        updatedDepartement.setIdDepart(departement.getIdDepart());
        updatedDepartement.setNomDepart("Updated Name");

        Departement result = departementService.updateDepartement(updatedDepartement);

        assertNotNull(result);
        assertEquals(updatedDepartement.getIdDepart(), result.getIdDepart());
        assertEquals(updatedDepartement.getNomDepart(), result.getNomDepart());
    }
    @Test
    void testRemoveDepartementController() {
        Departement departement = new Departement();
        departement.setNomDepart("Departement to remove");
        departement = departementRepository.save(departement);

        departementService.deleteDepartement(departement.getIdDepart());

        Departement removedDepartement = departementRepository.findById(departement.getIdDepart()).orElse(null);
        assertNull(removedDepartement);
    }}
