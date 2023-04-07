package com.alkimi.vo;

import com.alkimi.entities.Raffle;
import com.alkimi.entities.RaffleTicketConfig;
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
public class RaffleTicketConfigVO {

	private int ticketConfigId;
	
	private int prizePosition;
	
	private int ticketPrize;
	
	private int maxTickets;

	private RaffleRefVO raffleId;
	

	public RaffleTicketConfigVO(RaffleTicketConfig config) {
		this.ticketConfigId = config.getTicketConfigId();
		RaffleRefVO raffle = new RaffleRefVO(config.getRaffleId().getRaffleId());
		this.raffleId = raffle;
		this.prizePosition = config.getPrizePosition();
		this.ticketPrize = config.getTicketPrize();
		this.maxTickets = config.getMaxTickets();
	}

	@JsonIgnore
	public RaffleTicketConfig getRaffleTicketConfig() {
		RaffleTicketConfig config = new RaffleTicketConfig();
		Raffle raffle = new Raffle(this.getRaffleId().getRaffleId(),this.getRaffleId().getRaffleName());
		config.setRaffleId(raffle);
		config.setPrizePosition(prizePosition);
		config.setTicketPrize(ticketPrize);
		config.setMaxTickets(maxTickets);
		return config;
	}

}
