package pds.qflush.apiqflush.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pds.qflush.apiqflush.model.Customer;
import pds.qflush.apiqflush.model.Profil;
import pds.qflush.apiqflush.repository.ProfilRepository;
@Service
public class ProfilServiceImpl implements ServiceImpl<Profil> {
	@Autowired
    private ProfilRepository profilRepository;


    @Override
    public Profil save(Profil profil) {
        return profilRepository.save(profil);
    }

    @Override
    public Optional<Profil> findById(long id) {
        return profilRepository.findById(id);
    }

    @Override
    public void delete(Profil profil) {
    	profilRepository.delete(profil);
    }

    @Override
    public List<Profil> findAll() {
        return profilRepository.findAll();
    }
    
    public List<Profil> findByNomProfil(String name){
        System.out.println(name);
        return profilRepository.findByNomProfil(name);
    }

    public void generateProfile(){

        String[] profils = {"Sportif","Fashionista","Maison","Geek","Cinéphile","Gourmet","Enfance","Automobil"};

        for(int i = 0; i < profils.length; i++){
            int index = i;
            String name = profils[index];
            Profil profil = new Profil();
            profil.setNomProfil(name);
            profilRepository.save(profil);
        }
    }

	
}
