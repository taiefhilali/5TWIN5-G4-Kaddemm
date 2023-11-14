package tn.esprit.spring.kaddem;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import tn.esprit.spring.kaddem.dtos.EtudiantDTO;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.entities.Option;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;
import tn.esprit.spring.kaddem.services.EtudiantServiceImpl;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
class EtudiantMockitoTest {

    @Mock
    private EtudiantRepository etudiantRepository;

    @InjectMocks
    private EtudiantServiceImpl etudiantService;

    private Etudiant etudiant;



    @Test
     void testConstructorAndGetters() {
        Integer id = 1;
        String nom = "Doe";
        String prenom = "John";
        Option newOption = Option.SE;

        EtudiantDTO etudiantDTO = new EtudiantDTO(id, nom, prenom, newOption.GAMIX);

        assertEquals(id, etudiantDTO.getIdEtudiant());
        assertEquals(nom, etudiantDTO.getNomE());
        assertEquals(prenom, etudiantDTO.getPrenomE());
        assertEquals(newOption.GAMIX, etudiantDTO.getOp());
    }

    @Test
     void testSetters() {
        EtudiantDTO etudiantDTO = new EtudiantDTO();
        Integer newId = 2;
        String newNom = "Smith";
        String newPrenom = "Jane";
        Option newOption = Option.SE;

        etudiantDTO.setIdEtudiant(newId);
        etudiantDTO.setNomE(newNom);
        etudiantDTO.setPrenomE(newPrenom);
        etudiantDTO.setOp(newOption);

        assertEquals(newId, etudiantDTO.getIdEtudiant());
        assertEquals(newNom, etudiantDTO.getNomE());
        assertEquals(newPrenom, etudiantDTO.getPrenomE());
        assertEquals(newOption, etudiantDTO.getOp());
    }



    @org.junit.jupiter.api.Test
    void testSetAndGetOp() {
        EtudiantDTO etudiantDTO = new EtudiantDTO();

        Option option = Option.GAMIX;
        etudiantDTO.setOp(option);

        Option retrievedOption = etudiantDTO.getOp();

        assertEquals(option, retrievedOption);
    }}
