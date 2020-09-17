package pds.qflush.apiqflush.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pds.qflush.apiqflush.model.Category;
import pds.qflush.apiqflush.model.Profil;
import pds.qflush.apiqflush.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements ServiceImpl<Category> {

    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Optional<Category> findById(long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public void delete(Category category) {
        categoryRepository.delete(category);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
    
    public List<Category> findByName(String name){
        System.out.println(name);
        return categoryRepository.findByName(name);
    }
}
