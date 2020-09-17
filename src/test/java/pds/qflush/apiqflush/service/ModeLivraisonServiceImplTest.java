package pds.qflush.apiqflush.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import pds.qflush.apiqflush.model.ModeLivraison;
import pds.qflush.apiqflush.repository.ModeLivraisonRepository;

public class ModeLivraisonServiceImplTest {

	@Mock //Mockito mock Object.
    private ModeLivraisonRepository modeLivraisonRepository;

    @InjectMocks //setups up controller and injects mock objects into it.
    private ModeLivraisonServiceImpl modeLivraisonService;

    /**
     * Initiate mock object
     **/
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Unit test for save() method from ModeLivraisonServiceImpl
     * when ModeLivraisonService.save(ModeLivraison) is called we verify that save(ModeLivraison) is invoked by ModeLivraisonRepository
     */
   /* @Test
    public void saveTest(){
        ModeLivraison modeLivraison = mock(ModeLivraison.class);
        modeLivraisonService.save(modeLivraison);
        verify(modeLivraisonRepository).save(modeLivraison);
    }*/

    /**
     * Unit test for finaAll() method from ModeLivraisonServiceImpl
     * when ModeLivraisonService.findAll() is called we verify that findAllTest() is invoked by ModeLivraisonRepository
     */
    @Test
    public void findAllTest(){
        modeLivraisonService.findAll();
        verify(modeLivraisonRepository).findAll();
    }

    /**
     * Unit test for findAll() method from ModeLivraisonServiceImpl
     *  we are comparing the results of two different access to the method findAll()
     */
    @Test
    public void findAllTest2() {

        List<ModeLivraison> modeLivraisonList = modeLivraisonRepository.findAll();
        List<ModeLivraison> modeLivraisonListTest = modeLivraisonService.findAll();
        Assert.assertEquals(modeLivraisonList,modeLivraisonListTest);
    }
}
