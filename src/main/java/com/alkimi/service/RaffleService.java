package com.alkimi.service;

import java.util.List;

import com.alkimi.vo.PrizeDetails;
import com.alkimi.vo.RafflePrizeDetailsVO;
import com.alkimi.vo.RaffleVO;
import com.alkimi.vo.Winners;

public interface RaffleService {
	public List<RaffleVO> getAllRaffle();
	public RaffleVO createRaffle(RaffleVO raffle);
	public RaffleVO getRaffleById(int raffleId);
	public RaffleVO updateRaffle(int raffleId,RaffleVO raffleDetails);
	public RaffleVO deleteRaffle(int raffleId);
	
	
	public RafflePrizeDetailsVO createLuckyDrawWinners(int raffleId,int prizePositionId,int prizeIndexId);
	public List<RafflePrizeDetailsVO> createLuckyDrawWinners(int raffleId, int prizePositionId);
	public List<RafflePrizeDetailsVO> createLuckyDrawWinners(int raffleId);
	public List<Winners> getWinnerDetatils(int raffleId);
	public List<PrizeDetails> getRafflePrizes(int raffleId);
	public List<RaffleVO> getActiveRaffle();
}
