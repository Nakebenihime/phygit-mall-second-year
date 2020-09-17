package pds.qflush.apiqflush.datamock;

import org.springframework.beans.factory.annotation.Autowired;
import pds.qflush.apiqflush.model.*;
import pds.qflush.apiqflush.service.*;

import java.util.ArrayList;
import java.util.List;


public class DataMockCommand {

    public static void generateOrder(int numberProductByOder,int numberCommand,@Autowired ProductServiceImpl productService,@Autowired CommandServiceImpl commandService,@Autowired CustomerServiceImpl customerService,@Autowired ModeLivraisonServiceImpl modeLivraisonService){
            List<Customer> customerList= customerService.findAll();
            List<Product> productList= productService.findAll();
            List<ModeLivraison> modeLivraisonList=modeLivraisonService.findAll();
            for (int i=0;i<numberCommand;i++){
                Command oneCommand= new Command();
                Customer oneCustomer= customerList.get(DataMockGeneric.generateRandom(1,customerList.size()));
                List<CommandLine> commandLineList = new ArrayList<>();
                for(int j=0;j<numberProductByOder;j++){
                    Product p = productList.get(DataMockGeneric.generateRandom(1,productList.size()));
                    int quantity = DataMockGeneric.generateRandom(1,3);
                    double totalPrice=quantity*p.getPrice().getRefPrice();
                    CommandLine oneCommandLine= new CommandLine(p,oneCommand,quantity,totalPrice);
                    commandLineList.add(oneCommandLine);
                }
                System.out.println(commandLineList.size());
                oneCommand.setCustomer(oneCustomer);
                oneCommand.setCommandLines(commandLineList);
                double totalCommand= 0;
                for(int k=0;k<commandLineList.size();k++){
                    totalCommand=totalCommand +commandLineList.get(k).getTotalPrice();
                }
                oneCommand.setState("En attente");
                ModeLivraison  modeLivraison= modeLivraisonList.get(DataMockGeneric.generateRandom(1,modeLivraisonList.size()));
                oneCommand.setModeLivraison(modeLivraison);;
                totalCommand=totalCommand+modeLivraison.getTarificationLivraison().get(0).getPrix();
                oneCommand.setTotal(totalCommand);
                commandService.save(oneCommand);

            }

    }
}
