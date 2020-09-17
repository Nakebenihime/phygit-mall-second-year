package pds.qflush.apiqflush.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pds.qflush.apiqflush.model.Command;
import pds.qflush.apiqflush.model.DeliveryHistory;
import pds.qflush.apiqflush.model.Product;
import pds.qflush.apiqflush.service.CommandServiceImpl;
import pds.qflush.apiqflush.service.CustomerServiceImpl;
import pds.qflush.apiqflush.service.DeliveryHistoryServiceImpl;
import pds.qflush.apiqflush.service.ModeLivraisonServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * CustomerController class contains methods to handle customer crud requests
 */
@Controller
public class CommandController {



    @Autowired
    private CommandServiceImpl commandService;

    @Autowired
    private DeliveryHistoryServiceImpl deliveryHistoryService;

    @GetMapping("/commandsList")
    public String allCommands(Model model,HttpServletRequest request){
        model.addAttribute("commands",commandService.findAll());
        return "commandList";
    }

    @RequestMapping(value="/updatehistory", method= RequestMethod.POST)
    public String deliverySubmit(@RequestParam("idC") String id,@ModelAttribute DeliveryHistory deliveryHistory, Model model) {
        Long idC = Long.parseLong(id);
        Optional<Command> oneCommand = commandService.findById(idC);
        deliveryHistory.setCommand(oneCommand.get());
        deliveryHistoryService.save(deliveryHistory);
        oneCommand.get().setState(deliveryHistory.getState());
        commandService.save(oneCommand.get());
        return "redirect:/commandsList";
    }
}
