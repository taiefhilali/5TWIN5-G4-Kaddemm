package tn.esprit.spring.kaddem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.spring.kaddem.entities.Universite;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.repositories.UniversiteRepository;
import tn.esprit.spring.kaddem.services.UniversiteServiceImpl;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UniversiteServiceImplIntegrationTest {

    @Autowired
    private UniversiteRepository universiteRepository;

    @Autowired
    private DepartementRepository departementRepository;

    @Autowired
    private UniversiteServiceImpl universiteService;

    @Test
    void testAddUniversite() {
        Universite universiteToAdd = new Universite("Université Test 3");

        Universite addedUniversite = universiteService.addUniversite(universiteToAdd);

        assertNotNull(addedUniversite.getIdUniv());
        assertEquals(universiteToAdd.getNomUniv(), addedUniversite.getNomUniv());
    }

}
