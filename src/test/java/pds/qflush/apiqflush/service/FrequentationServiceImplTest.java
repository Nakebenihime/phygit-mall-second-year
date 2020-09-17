package pds.qflush.apiqflush.service;


import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.test.context.junit4.SpringRunner;

import pds.qflush.apiqflush.model.Product;
import pds.qflush.apiqflush.repository.ProductRepository;

@RunWith(SpringRunner.class)
public class FrequentationServiceImplTest {
	
	
	@InjectMocks //setups up controller and injects mock objects into it.
    private FrequentationServiceImpl FrequentationService;
	
	@Test
    public void testIfTheDataAreTheOneExpected(){
    	
    }
}
