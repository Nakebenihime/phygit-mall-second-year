package pds.qflush.apiqflush.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pds.qflush.apiqflush.model.Location;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    /**
     * Find only the available location
     * @return list of location
     */
    @Query("select l from Location l  where idLocation not in (select location.idLocation from LocationStore lo)")
    List<Location> findAvailableLocation();
    
    List<Location> findByAile(int aile);
    
    
    @Query("select l from Location l where aile= 1 or aile=2")
    List<Location> findByAileBoutiques();

    @Query(nativeQuery=true,value="SELECT *  FROM location L JOIN Store S ON LS.store_id=S.store_id JOIN Location_store LS ON LS.id_location=L.id_location WHERE (L.aile = 1 or L.aile = 2) and CAST(LS.store_id AS VARCHAR ) IN (?2)")
    List<Location> findStoresWithList(List<String> stores);
}
