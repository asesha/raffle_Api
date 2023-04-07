package com.alkimi.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkimi.dao.RafflePrizeDetailsRepository;
import com.alkimi.dao.RafflePrizeShipmentRepository;
import com.alkimi.dao.RaffleRepository;
import com.alkimi.dao.RaffleTicketConfigRepository;
import com.alkimi.dao.RaffleTicketRepository;
import com.alkimi.entities.Raffle;
import com.alkimi.entities.RafflePrizeDetails;
import com.alkimi.entities.RafflePrizeShipmentDetails;
import com.alkimi.entities.RaffleTicket;
import com.alkimi.entities.RaffleTicketConfig;
import com.alkimi.entities.Product;
import com.alkimi.entities.User;
import com.alkimi.exceptions.ClientErrorException;
import com.alkimi.validator.Validator;
import com.alkimi.vo.RafflePrizeDetailsVO;
import com.alkimi.vo.RafflePrizeShipmentDetailsVO;
import com.alkimi.vo.RaffleTicketConfigVO;
import com.alkimi.vo.RaffleVO;
import com.alkimi.vo.PrizeDetails;
import com.alkimi.vo.Winners;

@Service
public class RaffleServiceImpl implements RaffleService{

	@Autowired
	private RaffleRepository repository;
	
	@Autowired
	private RaffleTicketRepository raffleTicketRepository;
	
	@Autowired
	private RafflePrizeDetailsRepository rafflePrizeDetailsRepository;
	
	@Autowired
	private RaffleTicketConfigRepository configRepository;
	
	@Autowired
	private RafflePrizeShipmentRepository shipmentRepository;
	
	@Override
	public List<RaffleVO> getAllRaffle() {
		List<Raffle> raffles = repository.findAll();
    	List<RaffleVO> raffleVOs = raffles.stream().map(RaffleVO::new).collect(Collectors.toList());
    	return raffleVOs;
	}

	@Override
	public RaffleVO createRaffle(RaffleVO vo) {
		
		Validator.raffleCreationValidator(vo);
		
		Raffle raffle = repository.save(vo.getRaffle());
		
		List<RafflePrizeDetailsVO> prizeDetails =  vo.getPrizes();
		List<RafflePrizeDetails> createPrizeDetails =  prizeDetails.stream().map( e -> {
			RafflePrizeDetails prizeDetail = new RafflePrizeDetails();
			prizeDetail.setRaffleId(raffle);
			prizeDetail.setProductId(new Product(e.getProductId().getProductId()));
			prizeDetail.setPrizePosition(e.getPrizePosition());
			prizeDetail.setPrizeIndex(e.getPrizeIndex());
			prizeDetail.setCreatedOn(LocalDate.now());
			return prizeDetail;
		}).collect(Collectors.toList());
		
		createPrizeDetails = rafflePrizeDetailsRepository.saveAll(createPrizeDetails);
		
		prizeDetails = createPrizeDetails.stream().map(RafflePrizeDetailsVO::new).collect(Collectors.toList());
		
		RaffleVO response = raffle.getRaffleVO();
		response.setPrizes(prizeDetails);
		
		List<RaffleTicketConfigVO> configDetails =  vo.getConfigs();
		List<RaffleTicketConfig> createConfigDetails =  configDetails.stream().map( e -> {
			RaffleTicketConfig configDetail = new RaffleTicketConfig();
			configDetail.setRaffleId(raffle);
			configDetail.setPrizePosition(e.getPrizePosition());
			configDetail.setTicketPrize(e.getTicketPrize());
			configDetail.setMaxTickets(e.getMaxTickets());
			return configDetail;
		}).collect(Collectors.toList());
		
		createConfigDetails = configRepository.saveAll(createConfigDetails);
		configDetails = createConfigDetails.stream().map(RaffleTicketConfigVO::new).collect(Collectors.toList());
		response.setConfigs(configDetails);
		
		List<RafflePrizeShipmentDetailsVO> prizeShipments = vo.getPrizeShipment();
		List<RafflePrizeShipmentDetails> createPrizeShipments =  prizeShipments.stream().map( e -> {
			RafflePrizeShipmentDetails prizeShipment = new RafflePrizeShipmentDetails();
			prizeShipment.setRaffleId(raffle);
			prizeShipment.setPrizePosition(e.getPrizePosition());
			prizeShipment.setPrizeIndex(e.getPrizeIndex());
			prizeShipment.setPrizeShipmentMethod(e.getPrizeShipmentMethod());
			prizeShipment.setThirdPartyId(e.getThirdPartyId());
			prizeShipment.setCreatedOn(LocalDate.now());
			return prizeShipment;
		}).collect(Collectors.toList());
		createPrizeShipments = shipmentRepository.saveAll(createPrizeShipments);
		prizeShipments = createPrizeShipments.stream().map(RafflePrizeShipmentDetailsVO::new).collect(Collectors.toList());
		response.setPrizeShipment(prizeShipments);
		
		return response;
	}
	
