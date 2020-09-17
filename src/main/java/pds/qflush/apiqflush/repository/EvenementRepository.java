package pds.qflush.apiqflush.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pds.qflush.apiqflush.model.Evenement;



@Repository
public interface EvenementRepository extends JpaRepository<Evenement, Long> {
    

}