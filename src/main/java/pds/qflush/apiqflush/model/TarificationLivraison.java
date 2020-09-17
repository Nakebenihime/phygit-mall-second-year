
package pds.qflush.apiqflush.model;


import javax.persistence.*;

@Entity
@Table(name = "tarification_livraison")
public class TarificationLivraison {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idTarification", updatable = false, nullable = false)
    private Long idTarification;
    private int kmMax;
    private int kmMin;
    private int poidsMax;
    private int poidsMin;
    private int prix;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idModeLivraison", referencedColumnName = "idModeLivraison")
    private ModeLivraison modelivraison;

 
  
    public TarificationLivraison() {
	
    }
   

	public Long getIdTarification() {
		return idTarification;
	}



	public void setIdTarification(Long idTarification) {
		this.idTarification = idTarification;
	}




	public int getKmMax() {
		return kmMax;
	}






	public void setKmMax(int kmMax) {
		this.kmMax = kmMax;
	}






	public int getKmMin() {
		return kmMin;
	}






	public void setKmMin(int kmMin) {
		this.kmMin = kmMin;
	}






	public int getPoidsMax() {
		return poidsMax;
	}






	public void setPoidsMax(int poidsMax) {
		this.poidsMax = poidsMax;
	}






	public int getPoidsMin() {
		return poidsMin;
	}






	public void setPoidsMin(int poidsMin) {
		this.poidsMin = poidsMin;
	}






	public int getPrix() {
		return prix;
	}






	public void setPrix(int prix) {
		this.prix = prix;
	}











	










	public ModeLivraison getModelivraison() {
		return modelivraison;
	}






	public void setModelivraison(ModeLivraison modelivraison) {
		this.modelivraison = modelivraison;
	}






	@Override
    public String toString() {
        return "TarificationLivraison{" +
                "idTarification=" + idTarification +
                ", kmMax=" + kmMax +
                ", kmMin=" + kmMin +
                ", poidsMax=" + poidsMax +
                ", poidsMin=" + poidsMin +
                ", ModeLivraison=" + modelivraison +

                '}';
    }
}

