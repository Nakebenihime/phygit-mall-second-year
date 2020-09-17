package pds.qflush.apiqflush.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

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
import pds.qflush.apiqflush.repository.LocationStoreRepository;
import pds.qflush.apiqflush.repository.StoreRepository;

@RunWith(SpringRunner.class)

public class CourseServiceImplTest {

	


	@InjectMocks
	private CourseServiceImpl courseService;
	
	
	@Mock
	private LocationStoreRepository locationStoreRepository;
	
	List<LocationStore> listLocationStoreWithLimit = new ArrayList<LocationStore>();
	List<LocationStore> listLocationStoreByIdWithLimit = new ArrayList<LocationStore>();
	List<LocationStore> listRestaurantByIdWithLimit = new ArrayList<LocationStore>();
	LocationStore cinemaLocation = new LocationStore();
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		LocationStore locationStore = new LocationStore();
		Store s = new Store();
		s.setStoreId((long) 1);
		s.setName("Peugeot");
		locationStore.setStore(s);
		locationStore.setLocationStoreId((long)1);

		LocationStore locationStore2 = new LocationStore();
		Store s2 = new Store();
		s2.setStoreId((long) 2);
		s2.setName("Test");
		locationStore2.setStore(s2);
		locationStore2.setLocationStoreId((long)2);


		//Adding a restaurant list
		LocationStore locationStore3 = new LocationStore();
		Store s3 = new Store();
		s3.setStoreId((long) 3);
		s3.setName("Pizza Hut");
		locationStore3.setStore(s3);
		locationStore3.setLocationStoreId((long)3);

		//Adding a cinema
		Store s4 = new Store();
		s4.setStoreId((long) 4);
		s4.setName("Gaumont");
		cinemaLocation.setStore(s4);
		cinemaLocation.setLocationStoreId((long)4);


		listLocationStoreWithLimit.add(locationStore);
		listLocationStoreWithLimit.add(locationStore2);
		listRestaurantByIdWithLimit.add(locationStore3);
		listLocationStoreByIdWithLimit.add(locationStore);

		
	}

	
	
	@Test
	public void findStoreRandomWithLimitTest(){
		int time = 20;
		when(locationStoreRepository.findRandomWithLimit(time/20)).thenReturn(listLocationStoreWithLimit);
		List<LocationStore> found = courseService.findStoreRandomWithLimit(time/20);
		assertThat(found.size())
				.isEqualTo(listLocationStoreWithLimit.size());
	}

	@Test
	public void findStoreByIdWithLimit() {
		int time = 20;
		List<String> s = new ArrayList<String>();
		s.add("2");
		when(locationStoreRepository.findByIdWithLimit(time/20,s)).thenReturn(listLocationStoreByIdWithLimit);
		List<LocationStore> found = courseService.findStoreByIdWithLimit(time/20, s);
		assertTrue((found.size() == (listLocationStoreByIdWithLimit.size())) && (Contains(found,s)));
		
	}
	
	public boolean Contains(List<LocationStore> found, List<String> stores) {
		for (int i = 0; i<stores.size() ; i++) {
			boolean test = false;
			for (int j = 0; j<found.size(); j++) {
				if(stores.get(i).equals((found.get(j).getStore().getStoreId()).toString())) {
					test = true;
					break;
				}
			}
			if(test == false) {
				return false;
			}
		}
		return true;
	}

	@Test
	public void findOneRestaurantTest() {
		int courseDuration = 20;
		String stores = "";
		Boolean restaurant = true;
		Boolean cinema = false;
		when(locationStoreRepository.findOneRestaurant(1)).thenReturn(listRestaurantByIdWithLimit);

		List<LocationStore> found = courseService.findStoreByCriteria(courseDuration, stores, restaurant, cinema);
		assertTrue((found.size() == (listRestaurantByIdWithLimit.size())));

	}
    @Test
	public void findCinemaTest() {
		int courseDuration = 40;
		String stores = "";
		Boolean restaurant = false;
		Boolean cinema = true;
		when(locationStoreRepository.findCinema()).thenReturn(cinemaLocation);

		List<LocationStore> found = courseService.findStoreByCriteria(courseDuration, stores, restaurant, cinema);
		assertTrue((found.size() == 1));

	}
//    @Test
//    public void findCinemaAndRestaurantTest() {
//        int courseDuration = 20;
//        String stores = "";
//        Boolean restaurant = true;
//        Boolean cinema = true;
//        when(locationStoreRepository.findCinema()).thenReturn(cinemaLocation);
//        when(locationStoreRepository.findOneRestaurant(1)).thenReturn(listRestaurantByIdWithLimit);
//
//        List<LocationStore> found = courseService.findStoreByCriteria(courseDuration, stores, restaurant, cinema);
//        assertTrue((found.size() == (listRestaurantByIdWithLimit.size()+1)));
//
//    }

}
