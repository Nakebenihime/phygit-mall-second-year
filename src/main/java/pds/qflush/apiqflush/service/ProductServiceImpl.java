package pds.qflush.apiqflush.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import pds.qflush.apiqflush.model.Product;
import pds.qflush.apiqflush.model.SearchProductForm;
import pds.qflush.apiqflush.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ServiceImpl<Product>{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> findById(long id) {
        return productRepository.findById(id);
    }

    @Override
    public void delete(Product product) {
        productRepository.delete(product);
    }

    @Override
    public List<Product> findAll() { return productRepository.findAll();
    }


    public List<String> findDistinctBrand(){ return  productRepository.findDistinctBrand();}

    public List<Product> findByName(String name){ return productRepository.findByName(name);}

    public List<Product> findProduct(SearchProductForm searchProductForm) {
        List<Product> result = new ArrayList<Product>();
        List<Product> result2 = new ArrayList<Product>();
        System.out.println(searchProductForm.getType()+" "+searchProductForm.getBrand()+" "+searchProductForm.getName());
        // All fields are populated
        if(!searchProductForm.getType().equals("") && !searchProductForm.getBrand().equals("") && !searchProductForm.getName().equals("")) {
            result = productRepository.findByTypeIgnoreCaseContainingAndBrandIgnoreCaseContainingAndNameIgnoreCaseContaining(searchProductForm.getType(), searchProductForm.getBrand(), searchProductForm.getName());
            //Fields TYPE and BRAND are populated
        }else if(!searchProductForm.getType().equals("") && !searchProductForm.getBrand().equals("") && searchProductForm.getName().equals("")) {

            result = productRepository.findByTypeIgnoreCaseContainingAndBrandIgnoreCaseContaining(searchProductForm.getType(), searchProductForm.getBrand());

            //Fields TYPE and NAME are populated
        }else if(!searchProductForm.getType().equals("") && searchProductForm.getBrand().equals("") && !searchProductForm.getName().equals("")) {
            result = productRepository.findByTypeIgnoreCaseContainingAndNameIgnoreCaseContaining(searchProductForm.getType(), searchProductForm.getName());
            // Fields BRAND & NAME are populated
        }else if(searchProductForm.getType().equals("") && !searchProductForm.getBrand().equals("") && !searchProductForm.getName().equals("")) {
            result = productRepository.findByBrandIgnoreCaseContainingAndNameIgnoreCaseContaining(searchProductForm.getBrand(), searchProductForm.getName());
            // Field TYPE is populated
        }else if (!searchProductForm.getType().equals("") && searchProductForm.getBrand().equals("") && searchProductForm.getName().equals("")) {
            result = productRepository.findByTypeIgnoreCaseContaining(searchProductForm.getType());
            // Field BRAND
        }else if(searchProductForm.getType().equals("") && !searchProductForm.getBrand().equals("") && searchProductForm.getName().equals("")){
            result = productRepository.findByBrandIgnoreCaseContaining(searchProductForm.getBrand());
            // Field NAME
        }else if(searchProductForm.getType().equals("") && searchProductForm.getBrand().equals("") && !searchProductForm.getName().equals("")){
            result = productRepository.findByNameIgnoreCaseContaining(searchProductForm.getName());
        }
        System.out.println(searchProductForm.getPriceMin()+" "+searchProductForm.getPriceMax());
        if(searchProductForm.getPriceMax()>= 0 || searchProductForm.getPriceMin() >= 0) {
            if(!result.isEmpty()){
                result2 = productRepository.findByPrice_RefPriceGreaterThanEqualAndPrice_RefPriceLessThanEqual(searchProductForm.getPriceMin(),searchProductForm.getPriceMax());
                result.retainAll(result2);
            }else{
                result = productRepository.findByPrice_RefPriceGreaterThanEqualAndPrice_RefPriceLessThanEqual(searchProductForm.getPriceMin(),searchProductForm.getPriceMax());
            }
        }
        return result;
    }
}
