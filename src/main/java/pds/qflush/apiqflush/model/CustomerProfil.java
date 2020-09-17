package pds.qflush.apiqflush.model;

import java.io.Serializable;


import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;

import javax.persistence.*;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "customerProfil")
@Table(name = "customerProfil")

public class CustomerProfil implements Serializable{
	public CustomerProfilPK getId() {
		return id;
	}

	public void setId(CustomerProfilPK id) {
		this.id = id;
	}

	public Profil getProfil() {
		return profil;
	}

	public void setProfil(Profil profil) {
		this.profil = profil;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
    private CustomerProfilPK id = new CustomerProfilPK();
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idProfil", insertable = false, updatable = false)
    private Profil profil;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerId", insertable = false, updatable = false)
    private Customer customer = new Customer();

		
		
}
