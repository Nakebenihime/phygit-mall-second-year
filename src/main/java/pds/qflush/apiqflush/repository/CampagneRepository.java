package pds.qflush.apiqflush.repository;

import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.stereotype.Repository;

import pds.qflush.apiqflush.model.Campagne;



@Repository
public interface CampagneRepository extends JpaRepository<Campagne, Long> {
    

}