package pds.qflush.apiqflush.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pds.qflush.apiqflush.model.CommandLine;

import java.util.List;

@Repository
public interface CommandLineRepository extends JpaRepository<CommandLine, Long> {
    List<CommandLine> findAll();
    List<CommandLine> findCommandLineByCommand_Customer_CustomerId(long id);
}
