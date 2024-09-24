package com.uevocola.com.uevocola.controllers;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.uevocola.com.uevocola.dtos.ProductRecordDto;
import com.uevocola.com.uevocola.models.ProductModel;
import com.uevocola.com.uevocola.repositories.ProductRepository;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.Optional;
import java.util.UUID;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
public class ProductController {
  
    @Autowired
    ProductRepository productRepository; 

	
	@PostMapping("/products")
	public ResponseEntity<ProductModel> saveProduct(@RequestBody @Valid ProductRecordDto productRecordDto) {
		var productModel = new ProductModel();
		BeanUtils.copyProperties(productRecordDto, productModel);
		return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(productModel));
	}

	@GetMapping("/products")
    public ResponseEntity<List<ProductModel>> getAllProducts() {
    return ResponseEntity.status(HttpStatus.OK).body(productRepository.findAll());
   }

   @GetMapping("/products/{id}")
   public ResponseEntity<Object> getOneProduct(@PathVariable(value = "id") UUID id) {
	   Optional<ProductModel> productOptional = productRepository.findById(id);
	   
	   if (productOptional.isEmpty()) {
		   return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
	   }
	   
	   return ResponseEntity.ok(productOptional.get());
   }

   @PutMapping("/products/{id}")
   public ResponseEntity<Object> updateProduct(@PathVariable(value = "id") UUID id, @RequestBody @Valid ProductRecordDto productRecordDto) {
	   Optional<ProductModel> productOptional = productRepository.findById(id);
	   
	   // Verifica se o produto existe
	   if (productOptional.isEmpty()) {
		   return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
	   }
	   
	   // Obtém o produto existente
	   ProductModel productModel = productOptional.get();
	   
	   // Copia as propriedades do DTO para o modelo existente
	   BeanUtils.copyProperties(productRecordDto, productModel); 
   
	   // Salva as alterações e retorna a resposta
	   ProductModel updatedProduct = productRepository.save(productModel);
	   return ResponseEntity.status(HttpStatus.OK).body(updatedProduct);
   }

   @DeleteMapping("/products/{id}")
public ResponseEntity<String> deleteProduct(@PathVariable(value = "id") UUID id) {
    Optional<ProductModel> productOptional = productRepository.findById(id);

    // Verifica se o produto existe
    if (productOptional.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
    }

    // Remove o produto
    productRepository.delete(productOptional.get());
    
    // Retorna uma resposta de sucesso
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); 
}

   
   
   

	
	
}
