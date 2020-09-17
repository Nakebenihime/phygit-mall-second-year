package pds.qflush.apiqflush.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;


import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pds.qflush.apiqflush.model.Address;
import pds.qflush.apiqflush.model.Category;
import pds.qflush.apiqflush.model.Customer;
import pds.qflush.apiqflush.model.Notification;
import pds.qflush.apiqflush.repository.NotificationRepository;

@Service
public class NotificationServiceImpl implements ServiceImpl<Notification> {
	
	@Autowired
    private NotificationRepository notificationRepository;


    @Override
    public Notification save(Notification notification) {
        return notificationRepository.save(notification);
    }

    @Override
    public Optional<Notification> findById(long id) {
        return notificationRepository.findById(id);
    }

    @Override
    public void delete(Notification notification) {
    	notificationRepository.delete(notification);
    }

    @Override
    public List<Notification> findAll() {
        return notificationRepository.findAll();
    }
    
    public List<Notification> findByCustomer(Customer customer){
        return notificationRepository.findByCustomer(customer);
    }

}


