package pds.qflush.apiqflush.datamock;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import pds.qflush.apiqflush.model.Sensor;
import pds.qflush.apiqflush.model.Store;
import pds.qflush.apiqflush.service.SensorServiceImpl;
import pds.qflush.apiqflush.service.StoreServiceImpl;

public class DataMockSensor {
	
	@Autowired
    private SensorServiceImpl sensorService;
	@Autowired
	private StoreServiceImpl storeService;

	public void generateSensorData() {
		for(int i = 1; i <= 4; i++) {
			Sensor sensor = new Sensor();
			sensor.setName("sensor-entry-"+i);
			sensor.setStore(null);
			sensor.setEntry(i);
			sensorService.save(sensor);
		}
		List<Store> stores = this.storeService.findAll();
		for(Store store : stores) {
			Sensor sensor = new Sensor();
			sensor.setName(store.getName() + "-sensor");
			sensor.setStore(store);
			sensor.setEntry(0);
			sensorService.save(sensor);
			
			store.setSensor(sensor);
			storeService.save(store);
		}
	}
}
