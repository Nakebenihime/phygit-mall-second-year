package pds.qflush.apiqflush.model;

import java.util.List;

public class FormulaireSearchLivraison {
	
	
	private int dureeMax;
	private int prixMax;
	private float prixMin=0;
	private String nomService;
	private TarificationLivraison TarificationLivraison;
	
	public FormulaireSearchLivraison() {}

	public int getDureeMax() {
		return dureeMax;
	}
	
	public float getPrixMin() {
		return prixMin;
	}

	public int getPrixMax() {
		return prixMax;
	}

	public String getNomService() {
		return nomService;
	}
	
	public void setPrixMax(int prixMax) {
		this.prixMax=prixMax;
	}
	
	public void setPrixMin(float prixMin) {
		this.prixMin=prixMin;
	}
	
	public void setDureeMax(int dureeMax) {
		this.dureeMax=dureeMax;
	}
	
	public void setNomService(String nomService) {
		this.nomService=nomService;
	}
	
}
