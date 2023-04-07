package com.alkimi.validator;

import com.alkimi.vo.RaffleTicketConfigVO;
import com.alkimi.vo.RaffleVO;

import java.util.List;

import com.alkimi.constants.RaffleConstants;
import com.alkimi.exceptions.ClientErrorException;

public class Validator {
	
	public static void raffleCreationValidator(RaffleVO vo) {
		
		if( RaffleConstants.PRIZE_LEVEL_CONFIG.equals(vo.getTicketPrizeLevel()) || RaffleConstants.PRIZE_LEVEL_CONFIG.equals(vo.getMaxTicketsPrizeLevel()) ) {
			List<RaffleTicketConfigVO> configDetails =  vo.getConfigs();
			if( configDetails == null || configDetails.size()==0 ) {
				throw new ClientErrorException("Ticket Prize at each prize or Number of ticket at each prize is not configured properly");
			}
		}

	}

}
