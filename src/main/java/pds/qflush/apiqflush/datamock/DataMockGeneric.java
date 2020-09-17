package pds.qflush.apiqflush.datamock;

import java.text.DecimalFormat;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import pds.qflush.apiqflush.service.FrequentationServiceImpl;
import pds.qflush.apiqflush.service.LocationStoreServiceImpl;
import pds.qflush.apiqflush.service.StoreServiceImpl;

@Controller
public class DataMockGeneric {
	
	@Autowired
    private LocationStoreServiceImpl locationStoreServiceImpl;
	
	@Autowired
	private StoreServiceImpl storeServiceImpl;
	
	@Autowired
    private FrequentationServiceImpl frequentationService;
	
	@GetMapping("/feedDatabase")
    public String mockDatabase(Model model) {
		DataMockSensor dms = new DataMockSensor();
		DataMockFrequentation dmf = new DataMockFrequentation();
		//dms.generateSensorData();
		dmf.generateFrequentationData(frequentationService, storeServiceImpl);
        return "Prevent";
    }
 
    public static int generateRandom(int min, int max){
        Random random = new Random();
        int generated = random.nextInt(max - min);
        return  generated+min;
    }

	    public static double generateRandomDouble(int min, int max){
	    	//Random random = new Random();
			double x = (Math.random()*((max-min)+1))+min;
			DecimalFormat decimalFormat = new DecimalFormat("#0.00");
			String numberFormated = decimalFormat.format(x);
			String numberFormatWitoutComa= numberFormated.replace(',', '.');
			x = new Double(numberFormatWitoutComa);
			return x;
		}

	    public static String generatePhoneNumber() {
	        String number = null;
	        StringBuilder sb = new StringBuilder("07");
	        for (int i = 0; i < 8; i++) {

	            sb.append(generateRandom(1,9));
	        }
	        number= sb.toString();
	        return number;
	    }
}
