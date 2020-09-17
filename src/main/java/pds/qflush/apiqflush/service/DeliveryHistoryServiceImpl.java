package pds.qflush.apiqflush.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pds.qflush.apiqflush.model.DeliveryHistory;
import pds.qflush.apiqflush.model.ModeLivraison;
import pds.qflush.apiqflush.repository.DeliveryHistoryRepository;
import pds.qflush.apiqflush.repository.ModeLivraisonRepository;
import pds.qflush.apiqflush.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DeliveryHistoryServiceImpl implements ServiceImpl<DeliveryHistory>  {

	@Autowired
	DeliveryHistoryRepository deliveryHistoryRepository;

	@Override
	public DeliveryHistory save(DeliveryHistory deliveryHistory) {
		return deliveryHistoryRepository.save(deliveryHistory);
	}

	@Override
	public Optional<DeliveryHistory> findById(long id) {
		return deliveryHistoryRepository.findById(id);
	}

	@Override
	public void delete(DeliveryHistory deliveryHistory) {

	}

	@Override
	public List<DeliveryHistory> findAll() {
		return deliveryHistoryRepository.findAll();
	}
}
