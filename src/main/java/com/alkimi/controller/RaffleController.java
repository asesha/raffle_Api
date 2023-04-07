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
import com.alkimi.service.RaffleService;
import com.alkimi.vo.RafflePrizeDetailsVO;
import com.alkimi.vo.RaffleVO;
import com.alkimi.vo.PrizeDetails;
import com.alkimi.vo.Winners;

import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/api")
@Validated
@Slf4j
public class RaffleController {
	
	@Autowired
	private RaffleService service;
	    
    @GetMapping("/raffles")
    public List<RaffleVO> getAllRaffle() {
    	log.debug("getAllRaffles begin ");

    	List<RaffleVO> raffles = service.getAllRaffle();
    	
    	log.debug("getAllRaffles end");
    	return raffles;
    }
    
    @GetMapping("/raffles/active")
    public List<RaffleVO> getActiveRaffle() {
    	log.debug("getActiveRaffle begin ");

    	List<RaffleVO> luckyDrawers = service.getActiveRaffle();
    	
    	log.debug("getActiveRaffle end");
    	return luckyDrawers;
    }
    
    @GetMapping("/raffles/{id}/prizes")
    public List<PrizeDetails> getRafflePrizes(@Positive @PathVariable("id") int raffleId) {
    	log.debug("getRafflePrizes begin " + raffleId);

    	List<PrizeDetails> prizeDetails = service.getRafflePrizes(raffleId);
    	
    	log.debug("getActiveRaffle end " + raffleId);
    	return prizeDetails;
    }
    
    
    
    @GetMapping("/raffles/{id}")
    public ResponseEntity<?> getRaffleById(@Positive @PathVariable("id") int raffleId) {
    	log.info("getRaffleById begin");
    	RaffleVO vo = null;
  		try {
  			vo = service.getRaffleById(raffleId);
  			return new ResponseEntity<RaffleVO>(vo,HttpStatus.OK);
  		} catch(NoSuchElementException ex) {
  			log.error("Exception caught at getting the RaffleById {} ", raffleId);
  			throw new ClientErrorException("Raffle Not Found For ID : " + raffleId);
  		}
  		
    }
    
    @GetMapping("/raffles/{id}/luckydraw/{prizePositionId}/{prizeIndexId}")
    public ResponseEntity<?> createLuckyDrawWinner(@Positive @PathVariable("id") int raffleId, @PathVariable("prizePositionId") int prizePositionId, @PathVariable("prizeIndexId") int prizeIndexId) {
    	log.info("createLuckyDrawWinner begin");
    	RafflePrizeDetailsVO vo = null;
  		try {
  			vo = service.createLuckyDrawWinners(raffleId,prizePositionId,prizeIndexId);
  			return new ResponseEntity<RafflePrizeDetailsVO>(vo,HttpStatus.OK);
  		} catch(NoSuchElementException ex) {
  			ex.printStackTrace();
  			log.error("Exception caught at createLuckyDrawWinner by Id {} ", raffleId);
  			throw new ClientErrorException("Exception caught at createLuckyDrawWinner ID : " + raffleId);
  		}
  		
    }
    
    @GetMapping("/raffles/{id}/luckydraw/{prizePositionId}")
    public ResponseEntity<?> createLuckyDrawWinner(@Positive @PathVariable("id") int raffledId, @PathVariable("prizePositionId") int prizePositionId) {
    	log.info("createLuckyDrawWinner without prizeIndex begin");
    	List<RafflePrizeDetailsVO> vo = null;
  		try {
  			vo = service.createLuckyDrawWinners(raffledId,prizePositionId);
  			return new ResponseEntity<List<RafflePrizeDetailsVO>>(vo,HttpStatus.OK);
  		} catch(NoSuchElementException ex) {
  			log.error("Exception caught at createLuckyDrawWinner by Id {} ", raffledId);
  			throw new ClientErrorException("Exception caught at createLuckyDrawWinner ID : " + raffledId);
  		}
  		
    }
    
    @GetMapping("/raffles/{id}/luckydraw")
    public ResponseEntity<?> createLuckyDrawWinner(@Positive @PathVariable("id") int raffledId) {
    	log.info("createLuckyDrawWinner for raffle begin " + raffledId);
    	List<RafflePrizeDetailsVO> vo = null;
  		try {
  			vo = service.createLuckyDrawWinners(raffledId);
  			return new ResponseEntity<List<RafflePrizeDetailsVO>>(vo,HttpStatus.OK);
  		} catch(NoSuchElementException ex) {
  			log.error("Exception caught at createLuckyDrawWinner by Id {} ", raffledId);
  			throw new ClientErrorException("Exception caught at createLuckyDrawWinner ID : " + raffledId);
  		}
  		
    }
    
    @GetMapping("/raffles/{id}/winners")
    public ResponseEntity<List<Winners>> getWinnerDetatils(@Positive @PathVariable("id")int raffledId) {
    	List<Winners> winners = service.getWinnerDetatils(raffledId);
    	return new ResponseEntity<List<Winners>>(winners,HttpStatus.OK);
    }
   
    
    @PostMapping("/raffles")
    public ResponseEntity<RaffleVO> createRaffle(@Valid @RequestBody RaffleVO raffleVO) {
    	System.out.println("createRaffle begin");
    	raffleVO.setCreatedOn(LocalDate.now());
    	raffleVO.setUpdatedOn(LocalDate.now());
    	RaffleVO vo = service.createRaffle(raffleVO);
    	HttpHeaders responseHeaders = new HttpHeaders();
   	    System.out.println("createRaffle end");
	 	return new ResponseEntity<RaffleVO>(vo,responseHeaders,HttpStatus.CREATED);
	}
    
    
    @PutMapping("/raffles/{raffleId}")
    public RaffleVO updateRaffle(@PathVariable int raffleId,@Valid @RequestBody RaffleVO raffleVO) {
    	RaffleVO vo =  service.updateRaffle(raffleId, raffleVO);
		return vo;
	}
    
    @DeleteMapping("/raffles/{raffleId}")
    public ResponseEntity<RaffleVO> deleteRaffle(@PathVariable int raffleId) {
    	RaffleVO vo = service.deleteRaffle(raffleId);
    	return new ResponseEntity<RaffleVO>(vo,HttpStatus.NO_CONTENT);
    }
   
}
