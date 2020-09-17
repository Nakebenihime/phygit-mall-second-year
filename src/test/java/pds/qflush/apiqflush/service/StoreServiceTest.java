package pds.qflush.apiqflush.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;
import pds.qflush.apiqflush.model.Horaire;
import pds.qflush.apiqflush.model.LocationStore;
import pds.qflush.apiqflush.model.Store;
import pds.qflush.apiqflush.repository.HoraireRepository;
import pds.qflush.apiqflush.repository.StoreRepository;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class StoreServiceTest {



	@InjectMocks
	private StoreServiceImpl storeService;
	@InjectMocks
	private HoraireServiceImpl horaireService;
	@Mock
	private StoreRepository storeRepository;
	@Mock
	private HoraireRepository horaireRepository;
	List<Store> listNomStore=new ArrayList<Store>();
	List<Store> listStoreMardi=new ArrayList<Store>();


	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		Store store = new Store("TestNom","description","0101010101",true,new LocationStore(),null);
		Store store2 = new Store("TestNom","description","0101010101",true,new LocationStore(),null);
		List<Horaire> lstHoraire= new ArrayList<>();
		Horaire horaire = new Horaire();
		horaire.setJour("Mardi");
		lstHoraire.add(horaire);
		Store storeMardi = new Store("TestNom","description","0101010101",true,new LocationStore(),lstHoraire);
		horaire.setStore(storeMardi);



		listNomStore.add(store);
		listNomStore.add(store2);
		listStoreMardi.add(storeMardi);

	}

	@Test
	public void saveTest() {
		Store store = mock(Store.class);
		storeService.save(store);
		verify(storeRepository).save(store);

	}

	@Test
	public void searchStoreByName(){
		when(storeRepository.findByNameIgnoreCaseContaining("TestNom")).thenReturn(listNomStore);
		String name = "TestNom";
		List<Store> found = storeService.findByName(name);

		assertThat(found.size())
				.isEqualTo(2);
	}
	@Test
	public void searchStoreByJour(){
		when(horaireRepository.findByJour("Mardi")).thenReturn(listStoreMardi);
		String day = "Mardi";
		List<Store> found = horaireService.findByJour("Mardi");

		assertThat(found.size())
				.isEqualTo(1);
	}
}
