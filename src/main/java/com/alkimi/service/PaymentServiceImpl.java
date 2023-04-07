package com.alkimi.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkimi.dao.PaymenRepository;
import com.alkimi.dao.RaffleRepository;
import com.alkimi.dao.RaffleTicketRepository;
import com.alkimi.entities.Payment;
import com.alkimi.entities.Raffle;
import com.alkimi.entities.RaffleTicket;
import com.alkimi.entities.User;
import com.alkimi.exceptions.ClientErrorException;
import com.alkimi.vo.MailRequest;
import com.alkimi.vo.PaymentVO;
import com.alkimi.vo.TicketVO;




@Service
public class PaymentServiceImpl implements PaymentService {
	
	@Autowired
	private PaymenRepository repository;
	
	@Autowired
	private RaffleRepository raffleRepository;
	
	@Autowired
	private RaffleTicketRepository raffleTicketRepository;
	
	@Autowired
	private EmailService mailService;



	public PaymentVO createPaymentv1(PaymentVO vo) {
		
		Payment payment = repository.save(vo.getPayment());
		
		int raffleId = vo.getRaffleId().getRaffleId();
		Raffle raffle = getRaffle(vo.getRaffleId().getRaffleId());
		int maxTickets = raffle.getMaxTickets();
		
		//Ticket Booked for a RaffleId
		List<RaffleTicket> bookedTickets = raffleTicketRepository.findByRaffleIdRaffleId(raffleId);
		System.out.println("Size is " + bookedTickets.size());
		List<String> ticketList = bookedTickets.stream().map( e -> String.valueOf(e.getTicketId())).collect(Collectors.toList());
		System.out.println("Tickets booked are  " + ticketList );
		
		List<RaffleTicket> raffleTickets = new ArrayList<>();
		
		Random random = new Random();
		int ticketId = 0;
		User userId = new User(vo.getUserId().getUserId());
		
		for(int i=0; i < vo.getNoOfTickets(); i++) {
			do {
				ticketId = random.nextInt((maxTickets - 1) + 1) + 1;
				System.out.println("Generated Random Ticket is " + ticketId);
			} while(ticketList.contains(String.valueOf(ticketId)));
			System.out.println("Random Ticket not found in the list" + ticketId);
			
			RaffleTicket raffleTicket = new RaffleTicket();
			raffleTicket.setTicketId(ticketId);
			raffleTicket.setRaffleId(raffle);
			raffleTicket.setUserId(userId);
			raffleTicket.setCreatedOn(LocalDate.now());
			raffleTickets.add(raffleTicket);
		}
		
		raffleTickets = raffleTicketRepository.saveAll(raffleTickets);
		List<TicketVO> userTicketNumbers = new ArrayList<>();
		raffleTickets.stream().map( e-> {
			TicketVO ticketVO = new TicketVO();
			ticketVO.setTicketId(e.getTicketId());
			return vo;
		}).collect(Collectors.toList());
		
		PaymentVO responseVO = payment.getPaymentVO();
		responseVO.setTickets(userTicketNumbers);
				
		return payment.getPaymentVO();
	}
	
	
	@Override
	public PaymentVO createPayment(PaymentVO vo) {
		
		Payment payment = repository.save(vo.getPayment());
		
		int raffleId = vo.getRaffleId().getRaffleId();
		Raffle raffle = getRaffle(vo.getRaffleId().getRaffleId());
		int maxTickets = raffle.getMaxTickets();
		
		//Total tickets for a RaffleId
		List<Integer> allTcikets = IntStream.range(1, maxTickets + 1).boxed().collect(Collectors.toList());
		//Solded Tickets 
		List<RaffleTicket> soldRaffleTickets = raffleTicketRepository.findByRaffleIdRaffleId(raffleId);
		List<Integer> soldedTickets = soldRaffleTickets.stream().mapToInt( e -> e.getTicketId()).boxed().collect(Collectors.toList());
		
		allTcikets.removeAll(soldedTickets); // Available Tickets
		
		int[] availableTickets = allTcikets.stream().mapToInt( e -> e.intValue()).toArray();
		
		List<RaffleTicket> raffleTickets = new ArrayList<>();
		
		User userId = new User(vo.getUserId().getUserId());
		
		for(int k=0; k < vo.getNoOfTickets(); k++) {
			
			int index =   new Random().nextInt(availableTickets.length);
			int ticketId = availableTickets[index];
			
			System.out.println("Generated ticker number is : " + ticketId);
			
			int[] copy = new int[availableTickets.length - 1];

			for (int i = 0, j = 0; i < availableTickets.length; i++) {
			    if (i != index) {
			        copy[j++] = availableTickets[i];
			    }
			}
			RaffleTicket raffleTicket = new RaffleTicket();
			raffleTicket.setTicketId(ticketId);
			raffleTicket.setRaffleId(raffle);
			raffleTicket.setUserId(userId);
			raffleTicket.setCreatedOn(LocalDate.now());
			raffleTickets.add(raffleTicket);
		
			availableTickets = copy;
		

		}
		
		raffleTickets = raffleTicketRepository.saveAll(raffleTickets);
		List<TicketVO> userTicketNumbers = new ArrayList<>();
		userTicketNumbers = raffleTickets.stream().map( e-> {
			TicketVO ticketVO = new TicketVO();
			ticketVO.setTicketId(e.getTicketId());
			return ticketVO;
		}).collect(Collectors.toList());
		
		PaymentVO responseVO = payment.getPaymentVO();
		responseVO.setTickets(userTicketNumbers);
		
		Map<String, Object> model = new HashMap<>();
		model.put("userName", "Balachandar");
		model.put("ticketNumbers", userTicketNumbers.toString());
		
		//String adminEmail = "balachandar.nadar@alkimi.org";
		String adminEmail = "sbalaaa@gmail.com";
		MailRequest mailRequest = new MailRequest();
		mailRequest.setEmailTo(new String[] { adminEmail });
		mailRequest.setSubject("Order Confirmation");
		mailRequest.setModel(model);
		try {
			mailService.sendMail(mailRequest, "orderConfirmation");
		} catch (MessagingException e1) {
			e1.printStackTrace();
		}
		
		return responseVO;
	}
	
	
	
	
	
	
	
	private Raffle getRaffle(int raffleId ) {
		Optional<Raffle> raffle = raffleRepository.findById(raffleId);
		
		if(raffle.isPresent()) {
			return raffle.get();
		} else {
			throw new ClientErrorException(String.format("Raffle Id %d does not exist", raffleId));
		}
	}
	

	@Override
	public PaymentVO getPaymentById(int paymentId) {
		Optional<Payment> payment = repository.findById(paymentId);
		return payment.get().getPaymentVO();
	}

	@Override
	public PaymentVO updatePayment(int paymentId, PaymentVO vo) {
		Optional<Payment> payment = repository.findById(paymentId);
		Payment modifiedPayment = null;
		if(payment.get() != null) {
			modifiedPayment = payment.get();
			modifiedPayment.setLastupdate(LocalDate.now());
			modifiedPayment = repository.save(modifiedPayment);
	    }
		return payment.get().getPaymentVO();
	}


}
