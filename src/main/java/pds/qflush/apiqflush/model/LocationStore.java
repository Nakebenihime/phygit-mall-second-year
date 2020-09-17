
package pds.qflush.apiqflush.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "LocationStore")
@Table(name = "LocationStore")
public class LocationStore  {

	 @Id
	 Long locationStoreId;


	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "storeId")
    private Store store;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name ="idLocation")
    private Location location;
    
	private Date datedebut = new Date();
    private Date dateFin = null;

    
    
    public LocationStore() {
    }

	public Long getLocationStoreId() {
		return locationStoreId;
	}

	public void setLocationStoreId(Long locationStoreId) {
		this.locationStoreId = locationStoreId;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Date getDatedebut() {
		return datedebut;
	}

	public void setDatedebut(Date datedebut) {
		this.datedebut = datedebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}
}

