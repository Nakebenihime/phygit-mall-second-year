package pds.qflush.apiqflush.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pds.qflush.apiqflush.model.Category;
import pds.qflush.apiqflush.model.StockProduct;
import pds.qflush.apiqflush.repository.CategoryRepository;
import pds.qflush.apiqflush.repository.StockProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StockProductServiceImpl implements ServiceImpl<StockProduct> {

    @Autowired
    private StockProductRepository stockProductRepository;

    @Override
    public StockProduct save(StockProduct stockProduct) {
        return stockProductRepository.save(stockProduct);
    }

    @Override
    public Optional<StockProduct> findById(long id) {
        return Optional.empty();
    }

    @Override
    public void delete(StockProduct stockProduct) {

    }

    @Override
    public List<StockProduct> findAll() {
        return null;
    }
}
