package pds.qflush.apiqflush.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pds.qflush.apiqflush.model.Sensor;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Long> {
}
