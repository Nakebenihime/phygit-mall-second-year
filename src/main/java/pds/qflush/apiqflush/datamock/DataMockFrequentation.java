package pds.qflush.apiqflush.datamock;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import pds.qflush.apiqflush.model.Frequentation;
import pds.qflush.apiqflush.model.LocationStore;
import pds.qflush.apiqflush.model.Store;
import pds.qflush.apiqflush.service.FrequentationServiceImpl;
import pds.qflush.apiqflush.service.LocationStoreServiceImpl;
import pds.qflush.apiqflush.service.StoreServiceImpl;


public class DataMockFrequentation {

	@Autowired
	private StoreServiceImpl storeServiceImpl;
	
	
	public void generateFrequentationData(FrequentationServiceImpl frequentationService, StoreServiceImpl storeServiceImpl) {
		LocalDateTime ldt = LocalDateTime.of(2014, Month.JANUARY, 1, 0, 0, 0);
		LocalDateTime ldtEnd = LocalDateTime.of(2018, 12, 31, 0, 0);
		//y = a*i + b
		int b = 45000; int a = 28; int i = 0;
		//variation saisonniere
		double vs = 1.45;
		List<Frequentation> freqsId = new ArrayList<Frequentation>();
		//List<Frequentation> freqsUnId = new ArrayList<Frequentation>();
		//List<Store> stores = new ArrayList<Store>();
    	/*for (LocationStore ls : locationStoreServiceImpl.findById(1)) {
			stores.add(ls.getStore());
		}*/
		List<Store> stores = new ArrayList<>();
      	Optional<Store> s =storeServiceImpl.findById(1);
        stores.add(s.get());
		
		
		while(ldt.isBefore(ldtEnd)) {
			for(Store store : stores) {
				Frequentation freqIdentifie = new Frequentation();
				freqIdentifie.setDate(Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant()));
				System.out.println(ldt);
				freqIdentifie.setIdentifie(true);
				freqIdentifie.setNumber((long) ((b + a*i)*vs));
				freqIdentifie.setStore(store);
				frequentationService.save(freqIdentifie);
				freqsId.add(freqIdentifie);
			}
			
			
			/*Frequentation freqUnIdentifie = new Frequentation();
			freqUnIdentifie.setDate(Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant()));
			freqUnIdentifie.setIdentifie(false);
			freqUnIdentifie.setNumber((long) ((b + a*i)*vs));
			frequentationService.save(freqUnIdentifie);
			freqsUnId.add(freqUnIdentifie);*/
			
			if(ldt.getMonthValue() < 4) {
				vs = vs - 0.01;
			} else if(ldt.getMonthValue() < 7) {
				vs = vs + 0.01;
			} else if(ldt.getMonthValue() < 10) {
				vs = vs - 0.01;
			} else {
				vs = vs + 0.01;
			}
			i++;
			ldt = ldt.plus(1, ChronoUnit.DAYS);
		}
		

	}
}
