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
@Table(name = "campagne")

public class Campagne {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "idCampagne", updatable = false, nullable = false)
	    private Long idCampagne;

	    private String nomCampagne;
	    private String description;
	    
	    @ManyToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name = "idEvenement", referencedColumnName = "idEvenement")
	    private Evenement evenement;

	    private Boolean singleCheckboxField;

	public Boolean getSingleCheckboxField() {
		return singleCheckboxField;
	}

	public void setSingleCheckboxField(Boolean singleCheckboxField) {
		this.singleCheckboxField = singleCheckboxField;
	}

	public Evenement getEvenement() {
			return evenement;
		}




		public void setEvenement(Evenement evenement) {
			this.evenement = evenement;
		}




		public Evenement getIdEvenement() {
			return evenement;
		}




		public void setIdEvenement(Evenement idEvenement) {
			this.evenement = idEvenement;
		}




//		@Override
//	    public String toString() {
//	        return "Campagne{" +
//	                "idCampagne=" + idCampagne +
//	                ", nomCampagne=" + nomCampagne +
//	                ", description=" + description +
//	                '}';
//	    }




		public Long getIdCampagne() {
			return idCampagne;
		}




		public void setIdCampagne(Long idCampagne) {
			this.idCampagne = idCampagne;
		}




		public String getNomCampagne() {
			return nomCampagne;
		}




		public void setNomCampagne(String nomCampagne) {
			this.nomCampagne = nomCampagne;
		}




		public String getDescription() {
			return description;
		}




		public void setDescription(String description) {
			this.description = description;
		}
}
