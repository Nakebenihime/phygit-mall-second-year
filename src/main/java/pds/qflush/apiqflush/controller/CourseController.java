package pds.qflush.apiqflush.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pds.qflush.apiqflush.model.LocationStore;
import pds.qflush.apiqflush.model.Store;
import pds.qflush.apiqflush.service.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

@Controller
public class CourseController {

    @Autowired
    private ProductServiceImpl productService;
    @Autowired
    private CategoryServiceImpl categoryService;
    @Autowired
    private StoreServiceImpl storeService;
    @Autowired
    private CourseServiceImpl courseService;
    @Autowired
    private LocationStoreServiceImpl locationStoreService;

    /**
     * Handles a course form request and retrieves a form to customize a course.
     * @param model : May be used to add model attributes to a view in Spring MVC
     * @return string : name of the findCourse html template
     */
    @GetMapping("/course")
    public String getCourseForm(Model model) {
        //model.addAttribute("brands", productService.findDistinctBrand());
        //model.addAttribute("categories", categoryService.findAll());
        List<Store> stores = new ArrayList<Store>();
        for (LocationStore ls : locationStoreService.findAllOrder()) {
            stores.add(ls.getStore());
        }
        model.addAttribute("stores", stores);
        return "findCourse";
    }



    /**
     * This method handle the ajax request when a user ask for a customized course in the mall.
     * The request and the response are formatted in json
     * @param body contains the request of the user. The criteria are set in a json object.
     *  #1 - duration : maximum time to spend in the mall
     *  #2 - stores : array of store required by the customer
     *
     * @return array of json objects containing this information :
     *  #1 - store : name of the store
     *  #2 - aile : aile location
     *  #3 - etage : floor number
     *  #4 - num : location number in the mal architecture
     *  #5 - time : time to visit store
     */
    @RequestMapping(value="/course", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String courseSubmit(@RequestBody Map<String, String> body) {

        //Parse duration var from POST Body
        String duration = "";
        if (body.get("duration") != null){
            duration += body.get("duration");
        }else{
            duration = "100";
        }
        System.out.println(body.get("date") + " DATE " + body.get("heure"));
        System.out.println(body.get("cinema") + " attribut cinema ");
        System.out.println(body.get("restaurant") + " atriibutt restaurant");
        boolean cinema = true;
        boolean restaurant = true;
        if  (body.get("cinema") == null) {
        	cinema = false;
        }
        else if (body.get("cinema").equals("false")){
        	cinema = false;
        }
        else {
        	cinema = true;
        }
        if  (body.get("restaurant") == null) {
        	restaurant = false;
        }
        else if (body.get("restaurant").equals("false")){
        	restaurant = false;
        }
        else {
        	restaurant = true;
        }
        
        System.out.println(restaurant + "  restaurant " + cinema + "   cinema");
//        if (body.get("cinema") != null){
//            String c = body.get("cinema");
//            if(c.equals("false")) {
//            	cinema = false;
//            }
//            else if (c.equals("true")) {
//            	cinema = true;
//            }
//        }
//        
//        if (body.get("restaurant") != null){
//            String c = body.get("restaurant");
//            if(c.equals("false")) {
//            	cinema = false;
//            }
//            else if (c.equals("true")) {
//            	cinema = true;
//            }
//        }
        //Parse stores list from POST Body -> "1,34,25"
        String stores = "";
        stores = body.get("stores");

        // OK ON RECUPERE LES MAGASINS
        String s = "[";
        List<LocationStore> locationStoreList = courseService.findStoreByCriteria(Integer.parseInt(duration),stores,restaurant,cinema);
        // Formatin Json response
        int cpt = 0;
        int time = 0;
        GregorianCalendar gc = new GregorianCalendar();
        SimpleDateFormat fmt = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
        fmt.setCalendar(gc);
        String dateFormatted = fmt.format(gc.getTime());
        for (LocationStore ls: locationStoreList) {
            cpt ++;
            s += "{";
            s += "\"id\":\""+ls.getStore().getStoreId()+"\",";
            s += "\"store\":\""+ls.getStore().getName()+"\",";
            s += "\"wing\":\""+ls.getLocation().getAile()+"\",";
            s += "\"floor\":\""+ls.getLocation().getEtage()+"\",";
            s += "\"num\":\""+ls.getLocation().getNumero()+"\",";
            s += "\"time\":\""+time+"\",";
            s += "\"type\":\""+ls.getStore().getType() + "\",";
            s += "\"datetime\":\""+dateFormatted + "\"";


            time += ls.getLocation().getVisitTime();
            gc.add((GregorianCalendar.MINUTE), ls.getLocation().getVisitTime());
            fmt.setCalendar(gc);
            dateFormatted = fmt.format(gc.getTime());
            System.out.println(dateFormatted);
            if(cpt < locationStoreList.size()){
                s += "},";
            }else{
                s += "}";
            }
        }
        
        
      
        s += "]";
        return s;
    }


}
