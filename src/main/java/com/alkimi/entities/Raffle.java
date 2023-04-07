package com.alkimi.entities;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alkimi.vo.RaffleVO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="RAFFLE")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Raffle {
	
	public Raffle(int raffleId) {
		this.raffleId = raffleId;
	}
	
	public Raffle(int raffleId,String raffleName) {
		this.raffleId = raffleId;
		this.raffleName = raffleName;
	}

	@Id
	@Column(name="RAFFLE_ID")
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int raffleId;
	
	@Column(name="RAFFLE_NAME")
	private String raffleName;
	
	@Column(name="PRIZE_COUNT")
	private int prizeCount;
	
	@Column(name="TICKET_PRIZE")
	private int ticketPrize;
	
	@Column(name="TICKET_PRIZE_LEVEL")
	private String ticketPrizeLevel;
	
	@Column(name="PRIZE_SHIPMENT_METHOD_LEVEL")
	private String prizeShipmentMethodLevel;
	
	@Column(name="PRIZE_SHIPMENT_METHOD")
	private String prizeShipmentMethod;
	
	@Column(name = "THIRD_PARTY_ID_FK")
	private int thirdPartyId;
	
	@Column(name="START_DATE")
	private LocalDate startDate;
	
	@Column(name="START_TIME")
	private LocalTime startTime;
	
	@Column(name="END_DATE")
	private LocalDate endDate;
	
	@Column(name="END_TIME")
	private LocalTime endTime;
	
	@Column(name="DRAWING_DATE")
	private LocalDate drawingDate;
	
	@Column(name="DRAWING_TIME")
	private LocalTime drawingTime;
	
	@Column(name="MIN_TICKETS")
	private int minTickets;
	
	@Column(name="MAX_TICKETS")
	private int maxTickets;
	
	@Column(name="MAX_TICKETS_PRIZE_LEVEL")
	private String maxTicketsPrizeLevel;
	
	@Column(name="CREATED_ON")
	protected LocalDate createdOn;
	
	@Column(name="CREATED_BY")
	protected String createdBy;
	
	@Column(name="UPDATED_ON")
	protected LocalDate updatedOn;
	
	@Column(name="UPDATED_BY")
	protected String updatedBy;
	
	public Raffle(RaffleVO vo) {
		this.raffleId = vo.getRaffleId();
		this.raffleName = vo.getRaffleName();
		this.prizeCount = vo.getPrizeCount();
		this.ticketPrize = vo.getTicketPrize();
		this.ticketPrizeLevel = vo.getTicketPrizeLevel();
		this.prizeShipmentMethodLevel = vo.getPrizeShipmentMethodLevel();
		this.prizeShipmentMethod = vo.getPrizeShipmentMethod();
		this.thirdPartyId = vo.getThirdPartyId();
		this.startDate = vo.getStartDate();
		this.startTime = vo.getStartTime();
		this.endDate = vo.getEndDate();
		this.endTime = vo.getEndTime();
		this.drawingDate = vo.getDrawingDate();
		this.drawingTime = vo.getDrawingTime();
		this.minTickets = vo.getMinTickets();
		this.maxTickets = vo.getMaxTickets();
		this.maxTicketsPrizeLevel = vo.getMaxTicketsPrizeLevel();
		this.createdOn = vo.getCreatedOn();
		this.createdBy = vo.getCreatedBy();
		this.updatedOn = vo.getUpdatedOn();
		this.updatedBy = vo.getUpdatedBy();
		
	}
	

	public RaffleVO getRaffleVO() {
		RaffleVO vo = new RaffleVO();
		vo.setRaffleId(raffleId);
		vo.setRaffleName(raffleName);
		vo.setPrizeCount(prizeCount);
		vo.setTicketPrize(ticketPrize);
		vo.setTicketPrizeLevel(ticketPrizeLevel);
		vo.setPrizeShipmentMethodLevel(prizeShipmentMethodLevel);
		vo.setPrizeShipmentMethod(prizeShipmentMethod);
		vo.setThirdPartyId(thirdPartyId);
		vo.setStartDate(startDate);
		vo.setStartTime(startTime);
		vo.setEndDate(endDate);
		vo.setEndTime(endTime);
		vo.setDrawingDate(drawingDate);
		vo.setDrawingTime(drawingTime);
		vo.setMinTickets(minTickets);
		vo.setMaxTickets(maxTickets);
		vo.setMaxTicketsPrizeLevel(maxTicketsPrizeLevel);
		vo.setCreatedOn(createdOn);
		vo.setCreatedBy(createdBy);
		vo.setUpdatedOn(updatedOn);
		vo.setUpdatedBy(updatedBy);
		return vo;
	}
	
	
}
	
	

