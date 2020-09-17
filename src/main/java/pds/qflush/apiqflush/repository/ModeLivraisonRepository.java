package pds.qflush.apiqflush.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pds.qflush.apiqflush.model.ModeLivraison;

@Repository
public interface ModeLivraisonRepository extends JpaRepository<ModeLivraison, Long> {

}