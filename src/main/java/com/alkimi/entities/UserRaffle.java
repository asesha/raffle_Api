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
import com.alkimi.vo.UserRaffleVO;
import com.alkimi.vo.UserRefVO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="USER_RAFFLE")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserRaffle {

	@Id
	@Column(name="RAFFLE_ACTION_ID")
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int userRaffleId;
	
	@ManyToOne
	@JoinColumn(name = "USER_ID_FK", referencedColumnName="USER_ID", nullable = false)
	private User userId;
	
	@ManyToOne
	@JoinColumn(name = "RAFFLE_ID_FK", referencedColumnName="RAFFLE_ID", nullable = false)
	private Raffle raffleId;
	
	@Column(name="CREATED_ON")
	private LocalDate createdOn;

	
	@Column(name="STATUS")
	private String status;
	

	public UserRaffle(UserRaffleVO vo) {
		Raffle raffle = new Raffle(vo.getRaffleId().getRaffleId(),vo.getRaffleId().getRaffleName());
		this.raffleId = raffle;
		User user = new User(vo.getUserId().getUserId());
		this.userId = user;
		this.createdOn = vo.getCreatedOn();
		this.status = vo.getStatus();
	}
	

	public UserRaffleVO getUserRaffleVO() {
		UserRaffleVO vo = new UserRaffleVO();
		vo.setUserRaffleId(userRaffleId);
		RaffleRefVO raffleVO = new RaffleRefVO(this.getRaffleId().getRaffleId(),this.getRaffleId().getRaffleName());
		vo.setRaffleId(raffleVO);
		UserRefVO userVO = new UserRefVO(this.getUserId().getUserId());
		vo.setUserId(userVO);
		vo.setCreatedOn(createdOn);
		vo.setStatus(status);
		return vo;
	}
	
	
}
	
	

