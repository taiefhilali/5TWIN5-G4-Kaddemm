package tn.esprit.spring.kaddem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Specialite;
import tn.esprit.spring.kaddem.entities.Universite;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.repositories.UniversiteRepository;
import tn.esprit.spring.kaddem.services.UniversiteServiceImpl;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static tn.esprit.spring.kaddem.services.UniversiteServiceImpl.logger;

class UniversiteServiceImplUnitTest {

    @Mock
    private UniversiteRepository universiteRepository;

    @Mock
    private DepartementRepository departementRepository;

    @InjectMocks
    private UniversiteServiceImpl universiteService;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
 void testupdate()
    {
        Universite u =new Universite("updated1123");
        u.setIdUniv(3);
        when(universiteRepository.findById(1)).thenReturn(Optional.of(u));
        when(universiteRepository.save(Mockito.any(Universite.class))).thenReturn(u);
        logger.info("Attempting to update Universite with ID: {}", u.getNomUniv());

        Universite updatedu;
        updatedu = universiteService.updateUniversite(u);
        logger.info(" updated Universite with ID: {}", updatedu.getNomUniv());

        assertNotNull(updatedu);
        assertEquals(u.getIdUniv(), updatedu.getIdUniv());
        assertEquals(u.getNomUniv(), updatedu.getNomUniv());

    }

}

