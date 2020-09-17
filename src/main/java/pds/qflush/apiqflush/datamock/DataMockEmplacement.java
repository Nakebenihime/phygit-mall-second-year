package pds.qflush.apiqflush.datamock;


import org.springframework.beans.factory.annotation.Autowired;

import pds.qflush.apiqflush.model.Location;
import pds.qflush.apiqflush.model.LocationStore;
import pds.qflush.apiqflush.service.LocationServiceImpl;

public class DataMockEmplacement {
	
	
	public static void generateLocationData(LocationServiceImpl locationService) {
		int nbrBoutique = 150;
		int NbrAllee = 3;
		int nbrBoutiqueParAllee = nbrBoutique / NbrAllee;
		int allee = 1;
		int index = 1;
		for (index = 1; index <= nbrBoutique/2 ; index++) {
			int numeroEmplacement = index;
			for (int n = 1; n <= nbrBoutiqueParAllee ; n++) {
				Location empl = new Location();
				long surface = new Long(DataMockGeneric.generateRandom(40,60));
				empl.setSurface(surface);
				empl.setPrix(generatePrix(surface));
				empl.setAile(allee);
				if (allee == 1 || allee == 2) {
					empl.setVisitTime(20);
				}
				else if (allee == 3){
					empl.setVisitTime(45);
				}
				
				empl.setNumero(numeroEmplacement);
				if(n == 25) {
					numeroEmplacement += 51;
				}
				else {
					numeroEmplacement++;
				}
				if (n <= 25) {
					index++;
				}
				locationService.save(empl);
			}
			allee++;
		}
		/** Cinema **/
		Location empl = new Location();
		long surface = new Long(DataMockGeneric.generateRandom(40,60));
		empl.setSurface(surface);
		empl.setPrix(generatePrix(surface));
		empl.setAile(4);
		empl.setVisitTime(120);
		empl.setNumero(index);
		locationService.save(empl);
		
        
    }
	
	
	
	/** Le prix est de 23 euros par mois**/
	public static double generatePrix(long surface) {
		return 23 * surface;
	}
	
	public char generateNextAllee(char a) {
		char c = a;
        int test = (int) c + 1;
        c = (char)test;
        return c;
	}
}
