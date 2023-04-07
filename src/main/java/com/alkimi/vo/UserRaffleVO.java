package com.alkimi.vo;

import java.time.LocalDate;

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
public class UserRaffleVO {

	private int userRaffleId;
	
	private UserRefVO userId;
	
	private RaffleRefVO raffleId;
	
	private LocalDate createdOn;

	private String status;
	
	public UserRaffleVO(UserRaffle userRaffle) {
		RaffleRefVO raffleVO = new RaffleRefVO(userRaffle.getRaffleId().getRaffleId(),userRaffle.getRaffleId().getRaffleName());
		this.raffleId = raffleVO;
		UserRefVO user = new UserRefVO(userRaffle.getUserId().getUserId());
		this.userId = user;
		this.createdOn = userRaffle.getCreatedOn();
		this.status = userRaffle.getStatus();
	}
	
	
	@JsonIgnore
	public UserRaffle getUserRaffle() {
		UserRaffle userRaffle = new UserRaffle();
		Raffle raffle = new Raffle(this.getRaffleId().getRaffleId(),this.getRaffleId().getRaffleName());
		userRaffle.setRaffleId(raffle);
		User user = new User(this.getUserId().getUserId());
		userRaffle.setUserId(user);
		userRaffle.setCreatedOn(createdOn);
		userRaffle.setStatus(status);
		return userRaffle;
	}
	


	
	
}
	
	

