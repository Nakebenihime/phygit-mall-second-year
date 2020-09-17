package pds.qflush.apiqflush.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;
import pds.qflush.apiqflush.model.*;
import pds.qflush.apiqflush.repository.ProductRepository;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.setRemoveAssertJRelatedElementsFromStackTrace;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class SearchProductServiceTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    List<Product> productList = new ArrayList<Product>();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        // Product 1 mock
        Price pr1 = new Price();
        pr1.setRefPrice(699.0);
        Product p1 = new Product("Apple","iPhone 4","Smartphone",pr1);

        productList.add(p1);

        // Product 2 mock
        Price pr2 = new Price();
        pr2.setRefPrice(700);
        Product p2 = new Product("Apple","iPhone 5","Smartphone",pr2);

        productList.add(p2);

        // Product 3 mock
        Price pr3 = new Price();
        pr3.setRefPrice(1099);
        Product p3 = new Product("Apple","iPhone X","Smartphone",pr3);
        productList.add(p3);

        // Product 4 mock
//        Product p4 = new Product();
//        p4.setBrand("Lays");
//        p4.setName("Chips&Co");
//        p4.setType("Chips");
//        Price pr4 = new Price();
//        pr2.setRefPrice(350);
//        p4.setPrice(pr4);
//        productList.add(p4);

    }

    @Test
    public void findByBrandTest(){
        when(productRepository.findByBrandIgnoreCaseContaining("Apple")).thenReturn(productList);
        SearchProductForm searchForm = new SearchProductForm();
        searchForm.setBrand("Apple");
        searchForm.setName("");
        searchForm.setType("");
        searchForm.setPriceMax(-1.0);
        searchForm.setPriceMin(-1.0);


        List<Product> found = productService.findProduct(searchForm);
        assertThat(found.size()).isEqualTo(3);
    }
    @Test
    public void findByTypeTest(){
        when(productRepository.findByTypeIgnoreCaseContaining("Smartphone")).thenReturn(productList);
        SearchProductForm searchForm = new SearchProductForm();
        searchForm.setBrand("");
        searchForm.setType("Smartphone");
        searchForm.setName("");
        searchForm.setPriceMax(-1.0);
        searchForm.setPriceMin(-1.0);
        List<Product> found = productService.findProduct(searchForm);
        assertThat(found.size()).isEqualTo(3);
    }

    @Test
    public void findByNameTest(){
        when(productRepository.findByNameIgnoreCaseContaining("iphone")).thenReturn(productList);
        SearchProductForm searchForm = new SearchProductForm();
        searchForm.setName("iphone");
        searchForm.setBrand("");
        searchForm.setType("");
        searchForm.setPriceMax(-1.0);
        searchForm.setPriceMin(-1.0);
        List<Product> found = productService.findProduct(searchForm);
        assertThat(found.size()).isEqualTo(3);
    }

    @Test
    public void findByTypeAndNameTest(){
        when(productRepository.findByTypeIgnoreCaseContainingAndNameIgnoreCaseContaining("smartphone","iphone")).thenReturn(productList);
        SearchProductForm searchForm = new SearchProductForm();
        searchForm.setType("smartphone");
        searchForm.setName("iphone");
        searchForm.setBrand("");
        searchForm.setPriceMax(-1.0);
        searchForm.setPriceMin(-1.0);
        List<Product> found = productService.findProduct(searchForm);
        assertThat(found.size()).isEqualTo(3);
    }


    @Test
    public void findByTypeAndBrandTest(){
        when(productRepository.findByTypeIgnoreCaseContainingAndBrandIgnoreCaseContaining("smartphone","apple")).thenReturn(productList);
        SearchProductForm searchForm = new SearchProductForm();
        searchForm.setType("smartphone");
        searchForm.setName("");
        searchForm.setBrand("apple");
        searchForm.setPriceMax(-1.0);
        searchForm.setPriceMin(-1.0);
        List<Product> found = productService.findProduct(searchForm);
        assertThat(found.size()).isEqualTo(3);
    }
}
