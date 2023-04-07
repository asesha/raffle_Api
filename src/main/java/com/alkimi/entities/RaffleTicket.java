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

import com.alkimi.vo.RaffleRefVO;
import com.alkimi.vo.RaffleTicketVO;
import com.alkimi.vo.RaffleVO;
import com.alkimi.vo.UserRefVO;
import com.alkimi.vo.UserVO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="RAFFLE_TICKETS")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RaffleTicket {

	@Id
	@Column(name="RAFFLE_TICKETS_ID")
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int raffleTicketId;
	
	@ManyToOne
	@JoinColumn(name = "USER_ID_FK", referencedColumnName="USER_ID", nullable = false)
	private User userId;
	
	@ManyToOne
	@JoinColumn(name = "RAFFLE_ID_FK", referencedColumnName="RAFFLE_ID", nullable = false)
	private Raffle raffleId;
	
	@Column(name="CREATED_ON")
	private LocalDate createdOn;

	
	@Column(name="TICKET_ID")
	private int ticketId;
	

	public RaffleTicket(RaffleTicketVO vo) {
		Raffle raffle = new Raffle(vo.getRaffleId().getRaffleId());
		this.raffleId = raffle;
		User user = new User(vo.getUserId().getUserId());
		this.userId = user;
		this.createdOn = vo.getCreatedOn();
	}
	

	public RaffleTicketVO getRaffleTicketVO() {
		RaffleTicketVO vo = new RaffleTicketVO();
		vo.setRaffleTicketId(raffleTicketId);
		RaffleRefVO raffleVO = new RaffleRefVO(this.getRaffleId().getRaffleId(),this.getRaffleId().getRaffleName());
		vo.setRaffleId(raffleVO);
		UserRefVO userVO = new UserRefVO(this.getUserId().getUserId());
		vo.setUserId(userVO);
		vo.setCreatedOn(createdOn);
		return vo;
	}
	
	
}
	
	

