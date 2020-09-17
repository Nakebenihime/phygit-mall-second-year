package pds.qflush.apiqflush.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pds.qflush.apiqflush.model.*;
import pds.qflush.apiqflush.repository.CommandLineRepository;
import pds.qflush.apiqflush.repository.SuggestionRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AssociationServiceImpl {

    @Autowired
    CommandLineRepository commandLineRepository;

    @Autowired
    CampagneServiceImpl campagneService;

    @Autowired
    SuggestionRepository suggestionRepository;

    @Autowired
    CustomerServiceImpl customerService;

    /**
     * La méthode récupère les commandes de tous les clients
     *
     * @return les commandes de tous les clients.
     */
    public List<CommandLine> findAll() {
        return commandLineRepository.findAll();
    }

    /**
     * La méthode récupère les commandes d'un client à partir de sa session lorsque celui-ci a validé la commande.
     *
     * @param request
     * @return les commandes du client.
     */
    public List<CommandLine> findCommandByIdClient(HttpServletRequest request) {
        long id = (long) request.getSession().getAttribute("id");
        return commandLineRepository.findCommandLineByCommand_Customer_CustomerId(id);
    }

    /**
     * Envoi une suggestion au client après un achat.
     * @param request
     */
    @SuppressWarnings("Duplicates")
    public void suggestionByPurchase(HttpServletRequest request) {
        List<CommandLine> commandByIdClient = findCommandByIdClient(request);
        List<CommandLine> commandLineList = commandLineRepository.findAll();

        Map<Product, Long> unsortedMap = campagneService.countObjects(commandLineList);
        Map<Product, Long> sortedMap = campagneService.sortObjects(unsortedMap);

        List<Product> bestProducts = new ArrayList<>(sortedMap.keySet());
        List<Category> bestCategory = new ArrayList<>();

        Long idCustomer = (Long) request.getSession().getAttribute("id");
        Optional<Customer> customer = customerService.findById(idCustomer);


        for (int i = 0; i < bestProducts.size(); i++) {
            bestCategory.add(bestProducts.get(i).getCategory());
        }

        int cpt = 0;
        Suggestion suggestion = new Suggestion();
        for (int k = 0; k < commandByIdClient.size(); k++) {
            for(int j = 0; j < 10; j++) {
                if (bestCategory.get(j) == commandByIdClient.get(k).getProduct().getCategory()) {
                    suggestion.setProduct(bestProducts.get(j));
                    suggestion.setCustomer(customer.get());
                        suggestionRepository.save(suggestion);
                        cpt++;
                }
            if (cpt > 3) {
                break;
            }}
        }
    }
}

