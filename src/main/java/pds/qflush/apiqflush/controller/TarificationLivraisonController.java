package pds.qflush.apiqflush.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pds.qflush.apiqflush.model.*;
import pds.qflush.apiqflush.service.ModeLivraisonServiceImpl;
import pds.qflush.apiqflush.service.TarificationLivraisonServiceImpl;


@Controller
public class TarificationLivraisonController {

    @Autowired
    private TarificationLivraisonServiceImpl tarificationLivraisonService;
    
    @Autowired
    private ModeLivraisonServiceImpl modeLivraisonService;
    
    FormulaireSearchLivraison form = new FormulaireSearchLivraison();
   

    @GetMapping("/TarificationLivraisonList")
    public String getAllProducts(Model model) {
            model.addAttribute("TarificationLivraison", tarificationLivraisonService.findAll());
            return "TarificationLivraisonList";
    }
    
    
    @GetMapping("/formulaireSearchLivraison")
    public String getFilteredLivraison(Model model) {
    	model.addAttribute("FormulaireSearchLivraison", new FormulaireSearchLivraison());
    	return "formulaireSearchLivraison";
    }
    
    @RequestMapping(value="/formulaireSearchLivraison", method=RequestMethod.POST)
    public String getFilteredLivraisonSubmit(@ModelAttribute FormulaireSearchLivraison fsl, Model model) {
    	List<TarificationLivraison> tarifsLivraison = tarificationLivraisonService.findAll();
    	List<TarificationLivraison> filteredLivraisons = tarificationLivraisonService.getFilteredModeLivraison(tarifsLivraison, fsl.getPrixMax(), fsl.getDureeMax(), fsl.getNomService());
    	model.addAttribute("TarificationLivraison" , filteredLivraisons);
    	if(filteredLivraisons.isEmpty()) {
    		return "NoResult";
    	} else {
    		return "TarificationLivraisonList";
    	}
    }
    

    @GetMapping("/addTarificationLivraison")
    public String addTarificationLivraison(Model model) {
        model.addAttribute("TarificationLivraison", new TarificationLivraison());
        List<ModeLivraison> modeLivraisons = modeLivraisonService.findAll();
        model.addAttribute("modeLivraisons", modeLivraisons);
        return "addTarificationLivraison";
    }



    @RequestMapping(value="/addTarificationLivraison", method=RequestMethod.POST)
    public String TarificationLivraisonSubmit(@ModelAttribute TarificationLivraison tarificationlivraison, Model model) {
    	tarificationLivraisonService.save(tarificationlivraison);
       return "redirect:/TarificationLivraisonList";
    }


}


