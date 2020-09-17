package pds.qflush.apiqflush.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pds.qflush.apiqflush.model.Category;
import pds.qflush.apiqflush.model.Customer;
import pds.qflush.apiqflush.model.Evenement;
import pds.qflush.apiqflush.model.Notification;



@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
	
	List<Notification> findByCustomer(Customer customer);
    

}