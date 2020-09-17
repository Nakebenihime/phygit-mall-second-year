package pds.qflush.apiqflush.service;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import pds.qflush.apiqflush.model.Campagne;
import pds.qflush.apiqflush.model.Suggestion;
import pds.qflush.apiqflush.repository.CommandLineRepository;
import pds.qflush.apiqflush.repository.SuggestionRepository;

@RunWith(SpringRunner.class)
public class AssociationServiceImplTest {
	
	@InjectMocks
	AssociationServiceImpl associationService;
	
	@Mock
	CommandLineRepository commandLineRepo;
	
	@Mock
	SuggestionRepository suggestionRepo;
	
	@Test
    public void save_returnsNewSuggestion(){
        //tell the stub to return a new customer object.
        when(suggestionRepo.save((any(Suggestion.class)))).thenReturn(new Suggestion());
        Suggestion suggestion = new Suggestion();
        // asserts that the object is not null.
        assertNotNull(suggestionRepo.save(suggestion));
        // verify that save() method has been invoked.
        verify(suggestionRepo).save(any(Suggestion.class));
        // verify that save() method has been invoked once.
        verify(suggestionRepo,times(1)).save(suggestion);
    }
	
	@Test
    public void save_throwsException(){
        try{
            when(suggestionRepo.save((any(Suggestion.class)))).thenThrow(RuntimeException.class);
            Suggestion suggestion = new Suggestion();
            suggestionRepo.save(suggestion);
        } catch(Exception ex){
            assertNotNull(ex);
        }
	}
//        
//        @Test
//        public void findSuggestion(){
//          List<Suggestion> suggestions = suggestionRepo.findAll();
//          associationService.suggestionByPurchase();
//          List<Suggestion> suggestions1 = suggestionRepo.findAll();
//          assertEquals(suggestions, suggestions1);        
//    }

}
