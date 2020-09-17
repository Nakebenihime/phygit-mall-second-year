package pds.qflush.apiqflush.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pds.qflush.apiqflush.model.ModeLivraison;
import pds.qflush.apiqflush.service.ModeLivraisonServiceImpl;


@Controller
public class ModeLivraisonController {

    @Autowired
    private ModeLivraisonServiceImpl modeLivraisonService;
   

    //******************** GET METHOD ********************

//    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
//    public String index(Model model) {
//        return "index";
//    }
    
    @GetMapping("/ModeLivraisonList")
    public String getAllModeLivraison(Model model) {
            model.addAttribute("modeLivraison", modeLivraisonService.findAll());
            return "ModeLivraisonList";
    }



    @GetMapping("/addModeLivraison")
    public String addModeLivraison(Model model) {
        model.addAttribute("modeLivraison", new ModeLivraison());
        return "addModeLivraison";
    }



    @RequestMapping(value="/addModeLivraison", method=RequestMethod.POST)
    public String modeLivraisonSubmit(@ModelAttribute ModeLivraison modelivraison, Model model) {
    	modeLivraisonService.save(modelivraison);
       return "redirect:/ModeLivraisonList";
    }


}

   

