package pds.qflush.apiqflush.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pds.qflush.apiqflush.model.Location;
import pds.qflush.apiqflush.model.LocationStore;
import pds.qflush.apiqflush.repository.LocationRepository;
import pds.qflush.apiqflush.repository.LocationStoreRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * Course service implementation
 */
@Service
public class CourseServiceImpl {


    @Autowired
    private LocationStoreRepository locationStoreRepository;

    @Autowired
    private LocationRepository locationRepository;


    /**
     * This method is used to retrieve a list of stores which are located in the mall.
     * Constraints are set in the parameters of the method.
     * @param courseDuration : define the amount of minutes a customer want to spend in the mall.
     * @param stores : define the stores which have to be included in answer
     * @return a list of selected stores
     */
     public List<LocationStore> findStoreByCriteria(int courseDuration,String stores, boolean restaurant, boolean cinema){

         // Initialization of the maximum number of stores to retrieve.
         int storeVisitDuration = 20;
         String[] storesValues = stores.split(",");
//         List<Location> storeLocation = locationRepository.findStoresWithList(Arrays.asList(storesValues));
//         for (int i = 0; i<storeLocation.size(); i++) {
//        	 Location location = storeLocation.get(i);
//        	 storeVisitDuration += location.getVisitTime();
//         }

         int maxStores = courseDuration/storeVisitDuration;
         System.out.println(courseDuration);
         if (maxStores == 0) {
        	 maxStores = 5;
         }
         for(int i = 0; i < storesValues.length; i++) {
        	 System.out.println(storesValues[i]);
         }

         // Initialization of the return list variable
         List<LocationStore> storesList = new ArrayList<LocationStore>();

         // #1 Retrieve a list which include the stores selected by the user
         // And update the maximum number of stores to retrieve.
//         storesList = findStoreByIdWithLimit(maxStores, Arrays.asList(storesValues));
         maxStores = maxStores - storesList.size();
         System.out.println(maxStores + " MAXQTORES " + storesList.size());
         List<String> listStoreChoose = Arrays.asList(storesValues);
//         System.out.println(findStoreNoCommandByProfil(1,5).size());
         // Final step : if maxStores > 0 add some random stores
         if(storesValues.length > 0) {
        	 if(maxStores < listStoreChoose.size()) {
            	 maxStores = listStoreChoose.size();
        	 }
        	 storesList.addAll(findByIdWithLimit(maxStores,listStoreChoose));
         }
         System.out.println(maxStores + " MAXSTOREEE ");
         maxStores = maxStores - storesList.size();
         System.out.println(maxStores + " MAXS STOTRES 2 " + storesList.size());
         if(maxStores < 0) {
        	 maxStores = 0;
         }
         System.out.println(maxStores);
         List<String> id_store = new ArrayList<String>();
         for (int i = 0; i< storesList.size(); i++) {
        	 id_store.add(String.valueOf(storesList.get(i).getStore().getStoreId()));
         }
    	 storesList.addAll(findStoreByProfil(96,maxStores,id_store));
    	 maxStores = maxStores - storesList.size();
    	 if(maxStores < 0) {
    		 maxStores = 0;
    	 }
         storesList.addAll(findStoreNoCommand(1,maxStores));
         // list that contains the id stores of the pre-set shops

         System.out.println(storesList.size() + " STORE LIST SIZE " + maxStores);
         if (storesList.size() < maxStores) {
        	 maxStores = maxStores - storesList.size();
        	 System.out.println(storesList.size() + " SIZE DE LA STORE LIST APRES PROFIL");
        	 //             storesList.addAll(findByIdWithLimitContainsNoStore(maxStores,id_store));
         }
    	 //             storesList.addAll(findByIdWithLimitContainsNoStore(maxStores,id_store));

         System.out.println(restaurant + "  restaurant " + cinema + "   cinema");
         if (restaurant) {
        	 storesList.addAll(findOneRestaurant(1));
         }

         if(cinema) {
        	 storesList.add(findCinema());
         }

         Collections.sort(storesList, new Comparator<LocationStore>(){
        	    public int compare(LocationStore s1, LocationStore s2) {
        	    	if (s1.getLocation().getAile() == s2.getLocation().getAile()) {
        	    		return ((s1.getLocation().getIdLocation())).compareTo(s2.getLocation().getIdLocation());
        	        }
    	    		return Integer.valueOf((s1.getLocation().getAile())).compareTo(s2.getLocation().getAile());

        	    }
        	});


         return storesList;
    }


     public List<LocationStore> findByIdWithLimit(int maxStores, List<String> stores) {
    	 return locationStoreRepository.findByIdWithLimit(maxStores, stores);
     }

    /**
     * This method call the LocationStore repository in order to retrieve a store list which includes the stores from the parameter
     * @param maxStores define the maximum number of stores to retrieve.
     * @param stores define the stores list asked by the user
     * @return list of locationStore
     */
    public List<LocationStore> findStoreByIdWithLimit(int maxStores,List<String> stores){
         return locationStoreRepository.findByIdWithLimit(maxStores,stores);
    }

    /**
     * This method call the LocationStore repository in order to retrieve a list of random stores,
     * the number of stores is limited by maxStores parameter
     * @param maxStores define the maximum number of stores to retrieve.
     * @return list of locationStore
     */
    public List<LocationStore> findStoreRandomWithLimit(int maxStores){
        return locationStoreRepository.findRandomWithLimit(maxStores);
    }

    /**
     * This method call the LocationStore repository in order to retrieve a list of random stores,
     * This method return one restaurant
     * @return list of locationStore
     */
    public List<LocationStore> findOneRestaurant(int limit){
        return locationStoreRepository.findOneRestaurant(limit);
    }

    public LocationStore findCinema() {
    	return locationStoreRepository.findCinema();
    }

    public List<LocationStore> findCommandClient(int idClient){
    	return locationStoreRepository.findCommandClient(idClient);
    }

    public List<LocationStore> findStoreNoCommand(int idClient, int limit){
    	return locationStoreRepository.findStoreNoCommand(idClient,limit);
    }

    public List<LocationStore> findByIdWithLimitContainsNoStore(int idClient, List<String> stores) {
    	return locationStoreRepository.findByIdWithLimitContainsNoStore(idClient, stores);
    }

    public List<LocationStore> findStoreNoCommandByProfil(int idClient, int limit){
    	return locationStoreRepository.findStoreNoCommandByProfil(idClient, limit);
    }

    public List<LocationStore> findBoutiques(){
    	return locationStoreRepository.findBoutiques();
    }

    public List<LocationStore> findStoreByProfil(int idClient, int limit,List<String> list) {
    	return locationStoreRepository.findStoreByProfil(idClient, limit,list);
    }

}
