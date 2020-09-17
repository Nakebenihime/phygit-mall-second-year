package pds.qflush.apiqflush.datamock;

import org.springframework.beans.factory.annotation.Autowired;
import pds.qflush.apiqflush.model.*;
import pds.qflush.apiqflush.service.*;

import javax.xml.crypto.Data;
import java.util.List;


public class DataMockProduct {


    public static void genarateCategorie(@Autowired CategoryServiceImpl categoryService){
        String[] ListCategory={"Bijouterie","Alimentation","Jouet","Téléphonie","Multimédia","Parfumerie","Prêt-à-porter"};
        System.out.println(ListCategory[1]);
        for (int i =0 ; i<ListCategory.length;i++){
            Category oneCategory= new Category(ListCategory[i]);
            categoryService.save(oneCategory);
            System.out.println(oneCategory);
        }
    }

    public static void generateProductData(@Autowired ProductServiceImpl productService, @Autowired CategoryServiceImpl categoryService, @Autowired ProductStatusServiceImpl productStatusService, @Autowired StoreServiceImpl storeService, @Autowired StockProductServiceImpl stockProductService) {
        List<Store> storeList = storeService.findAll();
        List<Category> listcategory =categoryService.findAll();
        List<ProductStatus> productStatusList =productStatusService.findAll();
        String[] nameRandom= {};
        String[] name= {"Jute Grocery Bag - Square shape","Phone Samsung S9","HTC Phone","Parfum invictus","Casque beats by dre","Pull taille M","PC mac book pro","Jeu Monpoly","Verre trempé pour telephonne samasung s9" };
        //String[] name = {"Jute Grocery Bag - Square shape","Jute Drawstring Pouch - Small","Jute Laminated Landscape - Black","Jute Laminated Landscape - Full Black","Cotton Flat Bag","Cotton Tote With Bottom Only","Cotton Tote Black With Bottom Only","Cotton Backpack (Drawstring)","Cotton Messenger Bag","Canvas Tote With Bottom Only","Canvas Tote Black With Bottom Only","Canvas Farmers Market Bag","Canvas Farmers Market Black Bag","Juco Bag Landscape (jute+cotton blend)","Juco Small Bag (jute+cotton blend)","Cotton Drawstring Pouch - Small","Cotton Drawstring Pouch - Medium","Cotton Drawstring Pouch - Large","Jute Costmetic Zipper","Cotton Tote With Base Gusset Only - Red","Cotton Tote With Base Gusset Only - Royal","Cotton Tote With Base Gusset Only - White","Cotton Tote With Full Gusset","Canvas Tote With Full Gusset","Jute Laminated Landscape - Red","Canvas Wine Bag - 1 Bottle","Jute Wine Bag - 1 Bottle","Jute Wine Bag - 2 Bottle","Jute Laminated Landscape - Blue","Jute Laminated Landscape - Brown","Jute Dawstring Pouch - Medium","Jute Large Pouch Toggle","Unlaminated Jute Bag","Jute Laminated Landscape","Jute Farmers Market Bag","Jute Laminated Small Bag","Jute Medium Bag"};
        //String[] name = {"Jute Grocery Bag - Square shape", "Jute Drawstring Pouch - Small", "Jute Laminated Landscape - Black", "Jute Laminated Landscape - Full Black", "Cotton Flat Bag", "Cotton Tote With Bottom Only", "Cotton Tote Black With Bottom Only", "Cotton Backpack (Drawstring)", "Cotton Messenger Bag", "Canvas Tote With Bottom Only", "Canvas Tote Black With Bottom Only", "Canvas Farmers Market Bag ", "Canvas Farmers Market Black Bag ", "Juco Bag Landscape (jute+cotton blend) ", "Juco Small Bag (jute+cotton blend) ", "Cotton Drawstring Pouch - Small ", "Cotton Drawstring Pouch - Medium ", "Cotton Drawstring Pouch - Large ", "Jute Costmetic Zipper ", "Cotton Tote With Base Gusset Only - Red  ", "Cotton Tote With Base Gusset Only - Royal", "Cotton Tote With Base Gusset Only - White ", "Cotton Tote With Full Gusset ", "Canvas Tote With Full Gusset ", "Jute Laminated Landscape - Red ", "Canvas Wine Bag - 1 Bottle ", "Jute Wine Bag - 1 Bottle ", "Jute Wine Bag - 2 Bottle ", "Jute Laminated Landscape - Blue ", "Jute Laminated Landscape - Brown ", "Jute Dawstring Pouch - Medium ", "Jute Large Pouch Toggle", "Unlaminated Jute Bag  ", "Jute Laminated Landscape ", "Jute Farmers Market Bag ", "Jute Laminated Small Bag ", "Jute Medium Bag "};
        double[] sizeWidth = {33, 12, 40, 40, 38, 38, 38, 38, 38, 38, 38, 45, 45, 40, 25, 15, 20, 30, 21, 38, 38, 38, 38, 38, 40, 17, 11, 20, 40, 40, 20, 24, 38, 40, 45, 25, 35};
        double[] sizeHeight = {33, 18, 32, 32, 38, 38, 38, 43, 38, 38, 38, 32, 32, 32, 20, 21, 30, 40, 14, 38, 38, 38, 42, 42, 32, 30, 35, 35, 32, 32, 30, 40, 38, 32, 32, 20, 25};
        String[] type = {"Bags", "Bags", "Bags", "Bags", "Bags", "Bags", "Bags", "Bags", "Bags", "Bags", "Bags", "Bags", "Bags", "Bags", "Bags", "Bags", "Bags", "Bags", "Bags", "Bags", "Bags", "Bags", "Bags", "Bags", "Bags", "Bags", "Bags", "Bags", "Bags", "Bags", "Bags", "Bags", "Bags", "Bags", "Bags", "Bags", "Bags"};
        String[] brand = {};
        //Condition pour categorie


        String[] description = {"Jute bag for grocery", "Raw jute pouch - small", "Amazing natural & black contrast", "Black jute bag with smart white handles Looks smart for events & if you have black themed branding. Soft Black Cotton Padded Handles Expandable side & base gusset to allow more space Has inside lining All Prices: excl gst, freight & set up @ $112 per col; subject to change w/o notice & apprx guide only Repeat set up $56. Pl discuss for a firm quotefor a solid structure", "Most popular in the cotton family", "Cotton tote with expandable base", "Cotton tote in the black", "Backsack style", "Messenger style", "Most popular in the canvas family", "Classic black tote", "Market bag in the natural", "Classic Black in the market style", "Blend of Jute & Cotton to give it a smooth finish", "Blend of Jute & Cotton to give it a smooth finish", "Soft pouch in small", "Soft pouch in medium", "Soft pouch in large", "﻿Jute cosmetic pouch bag Zippered closure Ideal for skin care product companies", "Awesome red", "Awesome royal blue", "Awesome white", "Gusset on all 3 sides", "Gusset on all 3 sides", "Amazing natural & red contrast", "Single bottle wine holder", "1 Bottle Wine Carrier", "2 Bottle Wine Carrier", "Amazing natural & blue contrast", "Amazing natural & brown contrast", "Raw jute pouch - medium", "Raw jute pouch - large", "Economical bag in the jute family Raw unlaminated jute with No inside lining. Absolute raw & natural product Comes with jute handles Rustic feel", "Fastest moving product in the jute family Natural & Sturdy. Heavy Duty. Ideal for Grocery / Regular shopping, Conferences, Book Stores Soft Natural Cotton Padded Handles Expandable side & base gusset to allow more space Has inside lining", "High end jute bag Strong & Heavy Duty Ideal for Markets, Grocery, Regular shopping Soft Natural Cotton Padded Handles Comes with base gusset (expandable base) Has inside lining giving the bag a solid structure", "Small jute bag with inside lamination", "Medium jute bag with inside lamination"};

        for (int i = 0; i < name.length; i++) {
            double sizeLength = DataMockGeneric.generateRandom(0, 37);
            double weight = DataMockGeneric.generateRandom(0, 2000);
            int rayon = DataMockGeneric.generateRandom(0, 4);
            double prix = DataMockGeneric.generateRandom(20,200);
            int nameIndex = DataMockGeneric.generateRandom(0,37);
            int sizeWidthIndex = DataMockGeneric.generateRandom(0,37);
            int sizeHeighIndex = DataMockGeneric.generateRandom(0,37);
            int typeIndex = DataMockGeneric.generateRandom(0,37);
            int descriptionIndex = DataMockGeneric.generateRandom(0,37);
            Category category= listcategory.get(DataMockGeneric.generateRandom(0,listcategory.size()));

            String typeP="";
            String nameP =  name[i];
            if(nameP.contains("bague")){
                typeP = "bijoux";
                //BIJOUTERIE
                category=listcategory.get(0);

            }else if (nameP.contains("chip") || nameP.contains("bouteille")){
                typeP = "snack";
                //alimentation
                category=listcategory.get(1);
            }else if (nameP.contains("Jouet")|| nameP.contains("Jeu")){
                typeP = "Jouer enfant";
                //jouet
                category=listcategory.get(2);
            }else if (nameP.contains("Phone")|| nameP.contains("Verre")){
                typeP = "Smartphone";
                // telephonie
                category=listcategory.get(3);
            }else if (nameP.contains("Pc")|| nameP.contains("Casque")){
                if(nameP.contains("Pc")){
                    typeP = "Ordinateur";
                }else{
                    typeP = "Casque";
                }
                //Multimedia
                category=listcategory.get(4);
            }else if (nameP.contains("Parfum")){
                typeP = "Parfumerie";
                category=listcategory.get(5);
            }else if (nameP.contains("Pantalon")||nameP.contains("Pull")||nameP.contains("Bag") ){
                if(nameP.contains("Bag")){
                    typeP = "sac";
                }else{
                    typeP = "vetement";
                }
                category=listcategory.get(6);
            }

            double sizeWidthsW =  sizeWidth[sizeWidthIndex];
            double sizeHeightsH = sizeHeight[sizeHeighIndex];
            //String typeP = type[typeIndex];
            String descriptionP = description[descriptionIndex];
            //3 MAGASIN AVEC LE MEME PRODUIT
            for(int j =0; j<3;j++)  {

                int stockProduct = DataMockGeneric.generateRandom(1, 30);
                Price price = new Price(DataMockGeneric.generateRandomDouble(10, 150));
                ProductStatus productStatus = productStatusList.get(0);


                Product product = new Product(nameP, sizeWidthsW, sizeHeightsH, sizeLength, weight, typeP, null, descriptionP, rayon, price, category, productStatus, null);
                productService.save(product);
                int numStore= DataMockGeneric.generateRandom(0,storeList.size());
                Store store= storeList.get(numStore);
                StockProduct stockProduct1 = new StockProduct(product, store, stockProduct);
                stockProductService.save(stockProduct1);

            }

        }
    }

    public static void generateOrder(){

    }
}
