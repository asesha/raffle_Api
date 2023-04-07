package com.alkimi.vo;

import java.time.LocalDate;
import java.util.List;

import com.alkimi.entities.Payment;
import com.alkimi.entities.Raffle;
import com.alkimi.entities.User;
import com.alkimi.entities.UserRaffle;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PaymentVO {

	private int paymentTransactionId;
	
	private UserRefVO userId;
	
	private RaffleRefVO raffleId;
	
	private String paymentType;
	
	private int amount;
	
	private int noOfTickets;
	
	private LocalDate paymentDate;

	private LocalDate lastupdate;

	private String transactionStatus;
	
	private List<TicketVO> tickets;
	

	public PaymentVO(Payment payment) {
		RaffleRefVO raffleVO = new RaffleRefVO(payment.getRaffleId().getRaffleId(),payment.getRaffleId().getRaffleName());
		this.raffleId = raffleVO;
		UserRefVO user = new UserRefVO(payment.getUserId().getUserId());
		this.userId = user;
		this.paymentType = payment.getPaymentType();
		this.amount = payment.getAmount();
		this.noOfTickets = payment.getNoOfTickets();
		this.paymentDate = payment.getPaymentDate();
		this.lastupdate = payment.getLastupdate();
		this.transactionStatus = payment.getTransactionStatus();
	}
	
	@JsonIgnore
	public Payment getPayment() {
		Payment payment = new Payment();
		Raffle raffle = new Raffle(this.getRaffleId().getRaffleId(),this.getRaffleId().getRaffleName());
		payment.setRaffleId(raffle);
		User user = new User(this.getUserId().getUserId());
		payment.setUserId(user);
		
		payment.setPaymentType(paymentType);
		payment.setAmount(amount);
		payment.setNoOfTickets(noOfTickets);
		payment.setPaymentDate(paymentDate);
		payment.setLastupdate(lastupdate);
		payment.setTransactionStatus(transactionStatus);
		
		return payment;
	}
	
	
}
	
	

