package pds.qflush.apiqflush.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import pds.qflush.apiqflush.model.*;
import pds.qflush.apiqflush.service.CustomerServiceImpl;
import pds.qflush.apiqflush.service.CommandServiceImpl;
import pds.qflush.apiqflush.service.DeliveryHistoryServiceImpl;
import pds.qflush.apiqflush.service.ModeLivraisonServiceImpl;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * CustomerController class contains methods to handle customer crud requests
 */
@Controller
public class TrackingController {

    @Autowired
    private CustomerServiceImpl customerService;

    @Autowired
    private CommandServiceImpl commandService;

    @Autowired
    private ModeLivraisonServiceImpl modeLivraisonService;

    @Autowired
    private DeliveryHistoryServiceImpl deliveryHistoryService;

    @GetMapping("/commands/{id}")
    public String trackingPage(@PathVariable long id, Model model,HttpServletRequest request){
        Optional<Command> c= commandService.findById(id);
        model.addAttribute("commands",c);
        model.addAttribute("id",id);
        model.addAttribute("deliveryhistory" , new DeliveryHistory());
        List<String> listeStates = new ArrayList<>();
        for(DeliveryHistory d:c.get().getDeliveryHistoryList()){
        listeStates.add(d.getState());
        }
        model.addAttribute("listeStates",listeStates);

        return "trackingOrders";
    }

    @GetMapping("/retirer")
    public String retirerPage(Model model,HttpServletRequest request){
        model.addAttribute("command" , new Command());
        model.addAttribute("deliveryhistory" , new DeliveryHistory());
        model.addAttribute("msg", null);
        return "retirer";
    }

    @RequestMapping(value="/command/search", method= RequestMethod.POST)
    public String retirerSubmit(@ModelAttribute Command command, Model model, HttpServletRequest request) {
        DeliveryHistory deliveryHistory = new DeliveryHistory();
        Optional <Command> command1 = commandService.findById(command.getCommandID());
        if(command1.orElse(null)!=null){
            List<String> listeStates = new ArrayList<>();
            for(DeliveryHistory d:command1.get().getDeliveryHistoryList()){
                listeStates.add(d.getState());
            }
            if (command1.get().getCustomer().getCustomerId()==request.getSession().getAttribute("id")) {

         if(listeStates.contains("Récupérée") ){
            model.addAttribute("msgnondispo", "Votre commande a déjà été récupérée ");
            }
         else {
                     if(listeStates.contains("Disponible en borne" )|| listeStates.contains("Disponible en magasin") || listeStates.contains("Disponible en point relais")) {
                         System.out.println(command.getCommandID());
                         deliveryHistory.setState("Récupérée");
                         deliveryHistory.setCommand(command1.get());
                         deliveryHistoryService.save(deliveryHistory);
                         command1.get().setState("Récupérée");
                         commandService.save(command1.get());
                         model.addAttribute("msg", "Votre commande a été récupérée");
                     }else {
                         model.addAttribute("msgnondispo", "Votre commande n'est pas encore disponible");
                     }
             }
            }else{
                model.addAttribute("msgwronguser", "Ceci n'est pas votre commande !");
            }
        }else {
            model.addAttribute("msgnondispo", "Votre commande n'existe pas !");
        }


        return "retirer";
    }
}
