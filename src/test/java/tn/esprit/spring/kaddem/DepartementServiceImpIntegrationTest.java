package tn.esprit.spring.kaddem;


import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.Specialite;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.services.DepartementNotFoundException;
import tn.esprit.spring.kaddem.services.DepartementServiceImpl;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest

 class DepartementServiceImpIntegrationTest {

    @Mock
    private DepartementRepository departementRepository;

    @InjectMocks
    private DepartementServiceImpl departementService;


    @Test
    void testadddepartement() {
        Departement departementToAdd = new Departement();
        departementToAdd.setNomDepart("departementinformatique");

        when(departementRepository.save(Mockito.any(Departement.class))).thenReturn(departementToAdd);

        Departement addeddepartement = departementService.addDepartement(departementToAdd);

        assertNull(addeddepartement.getIdDepart());
        assertEquals(departementToAdd.getNomDepart(), addeddepartement.getNomDepart());

    }


    @Test
    void testUpdateDepartement() {
        Departement departementToUpdate = new Departement();
        departementToUpdate.setIdDepart(1); // Set a valid ID
        departementToUpdate.setNomDepart("departementinformatique");


        when(departementRepository.findById(1)).thenReturn(Optional.of(departementToUpdate));
        when(departementRepository.save(Mockito.any(Departement.class))).thenReturn(departementToUpdate);

        Departement updatedDepartement = departementService.updateDepartement(departementToUpdate);

        assertNotNull(updatedDepartement);
    }



    @Test
    void testRetrieveallDepartement() {
        when(departementRepository.findAll()).thenReturn(Arrays.asList(new Departement(), new Departement())); // Mock data

        List<Departement> result = departementService.retrieveAllDepartements();

        assertNotNull(result);
        assertEquals(2, result.size());

    }
    @Test
    void testRetrieveDepartment() {
        Departement departement = new Departement();
        departement.setIdDepart(6); // Set a valid ID
        when(departementRepository.findById(6)).thenReturn(Optional.of(departement));

        Departement result = departementService.retrieveDepartement(6);

        assertNotNull(result);
    }


    @Test
    void testRemoveDepartement() {
        Departement departement = new Departement();
        departement.setIdDepart(2); // Set a valid ID

        when(departementRepository.findById(2)).thenReturn(Optional.of(departement));

        // No exception is expected, and the method should run without throwing DepartementNotFoundException
        assertDoesNotThrow(() -> departementService.deleteDepartement(2));
    }



}
