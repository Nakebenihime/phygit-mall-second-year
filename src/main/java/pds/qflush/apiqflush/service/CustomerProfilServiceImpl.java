package pds.qflush.apiqflush.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pds.qflush.apiqflush.datamock.DataMockGeneric;
import pds.qflush.apiqflush.model.Customer;
import pds.qflush.apiqflush.model.CustomerProfil;
import pds.qflush.apiqflush.model.CustomerProfilPK;
import pds.qflush.apiqflush.model.Profil;
import pds.qflush.apiqflush.repository.CustomerProfilRepository;

@Service

public class CustomerProfilServiceImpl implements ServiceImpl<CustomerProfil> {

    @Autowired
    private CustomerProfilRepository customerProfilRepository;

    @Autowired
    private CustomerServiceImpl customerService;

    @Autowired
    private ProfilServiceImpl profilService;

    @Override
    public CustomerProfil save(CustomerProfil Customer) {
        return customerProfilRepository.save(Customer);
    }



    @Override
    public void delete(CustomerProfil Customer) {
        customerProfilRepository.delete(Customer);
    }

    @Override
    public List<CustomerProfil> findAll() {
        return customerProfilRepository.findAll();
    }

//    public List<CustomerProfil> findByIdProfil(String name){
//        System.out.println(name);
//        return customerProfilRepository.findByIdProfil(name);
//    }



	@Override
	public Optional<CustomerProfil> findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void generateCustomerProfile(){

        List<Customer> customerList = customerService.findAll();

        for (int i = 1; i <= customerList.size(); i++){
            long j = DataMockGeneric.generateRandom(1,9);
            CustomerProfilPK customerProfilPK = new CustomerProfilPK();
            Optional<Profil> profil = profilService.findById(j);
            Optional<Customer> customer = customerService.findById(i);
            customerProfilPK.setCustomerId(new Long(i));
            customerProfilPK.setIdProfil(j);

            CustomerProfil customerProfil = new CustomerProfil();
            customerProfil.setId(customerProfilPK);
            customerProfil.setCustomer(customer.get());
            customerProfil.setProfil(profil.get());

            customerProfilRepository.save(customerProfil);
        }

    }

 

}
