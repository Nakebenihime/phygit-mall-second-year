package pds.qflush.apiqflush.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pds.qflush.apiqflush.model.Location;
import pds.qflush.apiqflush.model.LocationStore;
import pds.qflush.apiqflush.repository.LocationStoreRepository;

@Service
public class LocationStoreServiceImpl implements ServiceImpl<LocationStore> {
	
	@Autowired
	LocationStoreRepository locationStoreRepository;
	
	 @Override
	    public LocationStore save(LocationStore locationstore) {
	        return locationStoreRepository.save(locationstore);
	    }

	@Override
	public Optional<LocationStore> findById(long id) {
		return locationStoreRepository.findById(id);
	}

	@Override
	public void delete(LocationStore t) {
		locationStoreRepository.delete(t);
	}

	@Override
	public List<LocationStore> findAll() {
		return locationStoreRepository.findAll();
	}

	public List<LocationStore> findAllOrder() {
		return locationStoreRepository.findAllOrder();
	}
}
