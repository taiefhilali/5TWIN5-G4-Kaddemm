package tn.esprit.spring.kaddem;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.entities.Niveau;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;
import tn.esprit.spring.kaddem.services.EquipeServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest

public class EquipeServiceImplIntegrationTest {
        @Mock
        private EquipeRepository equipeRepository;

        @InjectMocks
        private EquipeServiceImpl equipeService;


        @Test
        public void testAddEquipe() {

            Equipe equipe = new Equipe();
            equipe.setNomEquipe("Test Team");
            equipe.setNiveau(Niveau.JUNIOR);

            when(equipeRepository.save(Mockito.any(Equipe.class))).thenReturn(equipe);

            Equipe savedEquipe = equipeService.addEquipe(equipe);

            assertNotNull(savedEquipe);
            assertNull(savedEquipe.getIdEquipe());
            assertEquals("Test Team", savedEquipe.getNomEquipe());
            assertEquals(Niveau.JUNIOR, savedEquipe.getNiveau());
        }

        @Test
        public void testUpdateEquipe() {
            Equipe equipe = new Equipe();
            equipe.setNomEquipe("Test Update");
            equipe.setNiveau(Niveau.JUNIOR);

            when(equipeRepository.save(Mockito.any(Equipe.class))).thenReturn(equipe);

            Equipe savedEquipe = equipeService.addEquipe(equipe);

            when(equipeRepository.save(Mockito.any(Equipe.class))).thenReturn(savedEquipe);

            savedEquipe.setNiveau(Niveau.SENIOR);

            Equipe updatedEquipe = equipeService.updateEquipe(savedEquipe);

            assertNotNull(updatedEquipe);
        }




        @Test
        public void testRetrieveAllEquipes() {

            Equipe equipe1 = new Equipe();
            equipe1.setNomEquipe("Team 1");
            equipe1.setNiveau(Niveau.JUNIOR);

            Equipe equipe2 = new Equipe();
            equipe2.setNomEquipe("Team 2");
            equipe2.setNiveau(Niveau.SENIOR);

            List<Equipe> equipeList = new ArrayList<>();
            equipeList.add(equipe1);
            equipeList.add(equipe2);

            // Mock the behavior of the repository
            when(equipeRepository.findAll()).thenReturn(equipeList);

            List<Equipe> equipes = equipeService.retrieveAllEquipes();

            assertNotNull(equipes);
            assertEquals(2, equipes.size());
        }

        @Test
        public void testRetrieveEquipe() {

            Equipe equipe = new Equipe();
            equipe.setNomEquipe("Team 3");
            equipe.setNiveau(Niveau.SENIOR);

            when(equipeRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(equipe));

            Equipe retrievedEquipe = equipeService.retrieveEquipe(1);

            assertNotNull(retrievedEquipe);
            assertEquals("Team 3", retrievedEquipe.getNomEquipe());
            assertEquals(Niveau.SENIOR, retrievedEquipe.getNiveau());
        }






}
