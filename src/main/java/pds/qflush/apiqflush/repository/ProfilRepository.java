package pds.qflush.apiqflush.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pds.qflush.apiqflush.model.Customer;
import pds.qflush.apiqflush.model.Profil;



@Repository
public interface ProfilRepository extends JpaRepository<Profil, Long> {
	
    List<Profil> findByNomProfil(String name);

    

}