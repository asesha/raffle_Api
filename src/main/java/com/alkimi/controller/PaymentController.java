package com.alkimi.controller;

import java.time.LocalDate;
import java.util.NoSuchElementException;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alkimi.exceptions.ClientErrorException;
import com.alkimi.service.PaymentService;
import com.alkimi.vo.PaymentVO;

import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/api")
@Validated
@Slf4j
public class PaymentController {
	
	@Autowired
	private PaymentService service;
	  
    @GetMapping("/payments/{id}")
    public ResponseEntity<?> getPaymentById(@Positive @PathVariable("id") int paymentId) {
    	log.info("getPaymentById begin");
    	PaymentVO vo = null;
  		try {
  			vo = service.getPaymentById(paymentId);
  			return new ResponseEntity<PaymentVO>(vo,HttpStatus.OK);
  		} catch(NoSuchElementException ex) {
  			log.error("Exception caught at getting the Payment by Id {} ", paymentId);
  			throw new ClientErrorException("Payment Not Found For ID : " + paymentId);
  		}
  		
    }
    
    @PostMapping("/payments")
    public ResponseEntity<PaymentVO> createPayment(@Valid @RequestBody PaymentVO paymentVO) {
    	System.out.println("createPayment begin");

    	paymentVO.setPaymentDate(LocalDate.now());
    	PaymentVO vo = service.createPayment(paymentVO);
    	HttpHeaders responseHeaders = new HttpHeaders();
   	    System.out.println("createPayment end");
	 	return new ResponseEntity<PaymentVO>(vo,responseHeaders,HttpStatus.CREATED);
	}
    
    @PutMapping("/payments/{paymentId}")
    public PaymentVO updateUser(@PathVariable int paymentId,@Valid @RequestBody PaymentVO paymentVO) {
    	PaymentVO vo =  service.updatePayment(paymentId, paymentVO);
		return vo;
	}
    
   
    
}
