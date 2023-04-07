package com.alkimi.vo;

import java.time.LocalDate;
import java.util.List;

import com.alkimi.entities.Raffle;
import com.alkimi.entities.RaffleTicket;
import com.alkimi.entities.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

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
@JsonInclude(Include.NON_NULL)
public class RaffleTicketVO {

	private int raffleTicketId;
	
	private UserRefVO userId;
	
	private RaffleRefVO raffleId;
	
	private LocalDate createdOn;

	private List<TicketVO> tickets;
	
	private int ticketId;
	
	public RaffleTicketVO(RaffleTicket raffleTicket) {
		this.raffleTicketId = raffleTicket.getRaffleTicketId();
		RaffleRefVO raffleVO = new RaffleRefVO(raffleTicket.getRaffleId().getRaffleId(),raffleTicket.getRaffleId().getRaffleName());
		this.raffleId = raffleVO;
		UserRefVO user = new UserRefVO(raffleTicket.getUserId().getUserId());
		this.userId = user;
		this.createdOn = raffleTicket.getCreatedOn();
		this.ticketId = raffleTicket.getTicketId();
	}
	
	
	@JsonIgnore
	public RaffleTicket getRaffleTicket() {
		RaffleTicket raffleTicket = new RaffleTicket();
		Raffle raffle = new Raffle(this.getRaffleId().getRaffleId(),this.getRaffleId().getRaffleName());
		raffleTicket.setRaffleId(raffle);
		User user = new User(this.getUserId().getUserId());
		raffleTicket.setUserId(user);
		raffleTicket.setCreatedOn(createdOn);
		return raffleTicket;
	}
	


	
	
}
	
	

