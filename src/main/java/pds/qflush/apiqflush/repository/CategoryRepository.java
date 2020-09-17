package pds.qflush.apiqflush.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pds.qflush.apiqflush.model.Category;
import pds.qflush.apiqflush.model.Profil;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
	 List<Category> findByName(String name);
	
}


