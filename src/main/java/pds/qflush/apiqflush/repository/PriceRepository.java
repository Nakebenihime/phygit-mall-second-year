package pds.qflush.apiqflush.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pds.qflush.apiqflush.model.Price;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {
}
