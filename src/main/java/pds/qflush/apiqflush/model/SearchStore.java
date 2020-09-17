package pds.qflush.apiqflush.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

public class SearchStore {

	    private String name;
	    private String phoneNumber;
//	    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//	    private Horaire horaire;
	    @Temporal(TemporalType.TIME)
	    @DateTimeFormat(pattern = "HH:mm")
	    private Date horaireFin;
	    @Temporal(TemporalType.TIME)
	    @DateTimeFormat(pattern = "HH:mm")
	    private Date horaireDebut;
	    
	    private String jour;
//		public Horaire getHoraire() {
//			return horaire;
//		}
//		public void setHoraire(Horaire horaire) {
//			this.horaire = horaire;
//		}
	    
	    public String getJour() {
	    	return jour;
	    }
	    
	    public void setJour(String j) {
	    	this.jour = j;
	    }
	    public Date getHoraireFin() {
	    	return horaireFin;
	    }
	    
	    public Date getHoraireDebut() {
	    	return horaireDebut;
	    }
	    
	    public void setHoraireDebut(Date horaired) {
	    	this.horaireDebut = horaired;
	    }
	    public void setHoraireFin(Date horairef) {
	    	this.horaireFin = horairef;
	    }
		public String getName() {
			return name;
		}
		public void setName(String name1) {
			name = name1;
		}
		public String getPhoneNumber() {
			return phoneNumber;
		}
		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}
	    
	    
}
