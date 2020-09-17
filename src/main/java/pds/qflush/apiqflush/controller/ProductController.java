package pds.qflush.apiqflush.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pds.qflush.apiqflush.model.Product;
import pds.qflush.apiqflush.model.SearchProductForm;
import pds.qflush.apiqflush.service.CategoryServiceImpl;
import pds.qflush.apiqflush.service.PriceServiceImpl;
import pds.qflush.apiqflush.service.ProductServiceImpl;
import pds.qflush.apiqflush.service.ProductStatusServiceImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * ProductController class contains methods to handle product crud requests
 */
@Controller
public class ProductController {

    @Autowired
    private ProductServiceImpl productService;
    @Autowired
    private CategoryServiceImpl categoryService;
    @Autowired
    private PriceServiceImpl priceService;
    @Autowired
    private ProductStatusServiceImpl productStatusService;



    //******************** GET METHOD ********************


    /**
     * Handles a product listing request and retrieves a list of all existing products
     * @param model : May be used to add model attributes to a view in Spring MVC
     * @return string : name of the product-list html template
     */
    @GetMapping("/products")
    public String getAllProducts(Model model) {
            List<String> ListNameStore = new ArrayList<>();
             for(Product p : productService.findAll()){
                if(!ListNameStore.contains(p.getName())){
                    ListNameStore.add(p.getName());
                }
             }
            model.addAttribute("listProduct",ListNameStore);
            return "productList";
    }
    @GetMapping("/products/{name}")
    public String getProductInformation(@PathVariable String name, Model model) {
        model.addAttribute("products", productService.findByName(name));
        return "productInformation";
    }

    /**
     * Handles an product adding request and initializes web view and add-product form.
     * Product model attributes, categories ans status are loaded.
     * @param model : May be used to add model attributes to a view in Spring MVC
     * @return string : name of the add-product html template
     */
    @GetMapping("/products/add")
    public String addProduct(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("status", productStatusService.findAll());
        return "addProduct";
    }

    @GetMapping("/products/search")
    public String searchProduct(Model model){
        model.addAttribute("search", new SearchProductForm());
        return "searchProduct";
    }
    //******************** POST METHOD ********************

    /**
     * Handles a product model submitted from the add-product view form.
     * Product object is saved using a product service layer method.
     * @param product : Product object submitted by the user
     * @param model : May be used to add model attributes to a view in Spring MVC
     * @return : redirects to product list
     */
    @RequestMapping(value="/products", method=RequestMethod.POST)
    public String productSubmit(@ModelAttribute Product product, Model model) {
        productService.save(product);
        return "redirect:/products";
    }

    @RequestMapping(value="/products/search", method = RequestMethod.POST)
    public String searchSubmit(@ModelAttribute SearchProductForm searchProductForm,Model model){
         model.addAttribute("products",productService.findProduct(searchProductForm));
         List<String> L = new ArrayList<>();
         for(Product p : productService.findProduct(searchProductForm)){
             if(!L.contains(p.getName())){
                 L.add(p.getName());
             }
         }
         model.addAttribute("listProduct",L);
         return "productList";
    }

}