package pds.qflush.apiqflush.model;

import java.util.List;

public class CampagneProfilCategory {

	private Campagne campagne;
	private List<Profil> profil;
	private List<Category> category;
	private Evenement evenement;
	public Evenement getEvenement() {
		return evenement;
	}
	public void setEvenement(Evenement evenement) {
		this.evenement = evenement;
	}
	public Campagne getCampagne() {
		return campagne;
	}
	public void setCampagne(Campagne campagne) {
		this.campagne = campagne;
	}
	
	public List<Profil> getProfil() {
		return profil;
	}
	public void setProfil(List<Profil> profil) {
		this.profil = profil;
	}
	public List<Category> getCategory() {
		return category;
	}
	public void setCategory(List<Category> category) {
		this.category = category;
	}
	
	
	
}
