package pds.qflush.apiqflush.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pds.qflush.apiqflush.model.Sensor;
import pds.qflush.apiqflush.repository.SensorRepository;

@Service
public class SensorServiceImpl implements ServiceImpl<Sensor> {

    @Autowired
    private SensorRepository SensorRepository;

    @Override
    public Sensor save(Sensor Sensor) {
        return SensorRepository.save(Sensor);
    }

    @Override
    public Optional<Sensor> findById(long id) {
        return SensorRepository.findById(id);
    }

    @Override
    public void delete(Sensor Sensor) {
        SensorRepository.delete(Sensor);
    }

    @Override
    public List<Sensor> findAll() {
        return SensorRepository.findAll();
    }
}
