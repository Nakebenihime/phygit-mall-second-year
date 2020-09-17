package pds.qflush.apiqflush.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;
import pds.qflush.apiqflush.model.Horaire;
import pds.qflush.apiqflush.model.Store;

@Repository
public interface HoraireRepository extends JpaRepository<Horaire, Long> {

	@Query("select distinct u.store from Horaire u where u.horaireDebut < ?1 and u.horaireFin > ?2" )
	List<Store> findByHoraire( Date horaireDebut,Date horaireFin);
	
	@Query("select distinct u.store from Horaire u where u.jour = ?1" )
	List<Store> findByJour(String jour);
	
	@Query("select distinct u.store from Horaire u where u.horaireFin > ?1")
	List<Store> findByHoraireFin(Date horaireFin);
	
	@Query("select distinct u.store from Horaire u where u.horaireDebut < ?1")
	List<Store> findByHoraireDebut(Date horaireDebut);
}
