package pds.qflush.apiqflush.controller;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pds.qflush.apiqflush.model.FormulaireSearchLivraison;
import pds.qflush.apiqflush.model.LocationStore;
import pds.qflush.apiqflush.model.ModeLivraison;
import pds.qflush.apiqflush.model.Store;
import pds.qflush.apiqflush.service.FrequentationServiceImpl;
import pds.qflush.apiqflush.service.LocationStoreServiceImpl;
import pds.qflush.apiqflush.service.StoreServiceImpl;

@Controller
public class FrequentationController {

	    @Autowired
	    private FrequentationServiceImpl frequentationServiceImpl;
	    
	    @Autowired
	    private StoreServiceImpl storeServiceImpl;
	    
	    @Autowired
	    private LocationStoreServiceImpl locationStoreServiceImpl;
	    
	   private Store store = new Store();
	   private Random rng = new Random();

	    //******************** GET METHOD ********************
	    
	    @GetMapping("/Prevent")
	    public String getPrevent(Model model) {
	      	List<Store> stores = new ArrayList<>();
	      	
	      	Optional<Store> s =storeServiceImpl.findById(store.getStoreId());
	        stores.add(s.get());
	    	model.addAttribute("stores", stores);
	    	model.addAttribute("Frequentation", frequentationServiceImpl.getData());
	    	model.addAttribute("moyenne", frequentationServiceImpl.getMoyenne());
	    	model.addAttribute("coeffRegression" , frequentationServiceImpl.getCoeffRegressionLin(frequentationServiceImpl.getData()));
	    	return "Prevent";
	    }
	    
	    @GetMapping("/formulairePrevent")
	    public String getFormulairePrevent(Model model) {
	    	List<Store> storeList = new ArrayList<Store>();
	    	for (LocationStore ls : locationStoreServiceImpl.findAll()) {
				storeList.add(ls.getStore());
			}
	    	model.addAttribute("stores", storeList);
	    	model.addAttribute("Store", new Store());
	        return "formulairePrevent";
	    }
	    
	    @RequestMapping(value="/formulairePrevent", method=RequestMethod.POST)
	    public String getStoreName(@ModelAttribute Store store, Model model) {
	    	this.store=store;
	    	return "redirect:/Prevent";
	    }

	    @GetMapping("/Analysis")
	    public String getAnalysis(Model model) {
	    	boolean hasCorrected = frequentationServiceImpl.outlierDetection();
	    	List<Store> stores = new ArrayList<>();
	      	Optional<Store> s =storeServiceImpl.findById(store.getStoreId());
	        stores.add(s.get());
	    	model.addAttribute("stores", stores);
	    	model.addAttribute("frequentation", frequentationServiceImpl.getOutliersData());
	    	model.addAttribute("mfreq", frequentationServiceImpl.getCoeffRegressionLin(frequentationServiceImpl.getOutliersData()));
	    	model.addAttribute("pfreq", frequentationServiceImpl.getInterceptRegression(frequentationServiceImpl.getOutliersData()));
	    	model.addAttribute("correction", frequentationServiceImpl.getCorrectedData());
	    	model.addAttribute("mcor", frequentationServiceImpl.getCoeffRegressionLin(frequentationServiceImpl.getCorrectedData()));
	    	model.addAttribute("pcor", frequentationServiceImpl.getInterceptRegression(frequentationServiceImpl.getCorrectedData()));
	    	model.addAttribute("corrected", hasCorrected);
	    	return "Analysis";
	    }
	    
	    @GetMapping("/formulaireAnalyse")
	    public String getFormulaireAnalyse(Model model) {
	    	frequentationServiceImpl.initData();
	    	List<Store> storeList = new ArrayList<Store>();
	    	for (LocationStore ls : locationStoreServiceImpl.findAll()) {
				storeList.add(ls.getStore());
			}
	    	model.addAttribute("stores", storeList);
	    	model.addAttribute("Store", new Store());
	        return "formulaireAnalyse";
	    }
	    
	    @RequestMapping(value="/formulaireAnalyse", method=RequestMethod.POST)
	    public String getStoreNameOutliers(@ModelAttribute Store store, Model model) {
	    	this.store=store;
	    	return "redirect:/Analysis";
	    }

	}
