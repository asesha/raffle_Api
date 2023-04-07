package com.alkimi.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alkimi.exceptions.ClientErrorException;
import com.alkimi.service.ProductService;
import com.alkimi.vo.ProductVO;

import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/api")
@Validated
@Slf4j
public class ProductController {
	
	@Autowired
	private ProductService service;
	    
    @GetMapping("/products")
    public List<ProductVO> getAllUser() {
    	log.debug("getAllProducts begin ");

    	List<ProductVO> products = service.getAllProduct();
    	
    	log.debug("getAllProducts end");
    	return products;
    }
    
    
    
    @GetMapping("/products/{id}")
    public ResponseEntity<?> getProductById(@Positive @PathVariable("id") int productId) {
    	log.info("getProductById begin");
    	ProductVO vo = null;
  		try {
  			vo = service.getProductById(productId);
  			return new ResponseEntity<ProductVO>(vo,HttpStatus.OK);
  		} catch(NoSuchElementException ex) {
  			log.error("Exception caught at getting the Users by Id {} ", productId);
  			throw new ClientErrorException("Product Not Found For ID : " + productId);
  		}
  		
    }
    
    @PostMapping("/products")
    public ResponseEntity<ProductVO> createProduct(@Valid @RequestBody ProductVO productVO) {
    	System.out.println("createProduct begin");
    	productVO.setCreatedOn(LocalDate.now());
    	productVO.setUpdatedOn(LocalDate.now());
    	ProductVO vo = service.createProduct(productVO);
    	HttpHeaders responseHeaders = new HttpHeaders();
   	    System.out.println("createProduct end");
	 	return new ResponseEntity<ProductVO>(vo,responseHeaders,HttpStatus.CREATED);
	}
    
    @PostMapping("/multiproducts")
    public ResponseEntity<List<ProductVO>> createMultiUser(@Valid @RequestBody List<ProductVO> productVO) {
    	System.out.println("createMultiProduct begin");
    	List<ProductVO> productVOs = service.createMultiProduct(productVO);
    	HttpHeaders responseHeaders = new HttpHeaders();
   	    System.out.println("createMultiProduct end");
	 	return new ResponseEntity<List<ProductVO>>(productVOs,responseHeaders,HttpStatus.CREATED);
	}
    
    @PutMapping("/products/{productId}")
    public ProductVO updateUser(@PathVariable int productId,@Valid @RequestBody ProductVO productVO) {
    	ProductVO vo =  service.updateProduct(productId, productVO);
		return vo;
	}
    
    @DeleteMapping("/products/{productId}")
    public ResponseEntity<ProductVO> deleteUser(@PathVariable int productId) {
    	ProductVO vo = service.deleteProduct(productId);
    	return new ResponseEntity<ProductVO>(vo,HttpStatus.NO_CONTENT);
    }
   
    
}
