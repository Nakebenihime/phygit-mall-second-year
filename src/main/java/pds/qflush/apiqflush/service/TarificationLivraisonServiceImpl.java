package pds.qflush.apiqflush.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pds.qflush.apiqflush.model.TarificationLivraison;
import pds.qflush.apiqflush.repository.TarificationLivraisonRepository;
@Service
public class TarificationLivraisonServiceImpl implements ServiceImpl<TarificationLivraison> {

    @Autowired
    private TarificationLivraisonRepository tarificationLivraisonRepository;

	public TarificationLivraison save(TarificationLivraison t) {
		return tarificationLivraisonRepository.save(t);
	}

	@Override
	public Optional<TarificationLivraison> findById(long id) {
		return null;
	}
	

	@Override
	public void delete(TarificationLivraison t) {
		
	}

	public List<TarificationLivraison> findAll() {
		return tarificationLivraisonRepository.findAll();
	}
	
	/*public List<TarificationLivraison> findByPrix(int prixMax) {
		return tarificationLivraisonRepository.findByPrix(prixMax);
	}*/
	
	public List<TarificationLivraison> findByprixLessThan(int prixMax) {
		return tarificationLivraisonRepository.findByprixLessThanEqual(prixMax);
	}
	
	
	public List<TarificationLivraison> getFilteredModeLivraison(List<TarificationLivraison> livraisons, int prixMax, int kmMax, String nomService) {
		livraisons.removeIf(p -> p.getPrix() > prixMax);
    	livraisons.removeIf(p -> p.getModelivraison().getDureeMaxlivraison() > kmMax);
    	if(!(nomService.isEmpty())) {
    		livraisons.removeIf(p -> !(p.getModelivraison().getLibelle().toLowerCase().contains(nomService.toLowerCase())));
    	}
		return livraisons;
	}
	
}
