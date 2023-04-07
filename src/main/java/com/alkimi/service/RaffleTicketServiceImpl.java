package com.alkimi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.alkimi.dao.RaffleRepository;
import com.alkimi.dao.RaffleTicketRepository;
import com.alkimi.entities.Raffle;
import com.alkimi.entities.RaffleTicket;
import com.alkimi.exceptions.ClientErrorException;
import com.alkimi.vo.RaffleTicketVO;
import com.alkimi.vo.TicketVO;

@Service
public class RaffleTicketServiceImpl implements RaffleTicketService{

	@Autowired
	private RaffleTicketRepository repository;
	
	@Autowired
	private RaffleRepository raffleRepository;

	@Override
	public List<RaffleTicketVO> createRaffleTicket(RaffleTicketVO vo) {
		System.out.println(vo);
		System.out.println(vo.getRaffleId().getRaffleId());
		System.out.println("======================");
		Optional<Raffle> raffle = raffleRepository.findById(vo.getRaffleId().getRaffleId());
		int maxTickets = 0;
		if(raffle.get()!=null) {
			maxTickets = raffle.get().getMaxTickets();
		}
		List<RaffleTicket> raffleTickets = new ArrayList<>();
		try {
			
			for(TicketVO ticket: vo.getTickets()) {
				RaffleTicket raffleTicket = vo.getRaffleTicket();
				if( ticket.getTicketId() > maxTickets ) {
					throw new ClientErrorException("TicketId is greater than maximum ticket Id");
				}
				raffleTicket.setTicketId(ticket.getTicketId());
				raffleTickets.add(raffleTicket);
			}
			System.out.println(raffleTickets);
			raffleTickets = repository.saveAll(raffleTickets);
			System.out.println(raffleTickets);
		} catch(DataIntegrityViolationException ex) {
			System.out.println("===========================================");
			System.out.println(ex.getMessage());
			System.out.println(ex.getRootCause().getMessage());
			String rootCause = ex.getRootCause().getMessage();
			if(rootCause.contains("USER_ID_FK") || rootCause.contains("user_id_fk")) {
				throw new ClientErrorException(String.format("UserId %d does not exist", vo.getUserId().getUserId()));
			} else if(rootCause.contains("Duplicate")) {
				throw new ClientErrorException(String.format("Ticket already sold for raffle %d", vo.getRaffleId().getRaffleId()));
			} else {
				throw new ClientErrorException(ex.getMessage());
			}

			
		}
		
		List<RaffleTicketVO> raffleTicketsVO = new ArrayList<>();
		raffleTicketsVO = raffleTickets.stream().map(RaffleTicketVO::new).collect(Collectors.toList());
		return raffleTicketsVO;
	}

	@Override
	public List<String> getSoldTicketListByRaffleId(int raffleId) {
		List<RaffleTicket> raffleTickets = repository.findByRaffleIdRaffleId(raffleId);
		System.out.println("Sise is " + raffleTickets.size());
		List<String> ticketList = raffleTickets.stream().map( e -> String.valueOf(e.getTicketId())).collect(Collectors.toList());
		return ticketList;
	}
	
	public List<String> getUnSoldTicketListByRaffleId(int raffleId){
		Optional<Raffle> raffle = raffleRepository.findById(raffleId);
		int maxTickets = 0;
		if(raffle.get()!=null) {
			maxTickets = raffle.get().getMaxTickets();
		}
		
		List<Integer> allIntTcikets = IntStream.range(1, maxTickets + 1)
                .boxed()
                .collect(Collectors.toList());
		
		List<String> allTickets = allIntTcikets.stream().map(i -> i.toString()).collect(Collectors.toList());
		
		List<RaffleTicket> soldRaffleTickets = repository.findByRaffleIdRaffleId(raffleId);
		List<String> soldTickets = soldRaffleTickets.stream().map( e -> String.valueOf(e.getTicketId())).collect(Collectors.toList());
		
		allTickets.removeAll(soldTickets);
		
		return allTickets;
	}



	
	
}
