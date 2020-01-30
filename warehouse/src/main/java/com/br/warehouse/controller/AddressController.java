package com.br.warehouse.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.warehouse.model.Address;
import com.br.warehouse.service.AddressService;

@RestController
@RequestMapping("/api/v1/address")
public class AddressController {

	private AddressService addressService;

	public AddressController(AddressService addressService) {
		this.addressService = addressService;
	}
	
	@PostMapping
	public Address create(@RequestBody Address address) {
		return addressService.create(address);
	}
	
	@GetMapping
	public List<Address> showAll(){
		return addressService.showAll();
	}
	
	@GetMapping(path="{/{id}}")
	public Optional<Address> showById(@PathVariable("id") Long id){
		return addressService.showById(id);
	}

		
	@GetMapping(path= {"/{id}"})
	public ResponseEntity<Address> update(@PathVariable("id") Long id, @RequestBody Address address) {
		Address updatedAddress = addressService.update(id, address);
		if (updatedAddress == null) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok().body(updatedAddress);
		}
		
	}
	
	
	@DeleteMapping(path= {"/{id}"})
	public ResponseEntity<Address> deleteProduct(@PathVariable("id") Long id) {
		addressService.deleteAddressById(id);;
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
}
