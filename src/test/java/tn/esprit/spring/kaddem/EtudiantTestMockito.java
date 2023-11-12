package tn.esprit.spring.kaddem;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.entities.Option;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;
import tn.esprit.spring.kaddem.services.EtudiantServiceImpl;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class EtudiantTestMockito {

    @Mock
    private EtudiantRepository etudiantRepository;

    @InjectMocks
    private EtudiantServiceImpl etudiantService;

    private Etudiant etudiant;

    @Before
    public void setup() {
        etudiant = new Etudiant(1, "Doe", "John");
    }

    @Test
    public void testCreateEtudiant() {
        when(etudiantRepository.save(any(Etudiant.class))).thenReturn(etudiant);

        Etudiant created = etudiantService.addEtudiant(etudiant);

        assertNotNull(created);
        assertEquals("Doe", created.getNomE());
    }

    @Test
    public void testGetEtudiantById() {
        when(etudiantRepository.findById(1)).thenReturn(Optional.of(etudiant));

        Etudiant found = etudiantService.retrieveEtudiant(1);

        assertNotNull(found);
        assertEquals("John", found.getPrenomE());
    }

    /* @Test
    public void testUpdateEtudiant() {
        when(etudiantRepository.findById(1)).thenReturn(Optional.of(etudiant));
        when(etudiantRepository.save(any(Etudiant.class))).thenReturn(etudiant);

        etudiant.setNomE("Smith");
        Etudiant updated = etudiantService.updateEtudiant(etudiant);

        assertNotNull(updated);
        assertEquals("Smith", updated.getNomE());
    }

   @Test
    public void testDeleteEtudiant() {
        Integer etudiantId = 1;

        // Assurez-vous que l'étudiant existe avant de tenter de le supprimer.
        when(etudiantRepository.findById(etudiantId)).thenReturn(Optional.of(new Etudiant()));

        // Simuler la suppression.
        doNothing().when(etudiantRepository).deleteById(etudiantId);

        // Appel de la méthode du service pour supprimer l'étudiant.
        etudiantService.removeEtudiant(etudiantId);

        // Vérification que la méthode deleteById a été appelée sur le repository.
        verify(etudiantRepository).deleteById(etudiantId);
    }*/



}
