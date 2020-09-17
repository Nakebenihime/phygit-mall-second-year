package pds.qflush.apiqflush.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import pds.qflush.apiqflush.model.ModeLivraison;
import pds.qflush.apiqflush.model.TarificationLivraison;
import pds.qflush.apiqflush.repository.ModeLivraisonRepository;
import pds.qflush.apiqflush.repository.TarificationLivraisonRepository;

public class TarificationServiceImplTest {

	@Mock //Mockito mock Object.
    private TarificationLivraisonRepository tarificationLivraisonRepository;
	
	@Mock
	private ModeLivraisonRepository modeLivraisonRepository;

    @InjectMocks //setups up controller and injects mock objects into it.
    private TarificationLivraisonServiceImpl tarificationLivraisonService;

    /**
     * Initiate mock object
     **/
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Unit test for save() method from tarificationLivraisonServiceImpl
     * when tarificationLivraisonService.save(tarificationLivraison) is called we verify that save(tarificationLivraison) is invoked by tarificationLivraisonRepository
     */
    @Test
    public void saveTest(){
        TarificationLivraison tarificationLivraison = mock(TarificationLivraison.class);
        tarificationLivraisonService.save(tarificationLivraison);
        verify(tarificationLivraisonRepository).save(tarificationLivraison);
    }

    /**
     * Unit test for finaAll() method from tarificationLivraisonServiceImpl
     * when tarificationLivraisonService.findAll() is called we verify that findAllTest() is invoked by tarificationLivraisonRepository
     */
    @Test
    public void findAllTest(){
        tarificationLivraisonService.findAll();
        verify(tarificationLivraisonRepository).findAll();
    }

    /**
     * Unit test for findAll() method from tarificationLivraisonServiceImpl
     *  we are comparing the results of two different access to the method findAll()
     */
    @Test
    public void findAllTest2() {

        List<TarificationLivraison> tarificationLivraisonList = tarificationLivraisonRepository.findAll();
        List<TarificationLivraison> tarificationLivraisonListTest = tarificationLivraisonService.findAll();
        Assert.assertEquals(tarificationLivraisonList,tarificationLivraisonListTest);
    }
    
    @Test
    public void getFilteredModeLivraison() {
    	//tester le cas où on ne met rien dans le formulaire de recherche
    	
        List<TarificationLivraison> tarificationLivraisonList = tarificationLivraisonRepository.findAll();
        List<TarificationLivraison> recherche = tarificationLivraisonService.getFilteredModeLivraison(tarificationLivraisonList, 200, 70, "");
        Assert.assertEquals(tarificationLivraisonList, recherche);
    	
    }

    @Test
    public void getFilteredModeLivraison1() {
    	// s'assurer que tous les libellés affichés correcpondent aux libellé qu'on voulait trouver 
    	
        List<TarificationLivraison> tarificationLivraisonList = tarificationLivraisonRepository.findAll();
        List<TarificationLivraison> recherche = tarificationLivraisonService.getFilteredModeLivraison(tarificationLivraisonList, 200, 70, "Test");
        for (int i =0; i< recherche.size(); i++) {
        	Assert.assertEquals("Test", recherche.get(i).getModelivraison().getLibelle());
        } 	
    }
    
    @Test
    public void getFilteredModeLivraison2() {
    	// s'assurer que le nombre de modes affichés soit correcte
    	
    	// initialisation de mode et tarif livraison avec les données qu'on va filtrer
    	ModeLivraison m = new ModeLivraison();
    	TarificationLivraison t = new TarificationLivraison();
    	m.setLibelle("Test");
    	m.setDureeMaxlivraison(2);
    	t.setModelivraison(m);
    	t.setPrix(50);
    	//System.out.println("duree max à partir de t" + t.getPrix());
    	
    	ModeLivraison m2 = new ModeLivraison();
    	TarificationLivraison t2 = new TarificationLivraison();
    	m2.setLibelle("Test");
    	m2.setDureeMaxlivraison(1);
    	t2.setModelivraison(m2);
    	t2.setPrix(40);
    	
    	// initialisation du mode qui ne sera pas affiché 
    	ModeLivraison m3 = new ModeLivraison();
    	TarificationLivraison t3 = new TarificationLivraison();
    	m3.setLibelle("Test");
    	m3.setDureeMaxlivraison(1);
    	t3.setModelivraison(m3);
    	t3.setPrix(100);
    	
    	 List<TarificationLivraison> tarificationLivraisonList = new ArrayList<TarificationLivraison>();
    	 tarificationLivraisonList.add(t);
    	 tarificationLivraisonList.add(t2);
    	 tarificationLivraisonList.add(t3);

    	 //System.out.println("blaaaaaa"+tarificationLivraisonList.get(1).getPrix());
         List<TarificationLivraison> recherche = tarificationLivraisonService.getFilteredModeLivraison(tarificationLivraisonList, 50, 2, "Test");
         System.out.println(recherche.size());
         Assert.assertEquals(2, recherche.size());
    	
    	
    	
    }
    
    @Test
    public void getFilteredModeLivraison3() {
    	// s'assurer que si on rentre des informations inexistantes il n'y a pas de résultat
    	
    	// initialisation de mode et tarif livraison avec les données qu'on va filtrer
    	ModeLivraison m = new ModeLivraison();
    	TarificationLivraison t = new TarificationLivraison();
    	m.setLibelle("Test");
    	m.setDureeMaxlivraison(2);
    	t.setModelivraison(m);
    	t.setPrix(50);
    	
    	ModeLivraison m2 = new ModeLivraison();
    	TarificationLivraison t2 = new TarificationLivraison();
    	m2.setLibelle("Test");
    	m2.setDureeMaxlivraison(1);
    	t2.setModelivraison(m2);
    	t2.setPrix(40);
    	
    	ModeLivraison m3 = new ModeLivraison();
    	TarificationLivraison t3 = new TarificationLivraison();
    	m3.setLibelle("Test");
    	m3.setDureeMaxlivraison(1);
    	t3.setModelivraison(m3);
    	t3.setPrix(100);
    	
    	 List<TarificationLivraison> tarificationLivraisonList = new ArrayList<TarificationLivraison>();
    	 tarificationLivraisonList.add(t);
    	 tarificationLivraisonList.add(t2);
    	 tarificationLivraisonList.add(t3);

         List<TarificationLivraison> recherche = tarificationLivraisonService.getFilteredModeLivraison(tarificationLivraisonList, 0, 0, "bla");
         System.out.println(recherche.size());
         Assert.assertEquals(0, recherche.size());
    	
    	
    	
    }
    
}
