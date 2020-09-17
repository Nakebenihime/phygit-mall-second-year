package pds.qflush.apiqflush.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pds.qflush.apiqflush.model.Address;
import pds.qflush.apiqflush.model.Customer;
import pds.qflush.apiqflush.model.Evenement;
import pds.qflush.apiqflush.repository.EvenementRepository;

@Service
public class EvenementServiceImpl implements ServiceImpl<Evenement> {
	
	@Autowired
    private EvenementRepository evenementRepository;


    @Override
    public Evenement save(Evenement evenement) {
        return evenementRepository.save(evenement);
    }

    @Override
    public Optional<Evenement> findById(long id) {
        return evenementRepository.findById(id);
    }

    @Override
    public void delete(Evenement evenement) {
    	evenementRepository.delete(evenement);
    }

    @Override
    public List<Evenement> findAll() {
        return evenementRepository.findAll();
    }

}


