package pds.qflush.apiqflush.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pds.qflush.apiqflush.model.Location;
import pds.qflush.apiqflush.repository.LocationRepository;

import java.util.List;
import java.util.Optional;
@Service
public class LocationServiceImpl implements ServiceImpl<Location>{

    @Autowired
    private LocationRepository locationRepository ;

    @Override
    public Location save(Location location) {
        return locationRepository.save(location);
    }

    @Override
    public Optional<Location> findById(long id) {
        return locationRepository.findById(id);
    }

    @Override
    public void delete(Location location) {
        locationRepository.delete(location);
    }
    
    

    @Override
    public List<Location> findAll() {
        return locationRepository.findAll();
    }

    public Location getOne(long id){
        return locationRepository.getOne(id);
    }

    public List<Location> findAvailableLocation(){
        return locationRepository.findAvailableLocation();
    }
    
    public List<Location> findByAile(int aile){
    	return locationRepository.findByAile(aile);
    }
    public List<Location> findByAileBoutiques(){
    	return locationRepository.findByAileBoutiques();
    }
}
