package pds.qflush.apiqflush.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;
import pds.qflush.apiqflush.model.Customer;
import pds.qflush.apiqflush.repository.CustomerRepository;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class CustomerServiceImplTest {

    @Mock //Mockito mock Object.
    private CustomerRepository customerRepository;

    @InjectMocks //setups up controller and injects mock objects into it.
    private CustomerServiceImpl customerService;

    /**
     * Initiate mock objects
     **/
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }
    /**
     * Unit test for the method save() from CustomerServiceImpl
     * when customerService.save(product) is called we verify that save(customer) is invoked by customerRepository
     */
    @Test
    public void save_verifyMethodHasBeenInvoked(){
        Customer customer = mock(Customer.class);
        customerService.save(customer);
        // verify that save() method has been invoked.
        verify(customerRepository).save(customer);
    }
    /**
     *Unit test for the method save() from CustomerServiceImpl
     */
    @Test
    public void save_returnsNewCustomer(){
        //tell the stub to return a new customer object.
        when(customerRepository.save((any(Customer.class)))).thenReturn(new Customer());
        Customer customer = new Customer();
        // asserts that the object is not null.
        assertNotNull(customerService.save(customer));
        // verify that save() method has been invoked.
        verify(customerRepository).save(any(Customer.class));
        // verify that save() method has been invoked once.
        verify(customerRepository,times(1)).save(customer);
    }

    /**
     * Unit test for the method save() from CustomerServiceImpl
     * verify that when customerRepository.save() is called there is no exception returneds
     */
    @Test
    public void save_throwsException(){
        try{
            when(customerRepository.save((any(Customer.class)))).thenThrow(RuntimeException.class);
            Customer customer = new Customer();
            customerService.save(customer);
        } catch(Exception ex){
            assertNotNull(ex);
        }
    }

    /**
     * Unit test for the method findAll() from CustomerServiceImpl
     * when customerService.findAll() is called we verify that findAllTest() is invoked by customerRepository
     */
    @Test
    public void findAll_verifyMethodHasBeenInvoked(){
        customerService.findAll();
        // verify that save() method has been invoked.
        verify(customerRepository).findAll();
    }

    /**
     *  Unit test for the method findAll() from ProductServiceImpl
     *  we are comparing the results of two different access to the method findAll()
     */
    @Test
    public void findAll_returnsAllProducts() {

        List<Customer> customerList = customerRepository.findAll();
        List<Customer> customerListTest = customerService.findAll();
        // Assert that the two lists contain the same elements in the same order.
        Assert.assertEquals(customerList,customerListTest);
    }

}
