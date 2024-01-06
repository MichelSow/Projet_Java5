package be.helb.misow.Service;

import be.helb.misow.Dao.SponsorRepository;
import be.helb.misow.Model.Sponsor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class SponsorServiceTest {

    @Mock
    private SponsorRepository sponsorRepository;

    @InjectMocks
    private SponsorService sponsorService;

    // Test pour obtenir tous les sponsors
    @Test
    public void getAllSponsorsTest() {
        when(sponsorRepository.findAll()).thenReturn(Arrays.asList(new Sponsor("Quick"), new Sponsor("Audi")));

        List<Sponsor> sponsors = sponsorService.getAllSponsors();

        assertEquals(2, sponsors.size());
        verify(sponsorRepository, times(1)).findAll();
    }

    // Test pour obtenir un sponsor par son ID
    @Test
    void testGetSponsorById() {
        Long id = 1L;
        Sponsor sponsor = new Sponsor("Quick");
        sponsor.setId(id);

        when(sponsorRepository.findById(id)).thenReturn(Optional.of(sponsor));

        Sponsor found = sponsorService.getSponsorById(id).orElse(null);

        assertNotNull(found);
        assertEquals(sponsor.getName(), found.getName());
    }

    // Test pour ajouter un sponsor
    @Test
    public void addSponsorTest() {
        Sponsor sponsorToAdd = new Sponsor("pepsi");
        when(sponsorRepository.save(sponsorToAdd)).thenReturn(sponsorToAdd);

        Sponsor addedSponsor = sponsorService.addSponsor(sponsorToAdd);

        assertNotNull(addedSponsor);
        assertEquals("pepsi", addedSponsor.getName());
        verify(sponsorRepository, times(1)).save(sponsorToAdd);
    }

    // Test pour supprimer un sponsor
    @Test
    public void deleteSponsorTest() {
        Long id = 1L;
        doNothing().when(sponsorRepository).deleteById(id);

        sponsorService.deleteSponsorById(id);

        verify(sponsorRepository, times(1)).deleteById(id);
    }


}
