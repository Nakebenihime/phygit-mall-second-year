package pds.qflush.apiqflush.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pds.qflush.apiqflush.model.Address;
import pds.qflush.apiqflush.repository.AddressRepository;

@Service
public class AddressServiceImpl implements ServiceImpl<Address>{
	
	@Autowired
	private AddressRepository addressRepository;

	@Override
	public Address save(Address t) {
		return addressRepository.save(t);
	}

	@Override
	public Optional<Address> findById(long id) {
		return addressRepository.findById(id);
	}

	@Override
	public void delete(Address t) {
		addressRepository.delete(t);
	}

	@Override
	public List<Address> findAll() {
		return addressRepository.findAll();
	}

}
