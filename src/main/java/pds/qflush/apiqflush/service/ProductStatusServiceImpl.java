package pds.qflush.apiqflush.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pds.qflush.apiqflush.model.ProductStatus;
import pds.qflush.apiqflush.repository.ProductStatusRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductStatusServiceImpl implements ServiceImpl<ProductStatus> {

    @Autowired
    private ProductStatusRepository productStatusRepository;


    @Override
    public ProductStatus save(ProductStatus productStatus) {
        return productStatusRepository.save(productStatus);
    }

    @Override
    public Optional<ProductStatus> findById(long id) {
        return productStatusRepository.findById(id);
    }

    @Override
    public void delete(ProductStatus productStatus) {
        productStatusRepository.delete(productStatus);
    }

    @Override
    public List<ProductStatus> findAll() {
        return productStatusRepository.findAll();
    }
}
