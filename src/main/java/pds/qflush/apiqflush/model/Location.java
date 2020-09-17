
package pds.qflush.apiqflush.model;

import java.util.List;

import javax.persistence.*;

@Entity(name = "Location")
@Table(name = "Location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idLocation", updatable = false, nullable = false)
    private Long idLocation;
    private double Surface;
    private double prix ;
    private int aile;
    private int etage;
    private int numero;
    private int visitTime;
    @OneToOne(
            mappedBy = "location",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private LocationStore locationStore;
    public Location(){

    }

    public Long getIdLocation() {
        return idLocation;
    }

    public double getSurface() {
        return Surface;
    }

    public double getPrix() {
        return prix;
    }

    public int getAile() {
        return aile;
    }

    public int getEtage() {
        return etage;
    }

    public int getNumero() {
        return numero;
    }

    public LocationStore getLocationStore() {
        return locationStore;
    }

    public void setIdLocation(Long idLocation) {
        this.idLocation = idLocation;
    }

    public void setSurface(double surface) {
        Surface = surface;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public void setAile(int aile) {
        this.aile = aile;
    }

    public void setEtage(int etage) {
        this.etage = etage;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setLocationStore(LocationStore locationStore) {
       this.locationStore = locationStore;
    }
    
    

    public int getVisitTime() {
		return visitTime;
	}

	public void setVisitTime(int visitTime) {
		this.visitTime = visitTime;
	}

	@Override
    public String toString() {
        return "Location{" +
                "idLocation=" + idLocation +
                ", Surface=" + Surface +
                ", prix=" + prix +
                ", aile=" + aile +
                ", etage=" + etage +
                ", numero=" + numero +
                ", locationStore=" + locationStore +
                '}';
    }
}

