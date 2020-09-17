package pds.qflush.apiqflush.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pds.qflush.apiqflush.model.Horaire;
import pds.qflush.apiqflush.model.Store;
import pds.qflush.apiqflush.repository.HoraireRepository;
import pds.qflush.apiqflush.repository.StoreRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class StoreServiceImpl implements ServiceImpl<Store> {
    @Autowired
    private StoreRepository storeRepository;


    @Override
    public Store save(Store store) {
        return storeRepository.save(store);
    }

    @Override
    public Optional<Store> findById(long id) {
        return storeRepository.findById(id);
    }

    @Override
    public void delete(Store store) {
        storeRepository.delete(store);
    }

    @Override
    public List<Store> findAll() {
        return storeRepository.findAll();
    }
    

    public List<Store> findByName(String name){
    	if (!name.equals("")) {
        	return storeRepository.findByNameIgnoreCaseContaining(name);
    	}
    	return null;
    }
    
    public List<Store> findByType(String type){
    	if (!type.equals("")) {
        	return storeRepository.findByTypeIgnoreCaseContaining(type);
    	}
    	return null;
    }
    
  // public List<Horaire> findByHoraire(Date horairedebut, Date horaireFin){
   // 	System.out.println(horairedebut+ " " + horaireFin);
    //	return horaireRepository.findAllByHoraireDebutLessThanEqualAndHoraireFinGreaterThanEqual(horairedebut, horaireFin);
   //}


}
