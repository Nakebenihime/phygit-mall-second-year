package pds.qflush.apiqflush.service;

import java.util.List;
import java.util.Optional;

import pds.qflush.apiqflush.model.Store;

public interface ServiceImpl<T>{

    T save(T t);
    Optional<T> findById(long id);
    void delete(T t);
    List<T> findAll();


}
