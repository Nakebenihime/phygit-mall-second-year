package pds.qflush.apiqflush.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import pds.qflush.apiqflush.model.*;
import pds.qflush.apiqflush.service.*;

import javax.servlet.http.HttpServletRequest;

import java.util.Optional;

/**
 * CustomerController class contains methods to handle customer crud requests
 */
@Controller
public class CustomerController {

    @Autowired
    private CustomerServiceImpl customerService;

    @Autowired
    private CommandServiceImpl commandService;

    @Autowired
    private ModeLivraisonServiceImpl modeLivraisonService;

    @Autowired
    private ProductServiceImpl productService;

    @Autowired
    private AssociationServiceImpl associationService;

    //******************** GET METHOD ********************


    /**
     * Handles a customer listing request and retrieves a list of all existing customers
     *
     * @param model : May be used to add model attributes to a view in Spring MVC
     * @return string : name of the customer-list html template
     */
    @GetMapping("/customers")
    public String getAllCustomers(Model model) {
        model.addAttribute("customers", customerService.findAll());
        return "customerList";
    }

    @GetMapping("/customers/search")
    public String searchCustomer(Model model) {
        model.addAttribute("customer", new Customer());
        return "searchCustomer";
    }

    /**
     * Handles an customer adding request and initializes web view and add-customer form.
     * Customer and Address models attributes are loaded.
     *
     * @param model : May be used to add model attributes to a view in Spring MVC
     * @return string : name of the add-customer html template
     */
    @GetMapping("/customers/add")
    public String addCustomer(Model model) {
        model.addAttribute("customer", new Customer());
        return "addCustomer";
    }


    //******************** POST METHOD ********************

    /**
     * Handles a customer model submitted from the add-customer view form.
     * Customer object is saved using a customer service layer method.
     *
     * @param customer : Customer object submitted by the user
     * @return : redirects to customer list
     */
    @RequestMapping(value = "/customers", method = RequestMethod.POST)
    public String customerSubmit(@ModelAttribute Customer customer) {
        customerService.save(customer);
        return "redirect:/customers";
    }

    /**
     * Page panier
     * @param model
     * @param request
     * @return
     */
    @GetMapping("/shoppingCart")
    public String shoppingCart(Model model,HttpServletRequest request) {
        Cart cart = (Cart) request.getSession().getAttribute("Cart");
        model.addAttribute("update",new CartLine());
        model.addAttribute("cart", cart);
        return "shoppingCart";
    }
    //

    /**
     * Ajout produit au panier // etape 1 ajouter prduit au panier
     * @param id
     * @param model
     * @param request
     * @return
     */
    @GetMapping("/addCart/{id}")
    public String addCart(@PathVariable long id,Model model, HttpServletRequest request){
        commandService.addCart(id,request);
        Cart cart = (Cart) request.getSession().getAttribute("Cart");
        System.out.println(cart.getCartLines().get(0).toString());
        model.addAttribute("cart",cart);
        return "shoppingCart";
    }
    //etape 2 choisir mode de livraison page

    /**
     * Etape 2 Redirection a la validation du panier vers la page choix de livraison
     * @param model
     * @param request
     * @return
     */
    @GetMapping("/shoppingCart/deliveryChoice")
    public String deliveryChoice(Model model,HttpServletRequest request){
        model.addAttribute("ListDelivery",modeLivraisonService.findAll());
        model.addAttribute("modeLivraison",new ModeLivraison());
        return "deliveryChoice";
    }

    /**
     * Validation du choix de livraison puis redirection vers la page de paiement
     * @param modeLivraison
     * @param model
     * @param request
     * @return
     */
    @PostMapping("/shoppingCart/deliveryChoice/validate")
    public String validateChoiceDelivery(@ModelAttribute ModeLivraison modeLivraison,Model model,HttpServletRequest request){
        // on creer un commande a laquel on va mettre le type de livraison
        Optional<ModeLivraison> modeLivraisonFind = modeLivraisonService.findById(modeLivraison.getIdModeLivraison());
        Long idCustomer= (Long) request.getSession().getAttribute("id");
        Optional<Customer> customer =customerService.findById(idCustomer);
        Command c =commandService.chooseDelivery(customer.get(),modeLivraisonFind.get(),request);
        request.setAttribute("command",c);
        //on redirige vers la page de paiement ;
        return "redirect:/shoppingCart/payment";
    }
    //etape 3 paiement
    // page de paiement

    /**
     * Page de paiement
     * @param model
     * @param request
     * @return
     */
    @GetMapping("/shoppingCart/payment")
    public String payment(Model model,HttpServletRequest request){

        return "payment";
    }

    /**
     * Validation moyen de paiement puis redirection page recap de la commande
     * @return
     */
    @PostMapping("/shoppingCart/payment")
    public String validatePayment(){
        // methode de payment
        return "redirect:/shoppingCart/recap";
    }

    /**
     * Page de recap avant validation
     * @param model
     * @param request
     * @return
     */
    @GetMapping("/shoppingCart/recap")
    public String recap(Model model,HttpServletRequest request){

        Command command = commandService.commandCreation(request);
        // on retourne la commande dans une page de récapitulatif
        request.setAttribute("command",command);
        model.addAttribute("command",command);
        //System.out.println(command.getCommandLines().get(0).getQuantity());
        return "commandRecap";
    }

    /**
     * Validation de commande et reidrection page confirmation de la commande
     * @param model
     * @param request
     * @return
     */
    @GetMapping("/shoppingCart/confirmation")
    public String validationCommand(Model model,HttpServletRequest request){
        associationService.suggestionByPurchase(request);
        // on reduit le stock des produits selectionner
        Command d = (Command) request.getAttribute("command");
        Command c =commandService.validateCommand(request);
        model.addAttribute("command",c);
        return "commandConfirmation";
    }

    /**
     * Liste des commande du client
     * @param model
     * @param request
     * @return
     */
    @GetMapping("/commands")
    public String commandList(Model model,HttpServletRequest request){
        Long id= (Long) request.getSession().getAttribute("id");
        model.addAttribute("commands",commandService.findAllbyCustomerId(id));
        return "commandList";
    }

    @PostMapping("/shoppingCart/updateQuantity")
    public String updateCartLine(@ModelAttribute CartLine cartLine,HttpServletRequest request){
        commandService.updateQuantity(cartLine.getId(),cartLine.getQuantity(),request);
        return "redirect :/shoppingCart";
    }

    //@PostMapping("/deleteCartLine/{id}")
    //public String deleteCartLine(@PathVariable long id,HttpServletRequest request){
      //  commandService.deleteCartLine(id,request);
        //return "redirect :/shoppingCart";
    //}





}