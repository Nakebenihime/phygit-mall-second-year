package pds.qflush.apiqflush.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pds.qflush.apiqflush.model.CustomerProfil;
import pds.qflush.apiqflush.model.CustomerProfilPK;

@Repository
public interface CustomerProfilRepository extends JpaRepository<CustomerProfil, CustomerProfilPK> {

}
