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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alkimi.exceptions.ClientErrorException;
import com.alkimi.service.UserAddressDetailsService;
import com.alkimi.vo.UserAddressDetailsVO;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Validated
@Slf4j
public class UserAddressDetailsController {
	
	@Autowired
	private UserAddressDetailsService service;
	
	@PostMapping("/useraddress")
    public ResponseEntity<UserAddressDetailsVO> createUserAddress(@Valid @RequestBody UserAddressDetailsVO userAddressDetailsVO) {
    	log.info("createUserAddress begin");
    	userAddressDetailsVO.setCreatedOn(LocalDate.now());
    	UserAddressDetailsVO vo = service.createUserAddressDetails(userAddressDetailsVO);
    	HttpHeaders responseHeaders = new HttpHeaders();
   	    log.info("createUserAddress end");
	 	return new ResponseEntity<UserAddressDetailsVO>(vo,responseHeaders,HttpStatus.CREATED);
	}
	
	@GetMapping("/useraddress/{id}")
    public ResponseEntity<?> getUserAddressById(@Positive @PathVariable("id") int addressDetailsId) {
    	log.info("getUserAddressById begin");
    	UserAddressDetailsVO vo = null;
  		try {
  			vo = service.getUserAddressDetailsById(addressDetailsId);
  			return new ResponseEntity<UserAddressDetailsVO>(vo,HttpStatus.OK);
  		} catch(NoSuchElementException ex) {
  			log.error("Exception caught at getting the UserAddress by Id {} ", addressDetailsId);
  			throw new ClientErrorException("UserAddress Not Found For ID : " + addressDetailsId);
  		}
  		
    }
	
	@PutMapping("/useraddress/{id}")
    public UserAddressDetailsVO updateUserAddress(@PathVariable int id,@Valid @RequestBody UserAddressDetailsVO userAddressDetailsVO) {
		UserAddressDetailsVO vo =  service.updateUserAddressDetails(id, userAddressDetailsVO);
		return vo;
	}
	
	@DeleteMapping("/useraddress/{id}")
    public ResponseEntity<UserAddressDetailsVO> deleteUser(@PathVariable int id) {
		UserAddressDetailsVO vo = service.deleteUserAddressDetails(id);
    	return new ResponseEntity<UserAddressDetailsVO>(vo,HttpStatus.NO_CONTENT);
    }

}
