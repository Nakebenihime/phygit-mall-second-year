package pds.qflush.apiqflush.model;

import javax.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Frequentation")
public class Frequentation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)	
    @Column(name = "frequentationId", updatable = false, nullable = false)
    private Long frequentationId;
    private Date date;
    private boolean identifie;
    private long number;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "store_id")
    private Store store;
    
    public Date getDate() {
    	return date;
    }
    
    public void setDate(Date date) {
    	this.date = date;
    }
    
    public boolean isIdentifie() {
    	return identifie;
    }
    
    public void setIdentifie(boolean identifie) {
    	this.identifie=identifie;
    }

	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}
	
	public void setStore(Store store) {
		this.store=store;
	}
	
	public Store getStore() {
		return this.store;
	}
    
    
    
}
