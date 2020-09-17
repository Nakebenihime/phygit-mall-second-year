package pds.qflush.apiqflush.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pds.qflush.apiqflush.model.Store;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {

	
	  List<Store> findByNameIgnoreCaseContaining(String lastName);
	  List<Store> findByTypeIgnoreCaseContaining(String type);


//	@Query("select u from Store u, Horaire h where g.store_id = u.storeId")  
//	List<Store> findByHoraireOuvert(Date horairedebut);
//	
//	@Query("select u from Store u, Horaire h where g.store_id = u.storeId")  
//	List<Store> findByHoraireFerme(Date horaireFin);
}