	public List<Winners> getWinnerDetatils(int raffleId) {
		return rafflePrizeDetailsRepository.getWinnerDetatils(raffleId);
	}

	@Override
	public RaffleVO getRaffleById(int raffleId) {
		Optional<Raffle> raffle = repository.findById(raffleId);
		return raffle.get().getRaffleVO();
	}

	@Override
	public RaffleVO updateRaffle(int raffleId,RaffleVO raffleDetails) {
		Optional<Raffle> raffle = repository.findById(raffleId);
		Raffle modifiedRaffle = null;
		if(raffle.get() != null) {
			modifiedRaffle = raffle.get();
			updateRaffle(modifiedRaffle,raffleDetails);
			if(!raffleDetails.getUpdatedBy().isEmpty()) {
				modifiedRaffle.setUpdatedBy(raffleDetails.getUpdatedBy());
			}
			modifiedRaffle.setUpdatedOn(LocalDate.now());
	    	modifiedRaffle = repository.save(modifiedRaffle);
	    }
		return modifiedRaffle.getRaffleVO();
	}

	
	@Override
	public RaffleVO deleteRaffle(int raffleId) {
		Optional<Raffle> raffle = repository.findById(raffleId);
		if(raffle.get() != null) {
			repository.delete(raffle.get());
	    }
		return raffle.get().getRaffleVO();
	}
	
	private void updateRaffle(Raffle modifiedRaffle, RaffleVO raffleDetails ) {
		updateRaffleName(modifiedRaffle,raffleDetails);
		updatePrizeCount(modifiedRaffle,raffleDetails);
		updateRaffleStartDetails(modifiedRaffle,raffleDetails);
		updateRaffleEndDetails(modifiedRaffle,raffleDetails);
		updateRaffleDrawingDetails(modifiedRaffle,raffleDetails);
		updateMixMaxTickets(modifiedRaffle,raffleDetails);
	}

	private void updatePrizeCount(Raffle modifiedRaffle, RaffleVO raffleDetails) {
		if(raffleDetails.getPrizeCount()!=0) {
			modifiedRaffle.setPrizeCount(raffleDetails.getPrizeCount());
		}
	}

	private void updateMixMaxTickets(Raffle modifiedRaffle, RaffleVO raffleDetails) {
		if(raffleDetails.getMinTickets()!=0) {
			modifiedRaffle.setMinTickets(raffleDetails.getMinTickets());
		}
		
		if(raffleDetails.getMaxTickets()!=0) {
			modifiedRaffle.setMaxTickets(raffleDetails.getMaxTickets());
		}
		
	}

	private void updateRaffleDrawingDetails(Raffle modifiedRaffle, RaffleVO raffleDetails) {
		
		if(raffleDetails.getDrawingDate()!=null) {
			modifiedRaffle.setDrawingDate(raffleDetails.getDrawingDate());
		}
		
		if(raffleDetails.getDrawingTime()!=null) {
			modifiedRaffle.setDrawingTime(raffleDetails.getDrawingTime());
		}
	}

