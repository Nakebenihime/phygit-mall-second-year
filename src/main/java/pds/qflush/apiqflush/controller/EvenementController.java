package pds.qflush.apiqflush.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pds.qflush.apiqflush.model.Evenement;
import pds.qflush.apiqflush.service.EvenementServiceImpl;


@Controller
public class EvenementController {

    @Autowired
    private EvenementServiceImpl evenementService;
   

    //******************** GET METHOD ********************

//    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
//    public String index(Model model) {
//        return "index";
//    }
    
    @GetMapping("/EvenementList")
    public String getAllEvenement(Model model) {
            model.addAttribute("campagne", evenementService.findAll());
            return "EvenementList";
    }



    @GetMapping("/addEvenement")
    public String addEvenement(Model model) {
        model.addAttribute("evenement", new Evenement());
        return "addEvenement";
    }



    @RequestMapping(value="/addEvenement", method=RequestMethod.POST)
    public String evenementSubmit(@ModelAttribute Evenement evenement, Model model) {
    	evenementService.save(evenement);
       return "redirect:/addCampagne";
    }


}

   

