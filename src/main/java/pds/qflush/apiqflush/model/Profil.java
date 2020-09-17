package pds.qflush.apiqflush.model;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "profil")

public class Profil {
	 @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    @Column(name = "idProfil", updatable = false, nullable = false)
	    private Long idProfil;
	    private String nomProfil;
	    
	    
		
		
		@Override
	    public String toString() {
	        return "Profil{" +
	                "idProfil=" + idProfil +
	                ", nomProfil=" + nomProfil +
	                '}';
	    }




		public Long getIdProfil() {
			return idProfil;
		}




		public void setIdProfil(Long idProfil) {
			this.idProfil = idProfil;
		}




		public String getNomProfil() {
			return nomProfil;
		}




		public void setNomProfil(String nomProfil) {
			this.nomProfil = nomProfil;
		}
}
