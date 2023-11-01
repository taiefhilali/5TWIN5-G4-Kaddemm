package tn.esprit.spring.kaddem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.kaddem.entities.DetailEquipe;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.entities.Niveau;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;
import tn.esprit.spring.kaddem.services.EquipeServiceImpl;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class EquipeUnitTest {

    @BeforeEach
    public void setUp() {
        equipeRepository.deleteAll();
    }
    @Autowired
    private EquipeRepository equipeRepository;

    @Autowired
    private EquipeServiceImpl equipeService;


    @Test
    public void testAddEquipe() {

        Equipe equipe = new Equipe();
        equipe.setNomEquipe("Test Team");
        equipe.setNiveau(Niveau.JUNIOR);

        Equipe savedEquipe = equipeService.addEquipe(equipe);

        assertNotNull(savedEquipe);
        assertNotNull(savedEquipe.getIdEquipe());
        assertEquals("Test Team", savedEquipe.getNomEquipe());
        assertEquals(Niveau.JUNIOR, savedEquipe.getNiveau());
    }

    @Test
    public void testUpdateEquipe() {

        Equipe equipe = new Equipe();
        equipe.setNomEquipe("Test Update");
        equipe.setNiveau(Niveau.JUNIOR);

        Equipe savedEquipe = equipeRepository.save(equipe);

        savedEquipe.setNiveau(Niveau.SENIOR);

        Equipe updatedEquipe = equipeService.updateEquipe(savedEquipe);

        assertNotNull(updatedEquipe);
    }

    @Test
    public void testDeleteEquipe() {

        Equipe equipe = new Equipe();
        equipe.setNomEquipe("Test Delete");
        equipe.setNiveau(Niveau.JUNIOR);
        Equipe savedEquipe = equipeRepository.save(equipe);

        equipeService.deleteEquipe(savedEquipe.getIdEquipe());

        Equipe deletedEquipe = equipeRepository.findById(savedEquipe.getIdEquipe()).orElse(null);

        assertNull(deletedEquipe);
    }

    @Test
    public void testRetrieveAllEquipes() {

        Equipe equipe1 = new Equipe();
        equipe1.setNomEquipe("TeamRetrieve");
        equipe1.setNiveau(Niveau.JUNIOR);
        equipeRepository.save(equipe1);

        Equipe equipe2 = new Equipe();
        equipe2.setNomEquipe("TeamRetrieve 2");
        equipe2.setNiveau(Niveau.SENIOR);
        equipeRepository.save(equipe2);

        List<Equipe> equipes = equipeService.retrieveAllEquipes();

        assertNotNull(equipes);

        assertEquals(2, equipes.size());
    }

    @Test
    public void testRetrieveEquipe() {

        Equipe equipe = new Equipe();
        equipe.setNomEquipe("Team 3");
        equipe.setNiveau(Niveau.SENIOR);
        equipe = equipeRepository.save(equipe);

        Equipe retrievedEquipe = equipeService.retrieveEquipe(equipe.getIdEquipe());

        assertNotNull(retrievedEquipe);
        assertEquals("Team 3", retrievedEquipe.getNomEquipe());
        assertEquals(Niveau.SENIOR, retrievedEquipe.getNiveau());
    }



}






