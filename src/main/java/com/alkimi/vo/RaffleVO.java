package com.alkimi.vo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import com.alkimi.entities.Raffle;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

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
public class RaffleVO {
	
	public RaffleVO(int raffleId,String raffleName) {
		this.raffleId = raffleId;
		this.raffleName = raffleName;
	}
	
	private int raffleId;
	
	private String raffleName;
	
	private int prizeCount;
	
	private int ticketPrize;
	
	private String ticketPrizeLevel;
	
	private String prizeShipmentMethodLevel;
	
	private String prizeShipmentMethod;
	
	private int thirdPartyId;
	
	private List<RafflePrizeDetailsVO> prizes;
	
	private List<RaffleTicketConfigVO> configs;
	
	private List<RafflePrizeShipmentDetailsVO> prizeShipment;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate startDate;
	
	private LocalTime startTime;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate endDate;
	
	private LocalTime endTime;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate drawingDate;
	
	private LocalTime drawingTime;
	
	@NotNull(message ="Enter minimum Tickets")
	private int minTickets;
	
	@NotNull(message ="Enter maximum Tickets")
	private int maxTickets;
	
	private String maxTicketsPrizeLevel;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate createdOn;
	
	private String createdBy;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate updatedOn;
	
	private String updatedBy;
	
	public RaffleVO(Raffle raffle) {
		this.raffleId = raffle.getRaffleId();
		this.raffleName = raffle.getRaffleName();
		this.prizeCount = raffle.getPrizeCount();
		this.ticketPrize = raffle.getTicketPrize();
		this.ticketPrizeLevel = raffle.getTicketPrizeLevel();
		this.prizeShipmentMethodLevel = raffle.getPrizeShipmentMethodLevel();
		this.prizeShipmentMethod = raffle.getPrizeShipmentMethod();
		this.thirdPartyId = raffle.getThirdPartyId();
		this.startDate = raffle.getStartDate();
		this.startTime = raffle.getStartTime();
		this.endDate = raffle.getEndDate();
		this.endTime = raffle.getEndTime();
		this.drawingDate = raffle.getDrawingDate();
		this.drawingTime = raffle.getDrawingTime();
		this.minTickets = raffle.getMinTickets();
		this.maxTickets = raffle.getMaxTickets();
		this.maxTicketsPrizeLevel = raffle.getMaxTicketsPrizeLevel();
		this.createdOn = raffle.getCreatedOn();
		this.createdBy = raffle.getCreatedBy();
		this.updatedOn = raffle.getUpdatedOn();
		this.updatedBy = raffle.getUpdatedBy();
	}
	
	
	@JsonIgnore
	public Raffle getRaffle() {
		Raffle raffle = new Raffle();
		raffle.setRaffleId(raffleId);
		raffle.setRaffleName(raffleName);
		raffle.setPrizeCount(prizeCount);
		raffle.setTicketPrize(ticketPrize);
		raffle.setTicketPrizeLevel(ticketPrizeLevel);
		raffle.setPrizeShipmentMethodLevel(prizeShipmentMethodLevel);
		raffle.setPrizeShipmentMethod(prizeShipmentMethod);
		raffle.setThirdPartyId(thirdPartyId);
		raffle.setStartDate(startDate);
		raffle.setStartTime(startTime);
		raffle.setEndDate(endDate);
		raffle.setEndTime(endTime);
		raffle.setDrawingDate(drawingDate);
		raffle.setDrawingTime(drawingTime);
		raffle.setMinTickets(minTickets);
		raffle.setMaxTickets(maxTickets);
		raffle.setMaxTicketsPrizeLevel(maxTicketsPrizeLevel);		
		raffle.setCreatedOn(createdOn);
		raffle.setCreatedBy(createdBy);
		raffle.setUpdatedOn(updatedOn);
		raffle.setUpdatedBy(updatedBy);
		return raffle;
	}
	
}