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
import pds.qflush.apiqflush.service.NotificationServiceImpl;


@Controller
public class NotificationController {

    @Autowired
    private NotificationServiceImpl notificationService;
   

    //******************** GET METHOD ********************

//    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
//    public String index(Model model) {
//        return "index";
//    }
    
    @GetMapping("/notifList")
    public String getAllNotification(Model model) {
            model.addAttribute("Notification", notificationService.findAll());
            System.out.println(notificationService.findAll().size()+" siiiiiiiize");
            return "notifList";
    }



   

}

   

