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

import com.alkimi.vo.RafflePrizeDetailsVO;
import com.alkimi.vo.RaffleRefVO;
import com.alkimi.vo.RaffleTicketConfigVO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "RAFFLE_TICKET_CONFIG")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RaffleTicketConfig {

	@Id
	@Column(name = "TICKET_CONFIG_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ticketConfigId;
	
	@Column(name = "PRIZE_POSITION")
	private int prizePosition;
	
	@Column(name = "TICKET_PRIZE")
	private int ticketPrize;
	
	@Column(name="MAX_TICKETS")
	private int maxTickets;

	@ManyToOne
	@JoinColumn(name = "RAFFLE_ID_FK", referencedColumnName = "RAFFLE_ID", nullable = false)
	private Raffle raffleId;
	

	public RaffleTicketConfig(RaffleTicketConfigVO vo) {
		Raffle raffle = new Raffle(vo.getRaffleId().getRaffleId());
		this.raffleId = raffle;
		this.prizePosition = vo.getPrizePosition();
		this.ticketPrize = vo.getTicketPrize();
		this.maxTickets = vo.getMaxTickets();
	}

	public RaffleTicketConfigVO getRaffleTicketConfigVO() {
		RaffleTicketConfigVO vo = new RaffleTicketConfigVO();
		vo.setTicketConfigId(ticketConfigId);
		RaffleRefVO raffleVO = new RaffleRefVO(this.getRaffleId().getRaffleId(),
				this.getRaffleId().getRaffleName());
		vo.setRaffleId(raffleVO);
		vo.setPrizePosition(prizePosition);
		vo.setTicketPrize(ticketPrize);
		vo.setMaxTickets(maxTickets);
		return vo;
	}

}
