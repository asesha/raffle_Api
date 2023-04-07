package com.alkimi.controller;

import java.time.LocalDate;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alkimi.service.UserRaffleService;
import com.alkimi.vo.UserRaffleVO;

import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/api")
@Validated
@Slf4j
public class UserRaffleController {
	
	@Autowired
	private UserRaffleService service;
	    
   
    
    @PostMapping("/userraffles")
    public ResponseEntity<UserRaffleVO> createUserRaffle(@Valid @RequestBody UserRaffleVO userRaffleVO) {
    	log.info("createUserRaffle begin");
    	userRaffleVO.setCreatedOn(LocalDate.now());
    	UserRaffleVO vo = service.createUserRaffle(userRaffleVO);
    	HttpHeaders responseHeaders = new HttpHeaders();
   	    log.info("createUserRaffle end");
	 	return new ResponseEntity<UserRaffleVO>(vo,responseHeaders,HttpStatus.CREATED);
	}
    
    
    
   
}
