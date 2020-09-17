package pds.qflush.apiqflush.service;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pds.qflush.apiqflush.model.*;
import pds.qflush.apiqflush.repository.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static java.util.Map.Entry.comparingByKey;
import static java.util.stream.Collectors.toMap;

@Service
public class CampagneServiceImpl implements ServiceImpl<Campagne>{
	@Autowired
    private CampagneRepository campagneRepository;
	
	@Autowired
    private NotificationRepository notificationRepository;

	
	@Autowired
    private ProfilServiceImpl profilService;
	
	@Autowired
    private CustomerProfilServiceImpl customerProfilService;
	
	@Autowired
    private CustomerServiceImpl customerService;
	
	@Autowired
    private CategoryServiceImpl categoryService;

	@Autowired
	private CommandLineRepository commandLineRepository;

	@Autowired
	private SuggestionRepository suggestionRepository;
	

    @Override
    public Campagne save(Campagne campagne) {
        return campagneRepository.save(campagne);
    }

    @Override
    public Optional<Campagne> findById(long id) {
        return campagneRepository.findById(id);
    }

    @Override
    public void delete(Campagne campagne) {
    	campagneRepository.delete(campagne);
    }

    @Override
    public List<Campagne> findAll() {
        return campagneRepository.findAll();
    }

	@SuppressWarnings("Duplicates")
	public List<Customer> find(CampagneProfilCategory c){

    	List<Customer> customers = new ArrayList<>();
    	List<Category> cat = c.getCategory();
    	List<CustomerProfil> cp = customerProfilService.findAll();
    	
    	
    	if(c.getCategory().size()==0 && c.getProfil().size() == 0) {
    		List<Customer> allcust = customerService.findAll();
    		for (int i = 0; i<allcust.size();i++) {
    			customers.add(allcust.get(i));
    			Notification notif = new Notification();
    			notif.setCampagne(c.getCampagne());
    			notif.setCustomer(allcust.get(i));
    			notificationRepository.save(notif);
    		}
    	}
    	if(c.getProfil()!=null && !c.getCampagne().getSingleCheckboxField()){
			System.out.println("checkbox is not toggled");
		}
    	
    	if(c.getProfil()!=null && c.getCampagne().getSingleCheckboxField()) {
    		List<CommandLine> commandLinesById;
			List<CommandLine> commandLineList = commandLineRepository.findAll();
			Map<Product,Long> unsortedMap = countObjects(commandLineList);
			Map<Product,Long> sortedMap = sortObjects(unsortedMap);
			List<Product> bestProducts = new ArrayList<>(sortedMap.keySet());
			List<Category> bestCategory = new ArrayList<>();
            for (int i = 0; i < bestProducts.size();i++){
                bestCategory.add(bestProducts.get(i).getCategory());
            }

    	for (int i=0; i<cp.size(); i++) {
    		if ((c.getProfil().contains(cp.get(i).getProfil()))  ) {
    			if (!customers.contains(cp.get(i).getCustomer())) {
	    			Notification notif = new Notification();
	    			Suggestion suggestion = new Suggestion();
	    			customers.add(cp.get(i).getCustomer());
	    			commandLinesById = commandLineRepository.findCommandLineByCommand_Customer_CustomerId(cp.get(i).getCustomer().getCustomerId());
	    			int cpt =0;
	    			for(int k = 0; k < commandLinesById.size(); k++){
	    				for (int j = 0; j < 10; j++){
	    					if(bestCategory.get(j)==commandLinesById.get(k).getProduct().getCategory()){
								suggestion.setProduct(bestProducts.get(j));
								suggestion.setCustomer(cp.get(i).getCustomer());
								suggestionRepository.save(suggestion);
								cpt++;
							}
						if(cpt > 3){
							break;
						}}
					}
	    			notif.setCampagne(c.getCampagne());
	    			notif.setProfil(cp.get(i).getProfil());
	    			notif.setCustomer(cp.get(i).getCustomer());
	    			notificationRepository.save(notif); 
    			}
    		}
    	}
    	}
    	if (cat !=null) {
//    		List<Category> categ = categoryService.findByName(cat.getName());

    		for(int i=0; i<cat.size();i++) {
    		Profil prof = cat.get(i).getProfil();
    		List<Profil> profils = profilService.findByNomProfil(prof.getNomProfil());
    		for (int j=0; j<cp.size(); j++) {
    		if(profils.contains(cp.get(j).getProfil())) {
    			if (!customers.contains(cp.get(j).getCustomer())) {

	    			Notification notif = new Notification();
	    			customers.add(cp.get(j).getCustomer());
	    			notif.setCampagne(c.getCampagne());
	    			notif.setProfil(cp.get(j).getProfil());
	    			notif.setCustomer(cp.get(j).getCustomer());
	    			notificationRepository.save(notif); 
    			}
    			
    		}
    		}
    		
    		}
    		
    		
    	}
		return customers;
    	
    	
    }

    public Map<Product,Long> countObjects(List<CommandLine> commandLineList){
		List<Product> productList = new ArrayList<>();
		for (int i=0;i<commandLineList.size();i++){
			Product product = commandLineList.get(i).getProduct();
			productList.add(product);
		}
		Map<Product,Long> unsortedMap = productList.stream().collect(Collectors.groupingBy(e -> e, Collectors.counting()));
		return unsortedMap;
	}

	public Map<Product, Long> sortObjects(Map<Product,Long> unsortedMap){
    	Map<Product,Long> sortedMap = unsortedMap.entrySet().stream().sorted(Map.Entry.<Product,Long> comparingByValue().reversed()).collect(toMap(Map.Entry::getKey,Map.Entry::getValue,(e1,e2) -> e1, LinkedHashMap::new));
    	return sortedMap;
	}
}
