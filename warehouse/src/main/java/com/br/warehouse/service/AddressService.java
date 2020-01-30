package com.br.warehouse.service;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.br.warehouse.model.Address;
import com.br.warehouse.repository.AddressRepository;

@Service
public class AddressService {

	private AddressRepository addressrepository;

	public AddressService(AddressRepository addressrepository) {

		this.addressrepository = addressrepository;
	}

	public Address create(Address address) {
		return addressrepository.save(address);
	}

	public List<Address> showAll() {
		return addressrepository.findAll();
	}

	public Optional<Address> showById(Long id) {
		return addressrepository.findById(id);
	}
	
	public void deleteAddressById(Long id) {
		try {
			addressrepository.deleteById(id);

		} catch (EmptyResultDataAccessException e) {
		}
	}

	public Address update(@PathVariable Long id,@RequestBody Address address) {
		Optional<Address> updatingAddress = addressrepository.findById(id);
		if (updatingAddress.isPresent()) {
			updatingAddress.get().getProduct().addAll(address.getProduct());
			updatingAddress.get().setSection(address.getSection());
			updatingAddress.get().setShelf(address.getShelf());
			updatingAddress.get().setStreet(address.getStreet());
			return addressrepository.save(updatingAddress.get());
		}else {	return null;}
	}

	}
