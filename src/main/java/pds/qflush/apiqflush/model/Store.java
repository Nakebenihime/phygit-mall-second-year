package pds.qflush.apiqflush.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity(name = "Store")
@Table(name = "Store")
public class Store {
	/** Champ type : Decrit le type du magasins (Restaurant, Boutiques, Cinema) **/
    //store table
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "storeId", updatable = false, nullable = false)
    private Long storeId;
    private String name;
    private String description;
    private String phoneNumber;
    private String type;
    private boolean status = true;
    @OneToOne(mappedBy = "store", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private LocationStore locationStore = new LocationStore();
    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Horaire> horaire;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<StockProduct> stockProducts;
    @OneToOne
	@PrimaryKeyJoinColumn
    private Sensor sensor;
    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Frequentation> frequentation;

	public Store(){

    }
    public Store(String name, String description, String phoneNumber, boolean status, LocationStore locationStore, List<Horaire> horaire) {
        this.name = name;
        this.description = description;
        this.phoneNumber = phoneNumber;
        this.status = status;
//        this.locationStore = locationStore;
        this.horaire = horaire;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public LocationStore getLocationStore() {
        return locationStore;
    }

    public void setLocationStore(LocationStore locationStore) {
        this.locationStore = locationStore;
    }

    public List<Horaire> getHoraire() {
        return horaire;
    }

    public void setHoraire(List<Horaire> horaire) {
        this.horaire = horaire;
    }


   public List<StockProduct> getStockProducts() {
        return stockProducts;
    }

    public void setStockProducts(List<StockProduct> stockProducts) {
        this.stockProducts = stockProducts;
    }
    public Sensor getSensor() {
		return sensor;
	}
	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}
	public List<Frequentation> getFrequentation() {
		return frequentation;
	}
	public void setFrequentation(List<Frequentation> frequentation) {
		this.frequentation = frequentation;
	}
	
    @Override
    public String toString() {
        return "Store{" +
                "storeId=" + storeId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", status=" + status +
                ", locationStore=" + locationStore +
                ", horaire=" + horaire +
                '}';
    }
	public void setType(String string) {
		this.type = string;
		// TODO Auto-generated method stub

	}
	public String getType() {
		return this.type;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Store store = (Store) o;
        return  Objects.equals(name, store.name);
    }
}
