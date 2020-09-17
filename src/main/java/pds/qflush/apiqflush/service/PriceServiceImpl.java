package pds.qflush.apiqflush.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pds.qflush.apiqflush.model.Price;
import pds.qflush.apiqflush.repository.PriceRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PriceServiceImpl implements ServiceImpl<Price> {


    @Autowired
    private PriceRepository priceRepository;


    @Override
    public Price save(Price price) {
        return priceRepository.save(price);
    }

    @Override
    public Optional<Price> findById(long id) {
        return priceRepository.findById(id);
    }

    @Override
    public void delete(Price price) {
        priceRepository.delete(price);
    }

    @Override
    public List<Price> findAll() {
        return priceRepository.findAll();
    }
}
