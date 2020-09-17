package pds.qflush.apiqflush.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pds.qflush.apiqflush.model.ModeLivraison;
import pds.qflush.apiqflush.repository.ModeLivraisonRepository;

@Service
public class ModeLivraisonServiceImpl implements ServiceImpl<ModeLivraison>  {

    @Autowired
    private ModeLivraisonRepository modeLivraisonRepository;
	@Override
	public ModeLivraison save(ModeLivraison t) {
		return modeLivraisonRepository.save(t);
	}

	@Override
	public Optional<ModeLivraison> findById(long id) {
		return modeLivraisonRepository.findById(id);
	}

	@Override
	public void delete(ModeLivraison t) {
		// TODO Auto-generated method stub
		
	}
	public List<ModeLivraison> findAll() {
		return modeLivraisonRepository.findAll();
	}

}
