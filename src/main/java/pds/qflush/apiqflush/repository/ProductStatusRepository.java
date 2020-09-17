package pds.qflush.apiqflush.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pds.qflush.apiqflush.model.ProductStatus;

@Repository
public interface ProductStatusRepository extends JpaRepository<ProductStatus, Long> {
}
