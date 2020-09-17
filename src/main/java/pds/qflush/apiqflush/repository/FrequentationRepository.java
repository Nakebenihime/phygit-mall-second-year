package pds.qflush.apiqflush.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pds.qflush.apiqflush.model.Frequentation;

@Repository
public interface FrequentationRepository extends JpaRepository<Frequentation, Long>{

}
