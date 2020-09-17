package pds.qflush.apiqflush.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pds.qflush.apiqflush.model.StockProduct;

@Repository
public interface StockProductRepository extends JpaRepository<StockProduct, Long> {

}