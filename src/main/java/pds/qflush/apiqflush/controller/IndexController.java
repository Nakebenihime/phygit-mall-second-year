package pds.qflush.apiqflush.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * IndexController class contains methods to handle index page requests
 */
public class IndexController {

    /**
     * url : /, /index
     * @param model : May be used to add model attributes to a view in Spring MVC
     * @return string : name of the index html template
     */
    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(Model model) {
        return "index";
    }
}
