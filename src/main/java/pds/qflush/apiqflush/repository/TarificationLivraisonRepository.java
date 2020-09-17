package pds.qflush.apiqflush.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pds.qflush.apiqflush.model.TarificationLivraison;

@Repository
public interface TarificationLivraisonRepository extends JpaRepository<TarificationLivraison, Long> {
	
	/*@Query("select * from tarification_livraison where prix between 0 and ?1")
	List<TarificationLivraison> findByPrix(int prixMax);*/
	
	List<TarificationLivraison> findByprixLessThanEqual(int prixMax);

}