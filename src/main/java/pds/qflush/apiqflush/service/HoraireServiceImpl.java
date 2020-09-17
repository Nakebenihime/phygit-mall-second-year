package pds.qflush.apiqflush.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import pds.qflush.apiqflush.model.Horaire;
import pds.qflush.apiqflush.model.Store;
import pds.qflush.apiqflush.repository.HoraireRepository;
import pds.qflush.apiqflush.repository.StoreRepository;

@Service
public class HoraireServiceImpl implements ServiceImpl<Horaire>  {
	   @Autowired
	    private HoraireRepository horaireRepository;
	
	
	@Override
	public Horaire save(Horaire t) {
		return horaireRepository.save(t);
	}

	@Override
	public Optional<Horaire> findById(long id) {
		return horaireRepository.findById(id);
	}

	@Override
	public void delete(Horaire t) {
		horaireRepository.delete(t);
	}

	@Override
	public List<Horaire> findAll() {
		return horaireRepository.findAll();
		}
	
	public List<Store> findByHoraire(Date horaireDebut,Date horaireFin) throws ParseException{
		System.out.println(horaireDebut + "    "  + horaireFin);
		if (horaireDebut != null && horaireFin != null) {
			System.out.println("HoraireDebut et HoraireFin different null");

			return horaireRepository.findByHoraire( horaireDebut,  horaireFin);
		}
		else if (horaireDebut == null && horaireFin != null) {
			System.out.println("horaireDebut is null et horairefin non null");
			return horaireRepository.findByHoraireFin(horaireFin);
		}
		else if (horaireDebut != null && horaireFin == null) {
			System.out.println("HoraireDebut is not null et horarefin null");
			return horaireRepository.findByHoraireDebut(horaireDebut);
		}
		return null;
	}
	
	public List<Store> findByJour(String jour){
		if (!jour.equals("")) {
			return horaireRepository.findByJour(jour);
		}
		return null;
	}

}
