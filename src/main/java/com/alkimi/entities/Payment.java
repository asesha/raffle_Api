package com.alkimi.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.alkimi.vo.PaymentVO;
import com.alkimi.vo.RaffleRefVO;
import com.alkimi.vo.UserRefVO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="PAYMENT")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

	@Id
	@Column(name="PAYMENT_TRANSACTION_ID")
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int paymentTransactionId;
	
	@ManyToOne
	@JoinColumn(name = "USER_ID_FK", referencedColumnName="USER_ID", nullable = false)
	private User userId;
	
	@ManyToOne
	@JoinColumn(name = "RAFFLE_ID_FK", referencedColumnName="RAFFLE_ID", nullable = false)
	private Raffle raffleId;
	
	@Column(name="PAYMENT_TYPE")
	private String paymentType;
	
	@Column(name="AMOUNT")
	private int amount;
	
	@Column(name="NO_OF_TICKETS")
	private int noOfTickets;
	
	@Column(name="PAYMENT_DATE")
	private LocalDate paymentDate;
	
	@Column(name="LAST_UPDATE")
	private LocalDate lastupdate;

	
	@Column(name="TRANSACTION_STATUS")
	private String transactionStatus;
	

	public Payment(PaymentVO vo) {
		Raffle raffle = new Raffle(vo.getRaffleId().getRaffleId(),vo.getRaffleId().getRaffleName());
		this.raffleId = raffle;
		User user = new User(vo.getUserId().getUserId());
		this.userId = user;
		this.paymentType = vo.getPaymentType();
		this.amount = vo.getAmount();
		this.noOfTickets = vo.getNoOfTickets();
		this.paymentDate = vo.getPaymentDate();
		this.lastupdate = vo.getLastupdate();
		this.transactionStatus = vo.getTransactionStatus();

	}
	

	public PaymentVO getPaymentVO() {
		PaymentVO vo = new PaymentVO();
		RaffleRefVO raffleVO = new RaffleRefVO(this.getRaffleId().getRaffleId(),this.getRaffleId().getRaffleName());
		vo.setRaffleId(raffleVO);
		UserRefVO userVO = new UserRefVO(this.getUserId().getUserId());
		vo.setPaymentTransactionId(paymentTransactionId);
		vo.setUserId(userVO);
		vo.setPaymentType(paymentType);
		vo.setAmount(amount);
		vo.setNoOfTickets(noOfTickets);
		vo.setPaymentDate(paymentDate);
		vo.setLastupdate(lastupdate);
		vo.setTransactionStatus(transactionStatus);

		return vo;
	}
	
	
}
	
	

