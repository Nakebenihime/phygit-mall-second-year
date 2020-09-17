package pds.qflush.apiqflush.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pds.qflush.apiqflush.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
