package pds.qflush.apiqflush.datamock;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pds.qflush.apiqflush.model.Address;
import pds.qflush.apiqflush.model.Customer;
import pds.qflush.apiqflush.model.Horaire;
import pds.qflush.apiqflush.model.LocationStore;
import pds.qflush.apiqflush.model.Store;
import pds.qflush.apiqflush.service.StoreServiceImpl;

public class DataMockStore {
	
	
	public static void generateStoreData(StoreServiceImpl storeService) {
		generateBoutiquesData(storeService);
		generateRestaurantData(storeService);
		generateCinemaData(storeService);
	}
	
	
	 public static void generateBoutiquesData(StoreServiceImpl storeService) {
		 	int NbrBoutique = 100;
	        String csvFile = "fichierdata/NomMagasins.csv";

	        String line = "";

	        try {
	            BufferedReader bufferedReader = new BufferedReader(new FileReader(csvFile));
	            int i = 0;
	            while((line = bufferedReader.readLine()) != null && i<NbrBoutique){
	            	Store store = new Store();

	                String[] data = line.split(";");
	                String nom = data[0];
	                store.setName(nom);
	                store.setDescription("Boutiques ");
	                store.setType("Boutique");
	                String phoneNumber = DataMockGeneric.generatePhoneNumber();
	                store.setPhoneNumber(phoneNumber);
	                store.setLocationStore(null);
//	                store.setLocationStore(new LocationStore());
	                List<Horaire> listHoraire = new ArrayList<Horaire>();
	                for (int index = 0; index<7; index++) {
	                	Horaire horaire = new Horaire();
	                	switch(index) {
		                	case 0 : 
		                		horaire.setJour("Lundi");
		                		
		                		horaire.setHoraireDebut(generateHoraire(8,10));
		                		horaire.setHoraireFin(generateHoraire(18,20));
		                		horaire.setStore(store);
		                		break;
		                	case 1 : 
		                		horaire.setJour("Mardi");
		                		
		                		horaire.setHoraireDebut(generateHoraire(8,10));
		                		horaire.setHoraireFin(generateHoraire(18,20));
		                		horaire.setStore(store);
		                		break;
		                	case 2 : 
		                		horaire.setJour("Mercredi");
		                		
		                		horaire.setHoraireDebut(generateHoraire(8,10));
		                		horaire.setHoraireFin(generateHoraire(18,20));
		                		horaire.setStore(store);
		                		break;
		                	case 3 : 
		                		horaire.setJour("Jeudi");
		                		
		                		horaire.setHoraireDebut(generateHoraire(8,10));
		                		horaire.setHoraireFin(generateHoraire(18,20));
		                		horaire.setStore(store);
		                		break;
		                	case 4: 
		                		horaire.setJour("Vendredi");
		                		
		                		horaire.setHoraireDebut(generateHoraire(8,10));
		                		horaire.setHoraireFin(generateHoraire(18,20));
		                		horaire.setStore(store);
		                		break;
		                	case 5 : 
		                		horaire.setJour("Samedi");
		                		
		                		horaire.setHoraireDebut(generateHoraire(8,10));
		                		horaire.setHoraireFin(generateHoraire(18,20));
		                		horaire.setStore(store);
		                		break;
		                	case 6 : 
		                		horaire.setJour("Dimanche");
		                		
		                		horaire.setHoraireDebut(generateHoraire(8,10));
		                		horaire.setHoraireFin(generateHoraire(12,14));
		                		horaire.setStore(store);
		                		break;


	                	}
	                	listHoraire.add(horaire);
	                	
	                }
	                store.setHoraire(listHoraire);
	             	storeService.save(store);

	                i++;

	            }
	            bufferedReader.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	 
	 
	 public static void generateRestaurantData(StoreServiceImpl storeService) {
		 	int NbrRestaurant = 50;
	        String csvFile = "fichierdata/NomRestaurant.csv";

	        String line = "";

	        try {
	            BufferedReader bufferedReader = new BufferedReader(new FileReader(csvFile));
	            int i = 0;
	            while((line = bufferedReader.readLine()) != null && i<=NbrRestaurant){
	            	Store store = new Store();

	                String[] data = line.split(";");
	                String nom = data[0];
	                store.setName(nom);
	                store.setDescription("Restaurant ");
	                store.setType("Restaurant");
	                String phoneNumber = DataMockGeneric.generatePhoneNumber();
	                store.setPhoneNumber(phoneNumber);
	                store.setLocationStore(null);
	                List<Horaire> listHoraire = new ArrayList<Horaire>();
	                for (int index = 0; index<7; index++) {
	                	Horaire horaire = new Horaire();
	                	switch(index) {
		                	case 0 : 
		                		horaire.setJour("Lundi");
		                		
		                		horaire.setHoraireDebut(generateHoraire(10,11));
		                		horaire.setHoraireFin(generateHoraire(20,24));
		                		horaire.setStore(store);
		                		break;
		                	case 1 : 
		                		horaire.setJour("Mardi");
		                		
		                		horaire.setHoraireDebut(generateHoraire(10,11));
		                		horaire.setHoraireFin(generateHoraire(20,24));
		                		horaire.setStore(store);
		                		break;
		                	case 2 : 
		                		horaire.setJour("Mercredi");
		                		
		                		horaire.setHoraireDebut(generateHoraire(10,11));
		                		horaire.setHoraireFin(generateHoraire(20,24));
		                		horaire.setStore(store);
		                		break;
		                	case 3 : 
		                		horaire.setJour("Jeudi");
		                		
		                		horaire.setHoraireDebut(generateHoraire(10,11));
		                		horaire.setHoraireFin(generateHoraire(20,24));
		                		horaire.setStore(store);
		                		break;
		                	case 4: 
		                		horaire.setJour("Vendredi");
		                		
		                		horaire.setHoraireDebut(generateHoraire(10,11));
		                		horaire.setHoraireFin(generateHoraire(20,24));
		                		horaire.setStore(store);
		                		break;
		                	case 5 : 
		                		horaire.setJour("Samedi");
		                		
		                		horaire.setHoraireDebut(generateHoraire(10,11));
		                		horaire.setHoraireFin(generateHoraire(20,24));
		                		horaire.setStore(store);
		                		break;
		                	case 6 : 
		                		horaire.setJour("Dimanche");
		                		
		                		horaire.setHoraireDebut(generateHoraire(10,11));
		                		horaire.setHoraireFin(generateHoraire(20,24));
		                		horaire.setStore(store);
		                		break;


	                	}
	                	listHoraire.add(horaire);
	                	
	                }
	                store.setHoraire(listHoraire);
	                storeService.save(store);
	                i++;

	            }
	            bufferedReader.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	 
	 public static void generateCinemaData(StoreServiceImpl storeService) {
		 Store store = new Store();
		 store.setName("Gaumont");
		 store.setDescription("Cinema 50 salles");
		 store.setPhoneNumber(DataMockGeneric.generatePhoneNumber());
		 store.setType("Cinema");
		 List<Horaire> listHoraire = new ArrayList<Horaire>();
         store.setLocationStore(null);
         for (int index = 0; index<7; index++) {
         	Horaire horaire = new Horaire();
         	switch(index) {
             	case 0 : 
             		horaire.setJour("Lundi");
             		
             		horaire.setHoraireDebut(generateHoraire(10,11));
             		horaire.setHoraireFin(generateHoraire(20,24));
             		horaire.setStore(store);
             		break;
             	case 1 : 
             		horaire.setJour("Mardi");
             		
             		horaire.setHoraireDebut(generateHoraire(10,11));
             		horaire.setHoraireFin(generateHoraire(20,24));
             		horaire.setStore(store);
             		break;
             	case 2 : 
             		horaire.setJour("Mercredi");
             		
             		horaire.setHoraireDebut(generateHoraire(10,11));
             		horaire.setHoraireFin(generateHoraire(20,24));
             		horaire.setStore(store);
             		break;
             	case 3 : 
             		horaire.setJour("Jeudi");
             		
             		horaire.setHoraireDebut(generateHoraire(10,11));
             		horaire.setHoraireFin(generateHoraire(20,24));
             		horaire.setStore(store);
             		break;
             	case 4: 
             		horaire.setJour("Vendredi");
             		
             		horaire.setHoraireDebut(generateHoraire(10,11));
             		horaire.setHoraireFin(generateHoraire(20,24));
             		horaire.setStore(store);
             		break;
             	case 5 : 
             		horaire.setJour("Samedi");
             		
             		horaire.setHoraireDebut(generateHoraire(10,11));
             		horaire.setHoraireFin(generateHoraire(20,24));
             		horaire.setStore(store);
             		break;
             	case 6 : 
             		horaire.setJour("Dimanche");
             		
             		horaire.setHoraireDebut(generateHoraire(10,11));
             		horaire.setHoraireFin(generateHoraire(20,24));
             		horaire.setStore(store);
             		break;


         	}
         	listHoraire.add(horaire);
         	
         }
         store.setHoraire(listHoraire);
         storeService.save(store);
		 
		 
	 }
	 
	 public static Date generateHoraire(int min, int max) {
		 int h = DataMockGeneric.generateRandom(min, max);
		 String horaire = new String();
		 if (h < 10) {
			 horaire = "0" + String.valueOf(h); 
		 }
		 else {
			 horaire = String.valueOf(h);
		 }
		 horaire = horaire + ":00";
		 SimpleDateFormat sdf = new SimpleDateFormat("HH:MM");
         Date HoraireDebut = new Date();
		try {
			HoraireDebut = sdf.parse(horaire);
	         return HoraireDebut;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		 
	 }
}
