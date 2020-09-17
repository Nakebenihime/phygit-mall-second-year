package pds.qflush.apiqflush.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pds.qflush.apiqflush.model.Campagne;
import pds.qflush.apiqflush.model.CampagneProfilCategory;
import pds.qflush.apiqflush.model.Category;
import pds.qflush.apiqflush.model.Customer;
import pds.qflush.apiqflush.model.Profil;
import pds.qflush.apiqflush.service.CampagneServiceImpl;
import pds.qflush.apiqflush.service.CategoryServiceImpl;
import pds.qflush.apiqflush.service.CustomerServiceImpl;
import pds.qflush.apiqflush.service.EvenementServiceImpl;
import pds.qflush.apiqflush.service.NotificationServiceImpl;
import pds.qflush.apiqflush.service.ProfilServiceImpl;


@Controller
public class CampagneController {

    @Autowired
    private CampagneServiceImpl campagneService;
    
    @Autowired
    private ProfilServiceImpl profilService;
   
    @Autowired
    private CategoryServiceImpl categoryService;
    
    @Autowired
    private EvenementServiceImpl evenementService;
    
    @Autowired
    private NotificationServiceImpl notificationService;

    @Autowired
    private CustomerServiceImpl customerService;
    //******************** GET METHOD ********************

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(Model model) {
        return "index";
    }
    
    @GetMapping("/CampagneList")
    public String getAllCampagne(Model model) {
            model.addAttribute("campagne", campagneService.findAll());
            return "CampagneList";
    }



    @GetMapping("/addCampagne")
    public String addCampagne(Model model) {
        model.addAttribute("campagneprofilcategory", new CampagneProfilCategory());
        model.addAttribute("profil", profilService.findAll());
        model.addAttribute("category", categoryService.findAll());
        model.addAttribute("evenement", evenementService.findAll());

        return "addCampagne";
    }



    @RequestMapping(value="/addCampagne", method=RequestMethod.POST)
    public String campagneSubmit(@ModelAttribute("campagneprofilcategory") CampagneProfilCategory campagneprofcat, Model model) {
    	campagneprofcat.getCampagne().setEvenement((campagneprofcat.getEvenement()));
    	campagneService.save(campagneprofcat.getCampagne());
    	List<Customer> c = campagneService.find(campagneprofcat);
    	for (int i=0; i<c.size();i++) {
    		System.out.println(c.get(i).getCustomerId());
    	}
    	
    	
       return "redirect:/notifList";
    }




}

   

