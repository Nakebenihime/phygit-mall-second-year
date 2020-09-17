package pds.qflush.apiqflush.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pds.qflush.apiqflush.datamock.DataMockEmplacement;
import pds.qflush.apiqflush.datamock.DataMockEmplacementStore;
import pds.qflush.apiqflush.datamock.DataMockGeneric;
import pds.qflush.apiqflush.datamock.DataMockStore;
import pds.qflush.apiqflush.model.*;
import pds.qflush.apiqflush.service.HoraireServiceImpl;
import pds.qflush.apiqflush.service.LocationServiceImpl;
import pds.qflush.apiqflush.service.LocationStoreServiceImpl;
import pds.qflush.apiqflush.service.StoreServiceImpl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Controller
public class StoreController {

    private String message;
    @Autowired
    private StoreServiceImpl storeServiceImpl;

    @Autowired
    private LocationServiceImpl locationServiceImpl;
    @Autowired
    private HoraireServiceImpl horaireServiceImpl;

    @Autowired
    private LocationStoreServiceImpl locationStoreServiceImpl;

    /**
     * Method which return the page addStore page that contain the form
     * @param model
     * @return html page add store
     */
    @RequestMapping(value = {"/addStorePage"}, method = RequestMethod.GET)
    public String addStorePage(Model model) {
        //list of location
        model.addAttribute("store", new Store());
        model.addAttribute("locations", locationServiceImpl.findAvailableLocation());

        //model.addAttribute("message", message);
        return "addStore";
    }

    /**
     * Method which save the store in the database and redirect the client to the page result
     * @param store
     * @param model
     * @return
     */
    @PostMapping(value = "/addStore")
    public String addStore(@ModelAttribute("store") Store store, Model model) {
//        // we get the location selected and set it to the store
        store.getLocationStore().setLocation(locationServiceImpl.getOne(store.getLocationStore().getLocationStoreId()));
        store.getLocationStore().setStore(store);
        // we set the hours for each day
        store.getHoraire().get(0).setJour("Lundi");
        store.getHoraire().get(0).setStore(store);
        store.getHoraire().get(1).setJour("Mardi");
        store.getHoraire().get(1).setStore(store);
        store.getHoraire().get(2).setJour("Mercredi");
        store.getHoraire().get(2).setStore(store);
        store.getHoraire().get(3).setJour("Jeudi");
        store.getHoraire().get(3).setStore(store);
        store.getHoraire().get(4).setJour("Vendredi");
        store.getHoraire().get(4).setStore(store);
        store.getHoraire().get(5).setJour("Samedi");
        store.getHoraire().get(5).setStore(store);
        store.getHoraire().get(6).setJour("Dimanche");
        store.getHoraire().get(6).setStore(store);

        //If the hour is not define we don't add the day in the database
        Iterator<Horaire> itr = store.getHoraire().iterator();
        while (itr.hasNext())
        {
            Horaire o = itr.next();
            if (o.getHoraireDebut() == null ) {
                itr.remove();
            }
        }

        storeServiceImpl.save(store);
        //affect store to a place (location);
        model.addAttribute("success", "Le magasin " + store.getName()+ " a bien été ajouté");

        return "result";
    }
    
    /**
     * Method which return the page addStore page that contain the form
     * @param model
     * @return html page add store
     */
    @RequestMapping(value = {"/searchStorePage"}, method = RequestMethod.GET)
    public String searchStorePage(Model model) {
        //list of location
        model.addAttribute("searchStore", new SearchStore());

        //model.addAttribute("message", message);
        return "searchStore";
    }

    /**
     * Method which save the store in the database and redirect the client to the page result
     * @param store
     * @param model
     * @return
     * @throws ParseException 
     */
    @PostMapping(value = "/searchStore")
    public String searchStore(@ModelAttribute SearchStore searchStore,Model model) throws ParseException {
    	List<Store> ListResult = new ArrayList<Store>();
       List<Store> ListName = storeServiceImpl.findByName(searchStore.getName());
//       List<Store> s1 = storeServiceImpl.findByHoraire(searchStore.getHoraire().getHoraireDebut(), searchStore.getHoraire().getHoraireFin());
//       for (int i = 0; i<s1.size();i++) {
//    	   System.out.println(s1.get(i));
//       }
       System.out.println(searchStore.getHoraireDebut() + "   " + searchStore.getHoraireFin());
       List<Store> ListHoraire = horaireServiceImpl.findByHoraire(searchStore.getHoraireDebut(),searchStore.getHoraireFin());
       List<Store> ListJour = horaireServiceImpl.findByJour(searchStore.getJour());
       if (ListName != null && ListHoraire != null && ListJour != null) {
    	   System.out.println("Passe 3 champs remplie");
    	   ListName.retainAll(ListHoraire);
    	   ListName.retainAll(ListJour);  
    	   ListResult = ListName;
       }
       if (ListName != null && ListHoraire != null && ListJour == null) {
    	   System.out.println("Name et Horaire rempli");
    	   ListName.retainAll(ListHoraire);
    	   ListResult = ListName;
       }
       if (ListName != null && ListHoraire == null && ListJour == null) {
    	   System.out.println("Name rempli");
    	   ListResult = ListName;
       }
       if (ListName != null && ListHoraire == null && ListJour != null) {
    	   System.out.println("Name rempli et Jour");
    	   ListName.retainAll(ListJour);
    	   ListResult = ListName;
       }
       if (ListName == null && ListHoraire != null && ListJour != null) {
    	   System.out.println("Horaire et Jour rempli");
    	   ListHoraire.retainAll(ListJour);
    	   ListResult = ListHoraire;
       }
       if (ListName == null && ListHoraire != null && ListJour == null) {
    	   System.out.println("Horaire rempli");
    	   ListResult = ListHoraire;
       }
       if (ListName == null && ListHoraire == null && ListJour != null) {
    	   System.out.println("Jour rempli");
    	   ListResult = ListJour;
       }
      
       model.addAttribute("stores", ListResult);

        return "resultSearchStore";
    }
}
