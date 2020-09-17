package pds.qflush.apiqflush.model;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "evenement")

public class Evenement {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "idEvenement", updatable = false, nullable = false)
	    private Long idEvenement;
	    private Date dateDebut;
	    private Date dateFin;
	    private String nomEvenement;
	    
	    
		public Long getIdEvenement() {
			return idEvenement;
		}
		public void setIdEvenement(Long idEvenement) {
			this.idEvenement = idEvenement;
		}
		public Date getDateDebut() {
			return dateDebut;
		}
		public void setDateDebut(Date dateDebut) {
			this.dateDebut = dateDebut;
		}
		public Date getDateFin() {
			return dateFin;
		}
		public void setDateFin(Date dateFin) {
			this.dateFin = dateFin;
		}
		public String getNomEvenement() {
			return nomEvenement;
		}
		public void setNomEvenement(String nomEvenement) {
			this.nomEvenement = nomEvenement;
		}
		
		@Override
	    public String toString() {
	        return "Evenement{" +
	                "idEvenement=" + idEvenement +
	                ", dateDebut=" + dateDebut +
	                ", dateFin=" + dateFin +
	                ", nomEvenement=" + nomEvenement +
	                '}';
	    }
}
