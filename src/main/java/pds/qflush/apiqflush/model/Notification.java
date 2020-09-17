package pds.qflush.apiqflush.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "notification")

public class Notification {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "idNotification", updatable = false, nullable = false)
	    private Long idNotification;

	    
	    
	    @ManyToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name = "idCampagne", referencedColumnName = "idCampagne")
	    private Campagne campagne;
	    
	    @ManyToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name = "customerId", referencedColumnName = "customerId")
	    private Customer customer;
		
	    public Customer getCustomer() {
			return customer;
		}

		public void setCustomer(Customer customer) {
			this.customer = customer;
		}

		public Long getIdNotification() {
			return idNotification;
		}

		public void setIdNotification(Long idNotification) {
			this.idNotification = idNotification;
		}

		public Campagne getCampagne() {
			return campagne;
		}

		public void setCampagne(Campagne campagne) {
			this.campagne = campagne;
		}

		public Profil getProfil() {
			return profil;
		}

		public void setProfil(Profil profil) {
			this.profil = profil;
		}

		@ManyToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name = "idProfil", referencedColumnName = "idProfil")
	    private Profil profil;
		



		
}
