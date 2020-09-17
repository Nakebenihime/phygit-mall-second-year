package pds.qflush.apiqflush.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pds.qflush.apiqflush.model.LocationStore;
import pds.qflush.apiqflush.model.Store;

import java.util.List;

public interface LocationStoreRepository  extends JpaRepository<LocationStore, Long> {

    /**
     * This customized query retrieve a list of random stores, the number of rows is limited by a limit parameter
     * @param limit define the number of maximum rows the query retrieve.
     * @return a list of located stores
     */
    @Query(nativeQuery=true,value="SELECT *  FROM location_store LS JOIN Store S ON LS.store_id=S.store_id JOIN Location L ON LS.id_location=L.id_location WHERE (L.aile = 1 or L.aile = 2)  ORDER BY random() ORDER BY random(), L.aile desc LIMIT ?1")
    List<LocationStore> findRandomWithLimit(int limit);

    
    @Query(nativeQuery=true, value="Select * from location_store where store_id IN (select store_id from Store WHERE store_id IN (select store_id from stock_product where product_id IN (select product_id from product where category_id IN (select category_id from category where id_profil IN (select id_profil from customer_profil where customer_id = ?1))))) LIMIT ?2")
    List<LocationStore> findStoreByProfil(int idClient, int limit, List<String> stores);

    @Query(nativeQuery=true,value="SELECT *  FROM location_store LS JOIN Store S ON LS.store_id=S.store_id JOIN Location L ON LS.id_location=L.id_location WHERE (L.aile = 1 or L.aile = 2) and CAST(LS.store_id AS VARCHAR ) IN (?2) ORDER BY random(), L.aile desc LIMIT ?1")
    List<LocationStore> findByIdWithLimit(int limit, List<String> stores);
    
    @Query(nativeQuery=true,value="SELECT *  FROM location_store LS JOIN Store S ON LS.store_id=S.store_id JOIN Location L ON LS.id_location=L.id_location WHERE (L.aile = 1 or L.aile = 2) and CAST(LS.store_id AS VARCHAR ) NOT IN (?2) ORDER BY random(), L.aile desc LIMIT ?1")
    List<LocationStore> findByIdWithLimitContainsNoStore(int limit, List<String> stores);
    
    @Query(nativeQuery=true,value ="SELECT * from location_store LS JOIN Localtion L where (L.aile = 1 or L.aile = 2)")
    List<LocationStore> findBoutiques();
    
    @Query(nativeQuery=true,value="SELECT *  FROM location_store LS JOIN Store S ON LS.store_id=S.store_id JOIN Location L ON LS.id_location=L.id_location WHERE (L.aile = 3)  ORDER BY random() LIMIT ?1")
    List<LocationStore> findOneRestaurant(int limit);

    @Query(nativeQuery=true,value="SELECT *  FROM location_store LS JOIN Store S ON LS.store_id=S.store_id JOIN Location L ON LS.id_location=L.id_location WHERE (L.aile = 4)  ORDER BY random() LIMIT 1")
	LocationStore findCinema();
    
    @Query(nativeQuery=true,value="SELECT * FROM location_store LS JOIN Store S ON LS.store_id=S.store_id JOIN Location L ON LS.id_location=L.id_location JOIN Stock_Product SP ON SP.store_id=S.store_id JOIN Product P ON P.product_id=SP.product_id JOIN Command_line CL ON CL.product_id=P.product_id JOIN Command CMD ON CMD.command_id=CL.command_id WHERE CMD.customer_customer_id = ?1")
    List<LocationStore> findCommandClient(int idClient);
    
   
    @Query(nativeQuery=true,value="SELECT * FROM location_store LS JOIN Store S ON LS.store_id=S.store_id JOIN Location L ON LS.id_location=L.id_location WHERE LS.store_id NOT IN (Select SP.store_id from product P JOIN command_line CL ON CL.product_id=P.product_id JOIN command C ON C.command_id =CL.command_id JOIN stock_product SP ON P.product_id = SP.product_id where C.customer_customer_id = ?1) and (L.aile = 1 or L.aile = 2) ORDER BY random(), L.aile desc  LIMIT ?2")
    List<LocationStore> findStoreNoCommand(int idClient, int limit);
    
//    @Query(nativeQuery=true,value="SELECT * FROM location_store LS JOIN Store S ON LS.store_id=S.store_id JOIN Location L ON LS.id_location=L.id_location JOIN stock_product SP ON SP.store_id = S.store_id JOIN product PR ON PR.product_id = SP.product_id JOIN category C ON C.category_id = PR.category_id JOIN customer_profil CP ON CP.id_profil = C.id_profil WHERE LS.store_id NOT IN (Select SP.store_id from product P JOIN command_line CL ON CL.product_id=P.product_id JOIN command C ON C.command_id =CL.command_id JOIN stock_product SP ON P.product_id = SP.product_id where C.customer_customer_id = ?1) and CP.customer_id = ?1 ORDER BY random() LIMIT ?2")
//    List<LocationStore> findStoreNoCommandWithProfil(int idClient, int limit);
    
    @Query(nativeQuery=true,value="SELECT * FROM location_store LS JOIN Store S ON LS.store_id=S.store_id JOIN Location L ON LS.id_location=L.id_location WHERE LS.store_id NOT IN (Select SP.store_id from product P JOIN command_line CL ON CL.product_id=P.product_id JOIN command C ON C.command_id =CL.command_id JOIN stock_product SP ON P.product_id = SP.product_id where SP.store_id IN (SELECT SPR.store_id from Stock_product SPR JOIN Product PR ON PR.product_id = SPR.product_id JOIN Category CTY ON CTY.category_id = PR.category_id JOIN Profil PRF ON PRF.id_profil = CTY.id_profil JOIN Customer_profil CPFL ON CPFL.id_profil = PRF.id_profil where CPFL.customer_id = ?1)) ORDER BY random() LIMIT ?2")
    List<LocationStore> findStoreNoCommandByProfil(int idClient, int limit);
    
    @Query(nativeQuery=true,value="SELECT * FROM location_store LS order by store_id")
    List<LocationStore> findAllOrder();
    
    
    
    
    
    
//    @Query(nativeQuery=true,value="SELECT * FROM location_store LS JOIN Store S ON LS.store_id=S.store_id JOIN Location L ON LS.id_location=L.id_location JOIN ")
//    LocationStore findByProfilStoreUnknown(String profil);
    
   
}
