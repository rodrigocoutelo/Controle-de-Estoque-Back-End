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

import com.br.warehouse.model.Product;
import com.br.warehouse.service.ProductService;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

	private ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	@PostMapping
	public Product create(@RequestBody Product product) {
		return productService.create(product);
	}
	
	@GetMapping
	public List<Product> showAll(){
		return productService.showAll();
	}
	
	@GetMapping(path="{/{id}}")
	public Optional<Product> showById(@PathVariable("product_id") Long product_id){
		return productService.showById(product_id);
	}
	
	
		
	@GetMapping(path= {"/{product_id}"})
	public ResponseEntity<Product> update(@PathVariable("product_id") Long product_id, @RequestBody Product product) {
		Product updatedProduct = productService.update(product_id, product);
		if (updatedProduct == null) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok().body(updatedProduct);
		}
		
	}
	
	@DeleteMapping(path= {"/{product_id}"})
	public ResponseEntity<Product> deleteProduct(@PathVariable("product_id") Long product_id) {
		productService.deleteProductById(product_id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
