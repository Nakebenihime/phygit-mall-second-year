package pds.qflush.apiqflush.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pds.qflush.apiqflush.model.Customer;
import pds.qflush.apiqflush.model.Notification;
import pds.qflush.apiqflush.model.Suggestion;
import pds.qflush.apiqflush.repository.CustomerRepository;
import pds.qflush.apiqflush.repository.SuggestionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SuggestionServiceImpl implements ServiceImpl<Suggestion> {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private SuggestionRepository suggestionRepository;

    @Override
    public Suggestion save(Suggestion suggestion) {
        return suggestionRepository.save(suggestion);
    }

    @Override
    public Optional<Suggestion> findById(long id) {
        return suggestionRepository.findById(id);
    }

    @Override
    public void delete(Suggestion suggestion) {
        suggestionRepository.delete(suggestion);
    }

    @Override
    public List<Suggestion> findAll() {
        return suggestionRepository.findAll();
    }

    /**
     * La méthode récupére les suggestions du client à travers son identifiant
     * @param idcustomer
     * @return
     */
    public List<Suggestion> afficherSuggestion(Long idcustomer) {
        Optional<Customer> customer = customerRepository.findById(idcustomer);
        List<Suggestion> suggestions = suggestionRepository.findByCustomer(customer.get());
        return suggestions;

    }
}


