package tn.esprit.spring.kaddem;

import org.junit.jupiter.api.Test;
import java.util.List; // Make sure to import the correct List class

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.services.DepartementServiceImpl;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
 class DepartementunistTests {
    @Autowired
    private DepartementRepository departementRepository;

    @Autowired
    private DepartementServiceImpl departementService;
    @Test
    void testAddDepartement() {
        Departement departementToAdd = new Departement();
        departementToAdd.setNomDepart("departement1");

        Departement addeddepartement = departementService.addDepartement(departementToAdd);

        assertNotNull(addeddepartement.getIdDepart());

        assertEquals(departementToAdd.getNomDepart(), addeddepartement.getNomDepart());
    }
    @Test
    void testUpdateDepartement() {
        Departement departementToUpdate = new Departement();

        departementToUpdate.setNomDepart("departement2");
        Departement saveddepartement = departementRepository.save(departementToUpdate);

        Departement updateddepartement = departementService.updateDepartement(saveddepartement);

        assertNotNull(updateddepartement);
    }
    @Test
    void testRetrieveAllDepartements() {
        List<Departement> result = departementService.retrieveAllDepartements();

        assertNotNull(result);
    }

    @Test
    void testRetrieveDepartement() {
        Departement departement = new Departement();
        departement.setNomDepart("departement2");
        departementRepository.save(departement);

        Departement result = departementService.retrieveDepartement(departement.getIdDepart());

        // Assertions
        assertNotNull(result);
    }
    @Test
    void testRemoveDepartement() {
        Departement departement = new Departement();

        departement.setNomDepart("departement2");

        departementRepository.save(departement);

        departementService.deleteDepartement(departement.getIdDepart());

        Departement removedDepartement = departementRepository.findById(departement.getIdDepart()).orElse(null);

        assertNull(removedDepartement);

}}
