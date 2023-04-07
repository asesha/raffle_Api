package com.alkimi.vo;

import com.alkimi.entities.Raffle;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RaffleRefVO {
	
	private int raffleId;
	
	private String raffleName;
	
	public RaffleRefVO(int raffleId) {
		this.raffleId = raffleId;
	}
	
	
	public RaffleRefVO(Raffle raffle) {
		this.raffleId = raffle.getRaffleId();
	}

}