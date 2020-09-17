package pds.qflush.apiqflush.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pds.qflush.apiqflush.model.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    public List<Product> findByTypeIgnoreCaseContainingAndBrandIgnoreCaseContainingAndNameIgnoreCaseContaining(String type, String brand, String name);
    public List<Product> findByTypeIgnoreCaseContainingAndBrandIgnoreCaseContaining(String type,String brand);
    public List<Product> findByTypeIgnoreCaseContainingAndNameIgnoreCaseContaining(String type,String name);
    public List<Product> findByBrandIgnoreCaseContainingAndNameIgnoreCaseContaining(String brand, String name);
    public List<Product> findByTypeIgnoreCaseContaining(String type);
    public List<Product> findByBrandIgnoreCaseContaining(String brand);
    public List<Product> findByNameIgnoreCaseContaining(String name);

    @Query(value = "SELECT * FROM Product p, Price pr WHERE p.price_id=pr.price_id AND pr.ref_price BETWEEN :priceMin AND :priceMax", nativeQuery = true)
    List<Product> findbyPriceBetween(@Param("priceMin") double priceMin,@Param("priceMax") double priceMax);

    @Query("SELECT DISTINCT p.brand FROM Product p")
    List<String> findDistinctBrand();

    public List<Product> findByPrice_RefPriceGreaterThanEqualAndPrice_RefPriceLessThanEqual(double prixMin,double prixMax);
    public List<Product> findByName(String name);
}