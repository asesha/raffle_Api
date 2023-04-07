package com.alkimi.service;

import java.util.List;

import com.alkimi.vo.RaffleTicketVO;

public interface RaffleTicketService {
	public List<RaffleTicketVO> createRaffleTicket(RaffleTicketVO vo);
	public List<String> getSoldTicketListByRaffleId(int raffleId);
	public List<String> getUnSoldTicketListByRaffleId(int raffleId);
}
