package pds.qflush.apiqflush.controller;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import pds.qflush.apiqflush.model.Notification;
import pds.qflush.apiqflush.service.CustomerServiceImpl;
import pds.qflush.apiqflush.service.NotificationServiceImpl;


@Controller
public class MesNotifsController {

    @Autowired
    private CustomerServiceImpl customerService;
   

    //******************** GET METHOD ********************

//    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
//    public String index(Model model) {
//        return "index";
//    }
    
    @GetMapping("/MesNotifs")
    public String getAllNotification(Model model,HttpServletRequest request) {
    		Long idcust = (Long) request.getSession().getAttribute("id");
    		//List<Notification> notif =customerService.afficheNotif(idcust);
    		//System.out.println(notif.size()+ "    size mes notifs");
            model.addAttribute("MesNotifs", customerService.afficheNotif(idcust));
            return "MesNotifs";
    }



   

}

   

