package com.alkimi.vo;

import java.time.LocalDate;

import com.alkimi.entities.Raffle;
import com.alkimi.entities.RafflePrizeShipmentDetails;
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
public class RafflePrizeShipmentDetailsVO {

	private int rafflePrizeShipmentId;

	private RaffleRefVO raffleId;

	private int prizePosition;
	
	private int prizeIndex;
	
	private String prizeShipmentMethod;
	
	private int thirdPartyId;
	
	private LocalDate createdOn;

	private String createdBy;

	private LocalDate updatedOn;

	private String updatedBy;
	
	public RafflePrizeShipmentDetailsVO(RafflePrizeShipmentDetails rafflePrizeShipmentDetails) {
		this.rafflePrizeShipmentId = rafflePrizeShipmentDetails.getRafflePrizeShipmentId();
		RaffleRefVO raffleVO = new RaffleRefVO(rafflePrizeShipmentDetails.getRaffleId().getRaffleId());
		this.raffleId = raffleVO;
		this.prizeShipmentMethod = rafflePrizeShipmentDetails.getPrizeShipmentMethod();
		this.prizePosition = rafflePrizeShipmentDetails.getPrizePosition();
		this.prizePosition = rafflePrizeShipmentDetails.getPrizePosition();
		this.prizeIndex = rafflePrizeShipmentDetails.getPrizeIndex();
		this.createdOn = rafflePrizeShipmentDetails.getCreatedOn();
		this.createdBy = rafflePrizeShipmentDetails.getCreatedBy();
		this.updatedOn = rafflePrizeShipmentDetails.getUpdatedOn();
		this.updatedBy = rafflePrizeShipmentDetails.getUpdatedBy();
	}
	
	@JsonIgnore
	public RafflePrizeShipmentDetails getRafflePrizeShipmentDetails() {
		RafflePrizeShipmentDetails rafflePrizeShipmentDetails = new RafflePrizeShipmentDetails();
		Raffle raffle = new Raffle(this.getRaffleId().getRaffleId(),this.getRaffleId().getRaffleName());
		rafflePrizeShipmentDetails.setRaffleId(raffle);
		rafflePrizeShipmentDetails.setPrizeShipmentMethod(prizeShipmentMethod);
		rafflePrizeShipmentDetails.setPrizePosition(prizePosition);
		rafflePrizeShipmentDetails.setPrizePosition(prizePosition);
		rafflePrizeShipmentDetails.setPrizeIndex(prizeIndex);
		rafflePrizeShipmentDetails.setCreatedOn(createdOn);
		rafflePrizeShipmentDetails.setCreatedBy(createdBy);
		rafflePrizeShipmentDetails.setUpdatedOn(updatedOn);
		rafflePrizeShipmentDetails.setUpdatedBy(updatedBy);

		return rafflePrizeShipmentDetails;
	}

	

}
