package pds.qflush.apiqflush.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;
import pds.qflush.apiqflush.model.DeliveryHistory;
import pds.qflush.apiqflush.model.Product;
import pds.qflush.apiqflush.repository.DeliveryHistoryRepository;
import pds.qflush.apiqflush.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class DeliveryHistoryServiceImplTest {

    @Mock //Mockito mock Object.
    private DeliveryHistoryRepository deliveryHistoryRepository;

    @InjectMocks //setups up controller and injects mock objects into it.
    private DeliveryHistoryServiceImpl deliveryHistoryService;

    private DeliveryHistory deliveryHistory = new DeliveryHistory();

    private Optional<DeliveryHistory>  optionalDeliveryHistory;

    /**
     * Initiate mock objects
     **/
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        deliveryHistory=new DeliveryHistory();
        deliveryHistory.setState("En attente");
        optionalDeliveryHistory=Optional.of(deliveryHistory);


    }
    /**
     * Test for the method save() from deliveryHistoryService
     * when deliveryHistoryService.save(deliveryHistory) is called we verify that save(deliveryhistory) is invoked by deliveryHistoryRepository
     */
    @Test
    public void save_verifyMethodHasBeenInvoked(){
        DeliveryHistory product = mock(DeliveryHistory.class);
        deliveryHistoryService.save(product);
        // verify that save() method has been invoked.
        verify(deliveryHistoryRepository).save(product);
    }

    /**
     * Unit test for the method save() from deliveryHistoryService
     * verify that when deliveryHistoryService.save() is called there is no exception returneds
     */
    @Test
    public void save_throwsException(){
        try{
            when(deliveryHistoryService.save((any(DeliveryHistory.class)))).thenThrow(RuntimeException.class);
            DeliveryHistory deliveryHistory = new DeliveryHistory();
            deliveryHistoryService.save(deliveryHistory);
        } catch(Exception ex){
            assertNotNull(ex);
        }
    }

    @Test
    public void getDeliveryHistory(){

            when(deliveryHistoryRepository.findById((anyLong()))).thenReturn(optionalDeliveryHistory);
            Long l = new Long(2);
            Optional<DeliveryHistory> found = deliveryHistoryService.findById(l);
            assertThat(found.isPresent());

    }






}