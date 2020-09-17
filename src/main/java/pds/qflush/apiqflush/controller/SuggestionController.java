package pds.qflush.apiqflush.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pds.qflush.apiqflush.service.SuggestionServiceImpl;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SuggestionController {

    @Autowired
    SuggestionServiceImpl suggestionService;

    /**
     * La méthode récupére la session de l'utilisateur et l'envoie au service dédiée.
     * @param model
     * @param request
     * @return
     */
    @GetMapping("/MesSuggestion")
    public String getAllSuggestion(Model model, HttpServletRequest request) {
        Long idcustomer = (Long) request.getSession().getAttribute("id");
        model.addAttribute("MesSuggestions", suggestionService.afficherSuggestion(idcustomer));
        return "MesSuggestions";
    }
}