	private void updateRaffleEndDetails(Raffle modifiedRaffle, RaffleVO raffleDetails) {
		
		if(raffleDetails.getEndDate()!=null) {
			modifiedRaffle.setEndDate(raffleDetails.getEndDate());
		}
		
		if(raffleDetails.getEndTime()!=null) {
			modifiedRaffle.setEndTime(raffleDetails.getEndTime());
		}
	}

	private void updateRaffleStartDetails(Raffle modifiedRaffle, RaffleVO raffleDetails) {
		
		if(raffleDetails.getStartDate()!=null) {
			modifiedRaffle.setStartDate(raffleDetails.getStartDate());
		}
		
		if(raffleDetails.getEndTime()!=null) {
			modifiedRaffle.setEndTime(raffleDetails.getEndTime());
		}
		
	}

	private void updateRaffleName(Raffle modifiedRaffle, RaffleVO raffleDetails) {
		if(raffleDetails.getRaffleName()!=null) {
			modifiedRaffle.setRaffleName(raffleDetails.getRaffleName());
		}
	}

	@Override
	public RafflePrizeDetailsVO createLuckyDrawWinners(int raffleId, int prizePositionId,int prizeIndexId) {
		System.out.println("Raffle ID is " + raffleId  + " prizePositionId" + prizePositionId + " prizeIndexId" + prizeIndexId);
		Raffle raffle = getRaffle(raffleId);
		
		int ticketId = getLuckyTicket(raffle);
		
		User winnerId = getWinner(raffleId,ticketId);
		System.out.println("Winner is " + winnerId);
		
		RafflePrizeDetailsVO raffleWinnder = setPrizeDetails(raffle, raffleId, prizePositionId, prizeIndexId, winnerId, ticketId);
		
		return raffleWinnder;
	}
	
	
	@Override
	public List<RafflePrizeDetailsVO> createLuckyDrawWinners(int raffleId, int prizePositionId) {
		
		System.out.println("Raffle ID -> " + raffleId  + " prizePositionId -> " + prizePositionId);
		
		Raffle raffle = getRaffle(raffleId);
		List<Integer> prizeIndex = rafflePrizeDetailsRepository.getPrizeIndexByRaffleIdAndPrizePosition(raffle, prizePositionId).get();
		System.out.println("prizeIndexs are " + prizeIndex);
		
		List<RafflePrizeDetailsVO> prizeDetailList = new ArrayList<>();
		
		
		for(int index : prizeIndex ) {
			
			int ticketId = getLuckyTicket(raffle);
			
			User winnerId = getWinner(raffleId,ticketId);
			
			RafflePrizeDetailsVO raffleWinner = setPrizeDetails(raffle, raffleId, prizePositionId, index, winnerId, ticketId);
			
			prizeDetailList.add(raffleWinner);
		}
		
		return prizeDetailList;
	}
	
	@Override
	public List<RafflePrizeDetailsVO> createLuckyDrawWinners(int raffleId) {
		
		System.out.println("Raffle ID -> " + raffleId );
		
		Raffle raffle = getRaffle(raffleId);
		
		List<Integer> prizePositions =  rafflePrizeDetailsRepository.getPrizePositionByRaffleId(raffle).get();
		System.out.println("prizePositions are " + prizePositions);
		
		List<RafflePrizeDetailsVO> prizeDetailList = new ArrayList<>();
		
		for(int prizePositionId : prizePositions ) {
		
			List<Integer> prizeIndex = rafflePrizeDetailsRepository.getPrizeIndexByRaffleIdAndPrizePosition(raffle, prizePositionId).get();
			System.out.println("prizeIndexs are " + prizeIndex);
			
			for(int index : prizeIndex ) {
				
				int ticketId = getLuckyTicket(raffle);
				
				User winnerId = getWinner(raffleId,ticketId);
				
				RafflePrizeDetailsVO raffleWinner = setPrizeDetails(raffle, raffleId, prizePositionId, index, winnerId, ticketId);
				
				prizeDetailList.add(raffleWinner);
			}
		}
		
		return prizeDetailList;
	}
	
	
	private Raffle getRaffle(int raffleId ) {
		Optional<Raffle> raffle = repository.findById(raffleId);
		
		if(raffle.get()!=null) {
			return raffle.get();
		} else {
			throw new ClientErrorException(String.format("Raffle Id %d does not exist", raffleId));
		}
	}
	
