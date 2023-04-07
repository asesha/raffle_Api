package com.alkimi.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alkimi.service.RaffleTicketService;
import com.alkimi.vo.RaffleTicketVO;

import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/api")
@Validated
@Slf4j
public class RaffleTicketController {
	
	@Autowired
	private RaffleTicketService service;
	    
   
    
    @PostMapping("/raffletickets")
    public ResponseEntity<List<RaffleTicketVO>> createRaffleTicket(@Valid @RequestBody RaffleTicketVO raffleTicketVO) {
    	log.info("createRaffleTicket begin");
    	raffleTicketVO.setCreatedOn(LocalDate.now());
    	List<RaffleTicketVO> vo = service.createRaffleTicket(raffleTicketVO);
    	HttpHeaders responseHeaders = new HttpHeaders();
   	    log.info("createRaffleTicket end");
	 	return new ResponseEntity<List<RaffleTicketVO>>(vo,responseHeaders,HttpStatus.CREATED);
	}
    
    
    @GetMapping("/raffletickets/{id}/sold")
    public List<String> getSoldTickets(@PathVariable("id") int raffleId) {
    	System.out.println("getSoldTickets begin ");

    	List<String> tickets = service.getSoldTicketListByRaffleId(raffleId);
    	
    	System.out.println("getSoldTickets end");
    	return tickets;
    }
    
    @GetMapping("/raffletickets/{id}/unsold")
    public List<String> getUnSoldTickets(@PathVariable("id") int raffleId) {
    	System.out.println("getUnSoldTickets begin ");

    	List<String> tickets = service.getUnSoldTicketListByRaffleId(raffleId);
    	
    	System.out.println("getUnSoldTickets end");
    	return tickets;
    }
    
    
   
}
