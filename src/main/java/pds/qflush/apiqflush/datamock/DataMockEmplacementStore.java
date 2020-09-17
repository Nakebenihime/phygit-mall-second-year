package pds.qflush.apiqflush.datamock;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import pds.qflush.apiqflush.model.Location;
import pds.qflush.apiqflush.model.LocationStore;
import pds.qflush.apiqflush.model.Store;
import pds.qflush.apiqflush.service.LocationServiceImpl;
import pds.qflush.apiqflush.service.LocationStoreServiceImpl;
import pds.qflush.apiqflush.service.StoreServiceImpl;

public class DataMockEmplacementStore {

	public static void generateEmplacementStore(LocationStoreServiceImpl locationStoreService, LocationServiceImpl locationService, StoreServiceImpl storeService) {
		List<Store> listStoreRestaurant = storeService.findByType("Restaurant");
		List<Store> listStoreBoutique = storeService.findByType("Boutique");
		List<Store> listStoreCinema = storeService.findByType("Cinema");
		
		List<Location> listLocationRestaurant = locationService.findByAile(3);
		List<Location> listLocationCinema = locationService.findByAile(4);

		List<Location> listLocationBoutique = locationService.findByAileBoutiques();


		Calendar date = Calendar.getInstance();
		Date dateDebut = date.getTime();
		date.add(Calendar.YEAR, 1);
		Date dateFin = date.getTime();
		
		for (int i = 0 ; i <listStoreRestaurant.size() && i < listLocationRestaurant.size() ; i++) {
			LocationStore locationStore = new LocationStore();
			locationStore.setDatedebut(dateDebut);
			locationStore.setDateFin(dateFin);
			locationStore.setLocation(listLocationRestaurant.get(i));
			locationStore.setStore(listStoreRestaurant.get(i));
			locationStore.setLocationStoreId(listLocationRestaurant.get(i).getIdLocation());
			locationStoreService.save(locationStore);
		}
		
		for (int i = 0 ; i <listStoreBoutique.size() && i < listLocationBoutique.size() ; i++) {
			LocationStore locationStore = new LocationStore();
			locationStore.setDatedebut(dateDebut);
			locationStore.setDateFin(dateFin);
			locationStore.setLocation(listLocationBoutique.get(i));
			locationStore.setStore(listStoreBoutique.get(i));
			locationStore.setLocationStoreId(listLocationBoutique.get(i).getIdLocation());
			locationStoreService.save(locationStore);
		}
		
		for (int i = 0 ; i <listStoreCinema.size() && i < listLocationCinema.size() ; i++) {
			LocationStore locationStore = new LocationStore();
			locationStore.setDatedebut(dateDebut);
			locationStore.setDateFin(dateFin);
			locationStore.setLocation(listLocationCinema.get(i));
			locationStore.setStore(listStoreCinema.get(i));
			locationStore.setLocationStoreId(listLocationCinema.get(i).getIdLocation());
			locationStoreService.save(locationStore);
		}
		
		
		
		
	}
	
}
