package com.br.warehouse.service;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.br.warehouse.model.Product;
import com.br.warehouse.repository.ProductRepository;

@Service
public class ProductService {

	private ProductRepository productrepository;
	
	public ProductService(ProductRepository productrepository) {
	
		this.productrepository= productrepository;
	}
	
	public Product create(Product product) {
		return productrepository.save(product);
	}
	
	public List<Product> showAll(){
		return productrepository.findAll();
	}
	
	public Optional<Product> showById(Long product_id){
		return productrepository.findById(product_id);
	}
	
	public List<Product> findByName(String name) {
		return productrepository.findByNameContaining(name);
	}
	
	public void deleteProductById(Long product_id) {
		try {productrepository.deleteById(product_id);}
		catch (EmptyResultDataAccessException e) {}
	}
	
	public Product update(@PathVariable("product_id") Long product_id, @RequestBody Product product) {
		Optional<Product> updatingProduct= productrepository.findById(product_id);
		if(updatingProduct.isPresent()) {
			updatingProduct.get().setPrice(product.getPrice());
			updatingProduct.get().setCode(product.getCode());
			updatingProduct.get().setMeasureUnit(product.getMeasureUnit());
			updatingProduct.get().setQuantity(product.getQuantity());
			updatingProduct.get().setAddress(product.getAddress());
			updatingProduct.get().setName(product.getName());
			return productrepository.save(updatingProduct.get());
					
		}else {return null;}
	}
}
	

