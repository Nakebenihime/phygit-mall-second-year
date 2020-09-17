
package pds.qflush.apiqflush.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "mode_livraison")
public class ModeLivraison {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idModeLivraison", updatable = false, nullable = false)
    private Long idModeLivraison;
    private String libelle;
    private int dureeMinlivraison;
    private int dureeMaxlivraison;

	@OneToMany(mappedBy = "modelivraison", cascade=CascadeType.ALL)
    private List<TarificationLivraison> tarificationLivraison;

	public Long getIdModeLivraison() {
		return idModeLivraison;
	}

	public void setIdModeLivraison(Long idModeLivraison) {
		this.idModeLivraison = idModeLivraison;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public int getDureeMinlivraison() {
		return dureeMinlivraison;
	}

	public void setDureeMinlivraison(int dureeMinlivraison) {
		this.dureeMinlivraison = dureeMinlivraison;
	}

	public int getDureeMaxlivraison() {
		return dureeMaxlivraison;
	}

	public void setDureeMaxlivraison(int dureeMaxlivraison) {
		this.dureeMaxlivraison = dureeMaxlivraison;
	}

	public List<TarificationLivraison> getTarificationLivraison() {
		return tarificationLivraison;
	}

	public void setTarificationLivraison(List<TarificationLivraison> tarificationLivraison) {
		this.tarificationLivraison = tarificationLivraison;
	}

	@Override
	public String toString() {
		return "ModeLivraison{" +
				"idModeLivraison=" + idModeLivraison +
				", libelle='" + libelle + '\'' +
				", dureeMinlivraison=" + dureeMinlivraison +
				", dureeMaxlivraison=" + dureeMaxlivraison +
				", tarificationLivraison=" + tarificationLivraison +
				'}';
	}
}

