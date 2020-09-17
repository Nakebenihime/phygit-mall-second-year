package pds.qflush.apiqflush.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pds.qflush.apiqflush.model.*;
import pds.qflush.apiqflush.repository.CommandRepository;
import pds.qflush.apiqflush.repository.ProductRepository;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



@Service
public class CommandServiceImpl implements ServiceImpl<Command>{

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CommandRepository commandRepository;

    /**
     * Cette metthode permet l'ajout d'un produit au panier
     * @param id
     * @param request
     * @return
     */
    public boolean addCart(Long id, HttpServletRequest request){
        //verication id produit existant
        Product product= productRepository.getOne(id);
        System.out.println(product.toString());
        System.out.println(product.getStockProduct().get(0).toString());
        if(product!=null){
            Cart cart = (Cart) request.getSession().getAttribute("Cart");
            // Creation du panier si il n'existe pas
            if (cart == null) {
                // element du panier
                Long idOfUser = new Long(request.getSession().getAttribute("id").toString());
                cart = new Cart(idOfUser);
                // Creation d'une ligne panier avec le produit choisi
                CartLine item= new CartLine(id,1,product.getPrice().getRefPrice(),product);
                cart.addProduct(item);
                request.getSession().setAttribute("Cart", cart);
                return true;
            }else{
                // le panier existe : donc on ajoute juste un produit  au panier
                CartLine item= new CartLine(id,1,product.getPrice().getRefPrice(),product);
                cart.addProduct(item);
                return  true;
            }
        }
        return  false;
    }

    /**
     * Methode qui met un mode de livraison a la commande et ajoute le prix de la livraison au total de la commande
     * @param customer
     * @param modeLivraison
     * @param request
     * @return
     */
    public Command chooseDelivery(Customer customer,ModeLivraison modeLivraison,HttpServletRequest request) {
        //command.setModeLivraison(modeLivraison);

        Command command=new Command();
        command.setModeLivraison(modeLivraison);
        double prixlivraison= modeLivraison.getTarificationLivraison().get(0).getPrix();
        command.setTotal(prixlivraison);
        command.setCustomer(customer);
        request.getSession().setAttribute("command",command);
        return command;
    }

    /**
     * Cette methode cree les element de la commmande(le ligne de produit)
     * @param request
     * @return retourne la commande avec toutes ses ligen de produit
     */
    public Command commandCreation(HttpServletRequest request){
        Command command =(Command) request.getSession().getAttribute("command");
        //Création des element de la comande
        List<CommandLine> commandLines=new ArrayList<CommandLine>();
        //On récupere le panier en session
        Cart cart = (Cart) request.getSession().getAttribute("Cart");
        //recuperation des elements du panier et creation de la commande
        for(CartLine c: cart.getCartLines()){
            CommandLine line=new CommandLine(c.getProduct(),command,c.getQuantity(),c.getPrice()*c.getQuantity());
            commandLines.add(line);
        }
        System.out.println(command);
        System.out.println(commandLines);
        command.setCommandLines(commandLines);
        // total de la command
        double deliveryPrice= command.getTotal();
        command.setTotal(deliveryPrice+cart.getTotal());
        command.setState("En attente de traitement");
        return command;
    }

    /**
     * validation de la commande la commande est en enregistré en base de donnée et on modidfie la quantité des produit
     * @param request
     * @return
     */
    public Command validateCommand(HttpServletRequest request){
        Command command = (Command)request.getSession().getAttribute("command");;
        System.out.println("methode");
        System.out.println(command);
        Cart cart = (Cart) request.getSession().getAttribute("Cart");
        //mise a jour des quantité en stock
        for(CartLine c: cart.getCartLines()){
            Optional<Product> p=productRepository.findById(c.getId());
            int stock= p.get().getStockProduct().get(0).getQte();
            p.get().getStockProduct().get(0).setQte(stock-c.getQuantity());
            productRepository.save(p.get());
        }
        commandRepository.save(command);
        request.getSession().removeAttribute("Cart");
        request.getSession().removeAttribute("command");
        return command;
    }

    public boolean updateQuantity(Long id,int quantity,HttpServletRequest request){
        Cart cart = (Cart) request.getSession().getAttribute("Cart");
        Optional<Product> p=productRepository.findById(id);
        int stock= p.get().getStockProduct().get(0).getQte();
        if(stock<=quantity) {
            cart.updateProductQuantity(id, quantity);
            return true;
        }
      return false;
    }
    public void deleteCartLine(Long id,HttpServletRequest request ){
        Cart cart = (Cart) request.getSession().getAttribute("Cart");
        cart.removeProduct(cart.findOneProduct(id));
    }


    @Override
    public Command save(Command command) {
        return commandRepository.save(command);
    }

    @Override
    public Optional<Command> findById(long id) {
        return commandRepository.findById(id);
    }

    @Override
    public void delete(Command command) {

    }

    @Override
    public List<Command> findAll() {
        return commandRepository.findAll();
    }

    public List<Command> findAllbyCustomerId(Long id){
        return commandRepository.findAllByCustomer_CustomerId(id);
    }


}
