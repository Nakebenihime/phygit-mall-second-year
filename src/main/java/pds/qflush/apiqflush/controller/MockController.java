package pds.qflush.apiqflush.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pds.qflush.apiqflush.datamock.*;
import pds.qflush.apiqflush.service.*;


@Controller
public class MockController {

    @Autowired
    StoreServiceImpl storeService;

    @Autowired
    LocationServiceImpl locationService;

    @Autowired
    LocationStoreServiceImpl locationStoreService;

    @Autowired
    CustomerServiceImpl customerService;

    @Autowired
    ProfilServiceImpl profilService;

    @Autowired
    CustomerProfilServiceImpl customerProfilService;

    @Autowired
    CategoryServiceImpl categoryService;
    @Autowired
    ProductServiceImpl productService;
    @Autowired
    StockProductServiceImpl stockProductService;
    @Autowired
    ProductStatusServiceImpl productStatusService;
    @Autowired
    CommandServiceImpl commandService;
    @Autowired
    ModeLivraisonServiceImpl modeLivraisonService;


    @RequestMapping(path = "feedmock")
    public String generateData() {
        /*customerService.generateCustomer(100);
        profilService.generateProfile();
        customerProfilService.generateCustomerProfile();
        DataMockEmplacement.generateLocationData(locationService);
        DataMockStore.generateStoreData(storeService);
        DataMockEmplacementStore.generateEmplacementStore(locationStoreService, locationService, storeService);*/
        DataMockProduct.generateProductData(productService,categoryService,productStatusService,storeService,stockProductService);
        //System.out.println(DataMockGeneric.generateRandomDouble(19,1000));
        DataMockCommand.generateOrder(2,51,productService,commandService,customerService,modeLivraisonService);
        return "redirect:/";
    }

}
