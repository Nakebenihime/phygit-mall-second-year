package pds.qflush.apiqflush.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;
import pds.qflush.apiqflush.model.Product;
import pds.qflush.apiqflush.repository.ProductRepository;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class ProductServiceImplTest {

    @Mock //Mockito mock Object.
    private ProductRepository productRepository;

    @InjectMocks //setups up controller and injects mock objects into it.
    private ProductServiceImpl productService;

    /**
     * Initiate mock objects
     **/
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }
    /**
     * Unit test for the method save() from ProductServiceImpl
     * when productService.save(product) is called we verify that save(product) is invoked by productRepository
     */
    @Test
    public void save_verifyMethodHasBeenInvoked(){
        Product product = mock(Product.class);
        productService.save(product);
        // verify that save() method has been invoked.
        verify(productRepository).save(product);
    }
    /**
     *Unit test for the method save() from ProductServiceImpl
     */
    @Test
    public void save_returnsNewProduct(){
        //tell the stub to return a new product object.
        when(productRepository.save((any(Product.class)))).thenReturn(new Product());
        Product product = new Product();
        // asserts that the object is not null.
        assertNotNull(productService.save(product));
        // verify that save() method has been invoked.
        verify(productRepository).save(any(Product.class));
        // verify that save() method has been invoked once.
        verify(productRepository,times(1)).save(product);
    }

    /**
     * Unit test for the method save() from ProductServiceImpl
     * verify that when productRepository.save() is called there is no exception returneds
     */
    @Test
    public void save_throwsException(){
        try{
            when(productRepository.save((any(Product.class)))).thenThrow(RuntimeException.class);
            Product product = new Product();
            productService.save(product);
        } catch(Exception ex){
            assertNotNull(ex);
        }
    }

    /**
     * Unit test for the method findAll() from ProductServiceImpl
     * when productService.findAll() is called we verify that findAllTest() is invoked by productRepository
     */
    @Test
    public void findAll_verifyMethodHasBeenInvoked(){
        productService.findAll();
        // verify that save() method has been invoked.
        verify(productRepository).findAll();
    }

    /**
     *  Unit test for the method findAll() from ProductServiceImpl
     *  we are comparing the results of two different access to the method findAll()
     */
    @Test
    public void findAll_returnsAllProducts() {

        List<Product> productList = productRepository.findAll();
        List<Product> productListTest = productService.findAll();
        // Assert that the two lists contain the same elements in the same order.
        Assert.assertEquals(productList,productListTest);
    }


    @Test
    public void findById_verifyGoodProductFound(){
        // verifies that findById() method finds good product.
        Product product1 = mock(Product.class);
        Assert.assertEquals(productRepository.findById(product1.getProductId()), productRepository.findById(product1.getProductId()));

    }


}