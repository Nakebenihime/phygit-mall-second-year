package pds.qflush.apiqflush.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;
import pds.qflush.apiqflush.model.*;
import pds.qflush.apiqflush.repository.CampagneRepository;
import pds.qflush.apiqflush.repository.CommandLineRepository;
import pds.qflush.apiqflush.repository.CustomerProfilRepository;
import pds.qflush.apiqflush.repository.SuggestionRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class CampagneServiceImplTest {

    @InjectMocks
    CampagneServiceImpl campagneService;
    
    @InjectMocks
    CustomerProfil customerprofil;
    
    @Mock
    CommandLineRepository commandLineRepo;
    
    @Mock
    ProfilServiceImpl profilService;

    @Mock
    CampagneRepository campagneRepository;
    
    @Mock
	SuggestionRepository suggestionRepo;
    
    @Mock
    CustomerProfilServiceImpl customerProfilService;
    
    

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);

        Evenement evenement = new Evenement();
        evenement.setNomEvenement("event1");

        Campagne campagne = new Campagne();
        campagne.setNomCampagne("campagne");
        campagne.setDescription("description");
        campagne.setEvenement(evenement);

        Profil profil = new Profil();
        profil.setNomProfil("UNO");

        Profil profil1 = new Profil();
        profil.setNomProfil("DOS");

        Category category = new Category();
        category.setName("SPORT");

        List<Category> categoryList = new ArrayList<>();
        categoryList.add(category);

        List<Profil> profilList = new ArrayList<>();
        profilList.add(profil);
        profilList.add(profil1);

        CampagneProfilCategory campagneProfilCategory = new CampagneProfilCategory();
        campagneProfilCategory.setCampagne(campagne);
        campagneProfilCategory.setEvenement(evenement);
        campagneProfilCategory.setProfil(profilList);
        campagneProfilCategory.setCategory(categoryList);

    }

    @Test
    public void save_returnsNewCampagne(){
        //tell the stub to return a new customer object.
        when(campagneRepository.save((any(Campagne.class)))).thenReturn(new Campagne());
        Campagne campagne = new Campagne();
        // asserts that the object is not null.
        assertNotNull(campagneService.save(campagne));
        // verify that save() method has been invoked.
        verify(campagneRepository).save(any(Campagne.class));
        // verify that save() method has been invoked once.
        verify(campagneRepository,times(1)).save(campagne);
    }
    @Test
    public void save_throwsException(){
        try{
            when(campagneRepository.save((any(Campagne.class)))).thenThrow(RuntimeException.class);
            Campagne campagne = new Campagne();
            campagneService.save(campagne);
        } catch(Exception ex){
            assertNotNull(ex);
        }
    }
    
    
    
    @Test
    public void findSuggestion(){
    	CampagneProfilCategory cpc = new CampagneProfilCategory();
    	Profil profil = new Profil();
    	profil.setNomProfil("Geek");
    	Category categorie = new Category();
    	categorie.setName("telephonie");
    	List<Category> categories = new ArrayList<>();
    	categories.add(categorie);
    	List<Profil> profils = new ArrayList<>();
    	profils.add(profil);
    	categorie.setProfil(profil);
    	cpc.setProfil(profils);
    	cpc.setCategory(categories);
    	Campagne campagne = new Campagne();
    	campagne.setNomCampagne("campagne");
    	campagne.setSingleCheckboxField(true);
    	cpc.setCampagne(campagne);
    	List<Suggestion> suggestions = suggestionRepo.findAll();
    	campagneService.find(cpc);
    	List<Suggestion> suggestions1 = suggestionRepo.findAll();
    	assertEquals(suggestions1.size(), suggestions.size());
    	
   }
}