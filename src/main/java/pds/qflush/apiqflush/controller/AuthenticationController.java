package pds.qflush.apiqflush.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pds.qflush.apiqflush.model.Customer;
import pds.qflush.apiqflush.service.CustomerServiceImpl;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AuthenticationController {
    @Autowired
    CustomerServiceImpl customerService;


    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("customer", new Customer());
        return "login";
    }
    @PostMapping("/login")
    public ModelAndView connect(@ModelAttribute Customer customer, Model model, HttpServletRequest request, RedirectAttributes attributes){
        ModelAndView modelAndView ;
        String page = null;
        if(customerService.connexion(customer,request)){
            modelAndView = new ModelAndView("index");
        }else {
            modelAndView = new ModelAndView("login");
            modelAndView.addObject("errorMsg", "Cette email n'existe pas veuillez saisir une adresse valide ");
        }
        return modelAndView; // 2
    }

    @RequestMapping(value="/destroy", method = RequestMethod.GET)
    public String destroySession(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/login";
    }

}
