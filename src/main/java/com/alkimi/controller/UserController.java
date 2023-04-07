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
import com.alkimi.service.UserService;
import com.alkimi.vo.UserVO;

import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/api")
@Validated
@Slf4j
public class UserController {
	
	@Autowired
	private UserService service;
	    
    @GetMapping("/users")
    public List<UserVO> getAllUser() {
    	log.debug("getAllUsers begin ");

    	List<UserVO> luckyDrawers = service.getAllUser();
    	
    	log.debug("getAllUsers end");
    	return luckyDrawers;
    }
    
    
    
    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserById(@Positive @PathVariable("id") int userId) {
    	log.info("getUserById begin");
    	UserVO vo = null;
  		try {
  			vo = service.getUserById(userId);
  			return new ResponseEntity<UserVO>(vo,HttpStatus.OK);
  		} catch(NoSuchElementException ex) {
  			log.error("Exception caught at getting the Users by Id {} ", userId);
  			throw new ClientErrorException("User Not Found For ID : " + userId);
  		}
  		
    }
   
    
    @PostMapping("/users")
    public ResponseEntity<UserVO> createUser(@Valid @RequestBody UserVO userVO) {
    	System.out.println("createUser begin");
    	userVO.setCreatedOn(LocalDate.now());
    	userVO.setUpdatedOn(LocalDate.now());
    	UserVO vo = service.createUser(userVO);
    	HttpHeaders responseHeaders = new HttpHeaders();
   	    System.out.println("createUser end");
	 	return new ResponseEntity<UserVO>(vo,responseHeaders,HttpStatus.CREATED);
	}
    
    @PostMapping("/multiusers")
    public ResponseEntity<List<UserVO>> createMultiUser(@Valid @RequestBody List<UserVO> userVO) {
    	System.out.println("createMultiUser begin");
    	List<UserVO> userVOs = service.createMultiUser(userVO);
    	HttpHeaders responseHeaders = new HttpHeaders();
   	    System.out.println("createMultiUser end");
	 	return new ResponseEntity<List<UserVO>>(userVOs,responseHeaders,HttpStatus.CREATED);
	}
    
    
    @PutMapping("/users/{userId}")
    public UserVO updateUser(@PathVariable int userId,@Valid @RequestBody UserVO userVO) {
    	UserVO vo =  service.updateUser(userId, userVO);
		return vo;
	}
    
    @DeleteMapping("/users/{userId}")
    public ResponseEntity<UserVO> deleteUser(@PathVariable int userId) {
    	UserVO vo = service.deleteUser(userId);
    	return new ResponseEntity<UserVO>(vo,HttpStatus.NO_CONTENT);
    }
   
}
