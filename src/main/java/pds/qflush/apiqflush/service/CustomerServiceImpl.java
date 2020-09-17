package pds.qflush.apiqflush.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.apache.tomcat.util.buf.Utf8Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pds.qflush.apiqflush.datamock.DataMockGeneric;
import pds.qflush.apiqflush.model.Address;
import pds.qflush.apiqflush.model.Customer;
import pds.qflush.apiqflush.model.Notification;
import pds.qflush.apiqflush.repository.AddressRepository;
import pds.qflush.apiqflush.repository.CustomerRepository;
import pds.qflush.apiqflush.repository.NotificationRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
public class CustomerServiceImpl implements ServiceImpl<Customer> {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public Customer save(Customer Customer) {
        return customerRepository.save(Customer);
    }

    @Override
    public Optional<Customer> findById(long id) {
        return customerRepository.findById(id);
    }

    @Override
    public void delete(Customer Customer) {
        customerRepository.delete(Customer);
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public List<Customer> findByName(String name) {
        System.out.println(name);
        return customerRepository.findByName(name);
    }

    public List<Customer> findByFirstName(String firstname) {
        System.out.println(firstname);
        return customerRepository.findByFirstName(firstname);
    }

    public boolean connexion(Customer customer, HttpServletRequest request) {
        List<Customer> ListCustomer = customerRepository.findByEmailIgnoreCaseContaining(customer.getEmail());
        boolean response = false;

        if (ListCustomer.size() == 1) {
            System.out.println("ok");
            Customer find = ListCustomer.get(0);
            // Création de la session
            HttpSession session = request.getSession();
            System.out.println(find.getEmail());
            String userName = find.getName();
            // parametre de la session
            session.setAttribute("username", userName);
            session.setAttribute("id", find.getCustomerId());
            //type d'utilisateur
            session.setAttribute("type", find.getType());

            response = true;
        }
        System.out.println("no");
        return response;
    }

    public List<Notification> afficheNotif(Long idcust) {
    	Optional<Customer> customer = customerRepository.findById(idcust);
    	List<Notification> notif = notificationRepository.findByCustomer(customer.get());
    	System.out.println(notif.get(0).getCustomer().getFirstName() + " à partir de notif");
    	//System.out.println(customer.getFirstName() + " à partir du client");
    	//System.out.println(notif.get(0).getCampagne().getDescription());
    	for (int i=0; i<notif.size();i++) {
    		System.out.println(notif.get(i).getCampagne().getDescription() + "description");
    	}

    	return notif;

    }


    /**
     * method that generates an amount of clients
     * @param accountNumber
     */
    public void generateCustomer(int accountNumber){

        String[] name = {"Pierre","Michel","André","Philippe","René","Louis","Alain","Jacques","Bernard","Marcel","Daniel","Roger","Robert","Paul","Claude","Henri","Georges","Nicolas","François","Patrick","Gérard","Christophe","Joseph","Julien","Maurice","Laurent","Frédéric","Eric","David","Stéphane","Pascal","Sébastien","Alexandre","Thierry","Olivier","Thomas","Antoine","Raymond","Guy","Dominique","Charles","Didier","Marc","Vincent","Yves","Guillaume","Bruno","Serge","Maxime","Jeanne","Françoise","Monique","Catherine","Nathalie","Isabelle","Jacqueline","Anne","Sylvie","Martine","Madeleine","Nicole","Suzanne","Hélène","Christine","Marguerite","Denise","Louise","Christiane","Yvonne","Valérie","Sophie","Sandrine","Stéphanie","Céline","Véronique","Chantal"};
        String[] surname = {"Bernard","Roux","Thomas","Petit","Durand","Robert","Richard","Simon","Moreau","Dubois","Blanc","Laurent","Girard","Bertrand","Garnier","David","Morel","Guerin","Fournier","Roy","Rousseau","Andre","Gautier","Bonnet","Lambert","Henry","Faure","Mercier","Vincent","Chevalier","Leroy","Marchand","Perrin","Morin","Masson","Giraud","Dupont","Robin","Nicolas","Brun","Mathieu","Clement","Lefebvre","Fabre","Barbier","Francois","Roussel","Arnaud","Gerard","Aubert","Duval","Legrand","Blanchard","Brunet","Lefevre","Denis","Breton","Pierre","Roche","Paris","Boyer","Colin","Fontaine","Jean","Bourgeois","Gaillard","Noel","Dumas","Picard","Briand","Lucas","Rolland","Joly","Leclerc","Renard","Roger"};
        String[] address = {"Rue Cheret","Rue de l'Espérance","Rue Albert Gleizes","Rue de Paris","Rue de la Basse Quinte","Rue des Bouvets","Rue Alfred Thomereau","Rue Berthold Mahn","Rue David d'Angers","Rue de la Croix des Craies","Rue de Paris","Rue de Joly","Rue de Gourcuff","Rue Claude Debussy","Rue de Mayenne","Rue des Buttes","Rue Auguste Renoir","Rue des Caillotins","Rue de Mesly","Rue Albert Gleizes","Rue d'Estienne d'Orves","Rue Denfert Rochereau","Rue des Caillotins","Rue Ambroise Pare","Rue de la Plumerette","Rue Charles Despiau","Rue de l'Orme Saint Simeon","Rue de la Fosse Aux Moines","Rue Claude Debussy","Rue André Charles Boulle","Rue Benjamin Moloise","Rue de Bourgogne","Rue des Bordieres","Rue Camille Robert","Rue Ambroise Pare","Rue de Mesly","Rue Armand Guillaumin","Rue de Normandie","Rue Antoine Bourdelle","Rue Aristide Maillol","Rue Albert Doyen","Rue de Plaisance","Rue de la Prairie","Rue Amadeo Modigliani","Rue Auguste Perret","Rue de Reims","Rue de Mesly","Rue des Écoles","Rue des Écoles","Rue Charles Gounod","Rue de l'Orme Saint Simeon","Rue des Eglantiers","Rue Alfred Sisley","Rue Charpy","Rue André Maurois","Rue Cesar Franck","Rue de l'Écluse","Rue Claude Nicolas Ledoux","Rue Claude Perrault","Rue Alfred de Musset","Rue de l'Echat"};
        String[] city ={"Paris 14e Arrondissement","Neauphle-le-Château","Bondy","Pontault-Combault","Dammarie-les-Lys","Colombes","Gagny","Maurecourt","Paris 8e Arrondissement","Chilly-Mazarin","Boinvilliers","Coulombs-en-Valois","Fontainebleau","Clairefontaine-en-Yvelines","La Celle-les-Bordes","Conflans-Sainte-Honorine","Villebon-sur-Yvette","Saint-Cyr-l'École","Freneuse","Baby","Bois-Colombes","Buc","Chevreuse","Itteville","Bussy-Saint-Georges","Athis-Mons","Orly","Ozoir-la-Ferrière","Brétigny-sur-Orge","Crégy-lès-Meaux","Pierrefitte-sur-Seine","Thiverval-Grignon","Villeneuve-Saint-Georges","Dugny","Avrainville","Fourqueux","Émerainville","Saint-Denis","Héricy","Chatou","Dammartin-en-Goële","Chelles","Beynes","Tremblay-en-France","Mézières-sur-Seine","Alfortville","Ville-d'Avray","Chalautre-la-Petite","Saint-Pierre-du-Perray","Longvilliers","Carrières-sur-Seine","Saint-Mammès","Corbeil-Essonnes","Clichy-sous-Bois","Trappes","Couilly-Pont-aux-Dames","Chamarande","Coubron","Chamigny","Les Alluets-le-Roi","Taverny","Boissy-sous-Saint-Yon","Épinay-sur-Orge","Augers-en-Brie","Champcueil","Favières","Ablis","Vincennes","Morainvilliers","Menucourt","L' Étang-la-Ville","Monnerville","Garches","Jouars-Pontchartrain","Bailly-Romainvilliers","Le Bourget"};
        String[] zipcode ={"75014","78640","93140","77290","77340","77190","92700","93220","78780","75008","91380","78200","77840","77300","78120","78720","78700","91140","78210","78840","77480","92270","78530","78460","91760","77600","91200","94310","77330","91220","77124","93380","78850","94190","93440","91630","78112","77184","93200","77850","78400","77230","77500","78650","93290","78970","94140","92410","77160","91280","78730","78420","77670","91100","93390","78190","77860","91730","93470","77260","78580","95150","91790","91360","77560","91750","77220","78660","94300","78630","95180","78620","91930","92380","78760","77700"};

        for(int i = 0 ; i < accountNumber; i++){

            int nameIndex = DataMockGeneric.generateRandom(0,75);
            int surnameIndex = DataMockGeneric.generateRandom(0,75);
            int streetIndex = DataMockGeneric.generateRandom(0,60);
            int cityIndex = DataMockGeneric.generateRandom(0,75);
            int zipCodeIndex = DataMockGeneric.generateRandom(0,75);

            String firstname =  name[nameIndex];
            String lastname = surname[surnameIndex];

            String messagerie = name[nameIndex]+"."+surname[surnameIndex]+"@gmail.com";

            String addr = DataMockGeneric.generateRandom(1,200)+address[streetIndex];
            String cityzone = city[cityIndex];
            String zip = zipcode[zipCodeIndex];


            String phoneNumber = DataMockGeneric.generatePhoneNumber();
            int age = DataMockGeneric.generateRandom(15,90);

            String type = "client";

            Address addressObject = new Address(addr,zip,cityzone);
            /*   check that the mail does not exist in the database, if it exists generate a random number to have a different mail     */
            List<Customer> customers = this.findAll();
            List<String> existantMails = new ArrayList<>();
            for(int j=0; i<customers.size();j++) {
            	existantMails.add(customers.get(j).getEmail());            	
            }
            while (existantMails.contains(messagerie)) {
               	messagerie= name[nameIndex]+"."+surname[surnameIndex]+DataMockGeneric.generateRandom(1,500)+"@gmail.com";        	
            }
            Customer customer = new Customer(lastname,firstname,addressObject,messagerie,phoneNumber,age,type);
            customerRepository.save(customer);
        }
        
        Customer customer = new Customer("admin","center",null,"admin"+"."+"center"+"@gmail.com","0778357609",0,"admin");
        customerRepository.save(customer);
    }
}