	private int getLuckyTicket(Raffle raffle ) {
		
		int maxTickets = raffle.getMaxTickets();
		System.out.println("Number of availability Tickets " + maxTickets);
		int ticketId = 0;
		Random random = new Random();
		List<Integer> luckyTickets = new ArrayList<>();
		Optional<List<Integer>> optionalLuckyTickets = rafflePrizeDetailsRepository.getTicketNumbersByRaffleId(raffle);
		if(optionalLuckyTickets.get()!=null) {
			luckyTickets = optionalLuckyTickets.get();
		}
		
		do {
			ticketId = random.nextInt((maxTickets - 1) + 1) + 1;
			System.out.println("Generated Random Ticket is " + ticketId);
		} while(luckyTickets.contains(ticketId));
		
		return ticketId;
	}
	
	private User getWinner(int raffleId, int ticketId) {
		System.out.println("Raffle ID is ->  " + raffleId  + " ticketId -> " + ticketId);
		Optional<RaffleTicket> raffleTicket = raffleTicketRepository.findByRaffleIdRaffleIdAndTicketId(raffleId,ticketId);
		
		User winnerId = null;
		if( raffleTicket.isPresent() ) {
			winnerId = raffleTicket.get().getUserId();
			System.out.println("Winner is " + winnerId);
		}
		
		return winnerId;
	}
	
	
	private RafflePrizeDetailsVO setPrizeDetails(Raffle raffle, int raffleId, int prizePositionId, int prizeIndexId, User winnerId, int ticketId) {
		System.out.println("Raffle ID -> " + raffleId  + " prizePositionId -> " + prizePositionId + " prizeIndexId -> " + prizeIndexId + " winnerId -> " + winnerId + " ticketId -> " + ticketId );
		
		RafflePrizeDetailsVO winnerRaffle = null;
		Optional<RafflePrizeDetails> prizeDetail = rafflePrizeDetailsRepository.findByRaffleIdAndPrizePositionAndPrizeIndex(raffle,prizePositionId,prizeIndexId);
		
		RafflePrizeDetails detail;
		if( prizeDetail.isPresent() ) {
			detail = prizeDetail.get();
			if(detail.getWinnerId()!=null) {
				throw new ClientErrorException(String.format("Lucky draw is already done for this raffle Id %d prize %d", raffleId, prizePositionId));
			}
			detail.setWinnerId(winnerId);
			winnerRaffle = new RafflePrizeDetailsVO(detail,winnerId);
			detail.setUpdatedBy("LUCKYDRAW");
			detail.setUpdatedOn(LocalDate.now());
			detail.setTicketId(ticketId);
			rafflePrizeDetailsRepository.save(detail);
		} else {
			throw new ClientErrorException(String.format("Lucky draw is not available for this raffle Id %d prize %d prizeIndex %d", raffleId, prizePositionId,prizeIndexId));
		}
		winnerRaffle.setWinnerTicketId(ticketId);
		return winnerRaffle;
	}

	@Override
	public List<RaffleVO> getActiveRaffle() {
		List<Raffle> raffles = repository.findByEndDateAfter(LocalDate.now());
		List<RaffleVO> raffleVOList = raffles.stream().map(RaffleVO::new).collect(Collectors.toList());
		return raffleVOList;
	}

	@Override
	public List<PrizeDetails> getRafflePrizes(int raffleId) {
		List<PrizeDetails> prizeList = rafflePrizeDetailsRepository.getPrizeDetails(raffleId);
		return prizeList;
	}

	
}
