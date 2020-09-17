package pds.qflush.apiqflush.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity(name = "Sensor")
@Table(name = "Sensor")
public class Sensor {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SensorId", updatable = false, nullable = false)
    private Long sensorId;
    private String name;
	@OneToOne(mappedBy = "sensor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Store store;
	private int entry;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public Long getSensorId() {
		return sensorId;
	}
	
	public void setSensorId(Long sensorId) {
		this.sensorId = sensorId;
	}

	public int getEntry() {
		return entry;
	}

	public void setEntry(int entry) {
		this.entry = entry;
	}
}
