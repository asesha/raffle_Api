package com.alkimi.vo;

import java.time.LocalDate;

import com.alkimi.entities.Raffle;
import com.alkimi.entities.RafflePrizeDetails;
import com.alkimi.entities.Product;
import com.alkimi.entities.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

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
@JsonInclude(Include.NON_NULL)
public class RafflePrizeDetailsVO {

	private int rafflePrizeId;

	private RaffleRefVO raffleId;
	
	private ProductRefVO productId;
	
	private UserRefVO winnerId;
	
	private int prizePosition; 

	private int prizeIndex;
	
	protected LocalDate createdOn;
	
	protected String createdBy;
	
	protected LocalDate updatedOn;
	
	protected String updatedBy;
	
	private int winnerTicketId;
	
	public RafflePrizeDetailsVO(RafflePrizeDetails rafflePrizeDetails) {
		this.rafflePrizeId = rafflePrizeDetails.getRafflePrizeId();
		RaffleRefVO raffleVO = new RaffleRefVO(rafflePrizeDetails.getRaffleId().getRaffleId());
		this.raffleId = raffleVO;
		ProductRefVO productVO = new ProductRefVO(rafflePrizeDetails.getProductId().getProductId());
		this.productId = productVO;
		this.prizePosition = rafflePrizeDetails.getPrizePosition();
		this.prizeIndex = rafflePrizeDetails.getPrizeIndex();
		this.createdOn = rafflePrizeDetails.getCreatedOn();
		this.createdBy = rafflePrizeDetails.getCreatedBy();
		this.updatedOn = rafflePrizeDetails.getUpdatedOn();
		this.updatedBy = rafflePrizeDetails.getUpdatedBy();
	}
	
	public RafflePrizeDetailsVO(RafflePrizeDetails rafflePrizeDetails, User userDetail) {
		this.rafflePrizeId = rafflePrizeDetails.getRafflePrizeId();
		RaffleRefVO raffleVO = new RaffleRefVO(rafflePrizeDetails.getRaffleId().getRaffleId());
		this.raffleId = raffleVO;
		UserRefVO user = new UserRefVO(userDetail.getUserId(),userDetail.getUserName());
		this.winnerId = user;
		ProductRefVO productVO = new ProductRefVO(rafflePrizeDetails.getProductId().getProductId(),rafflePrizeDetails.getProductId().getProductName());
		this.productId = productVO;
		this.prizePosition = rafflePrizeDetails.getPrizePosition();
		this.prizeIndex = rafflePrizeDetails.getPrizeIndex();
		this.createdOn = rafflePrizeDetails.getCreatedOn();
		this.createdBy = rafflePrizeDetails.getCreatedBy();
		this.updatedOn = rafflePrizeDetails.getUpdatedOn();
		this.updatedBy = rafflePrizeDetails.getUpdatedBy();

	}
	
	
	@JsonIgnore
	public RafflePrizeDetails getRafflePrizeDetails() {
		RafflePrizeDetails rafflePrizeDetails = new RafflePrizeDetails();
		Raffle raffle = new Raffle(this.getRaffleId().getRaffleId(),this.getRaffleId().getRaffleName());
		rafflePrizeDetails.setRaffleId(raffle);
		Product product = new Product(this.getProductId().getProductId());
		rafflePrizeDetails.setProductId(product);
		rafflePrizeDetails.setPrizePosition(prizePosition);
		rafflePrizeDetails.setPrizeIndex(prizeIndex);
		rafflePrizeDetails.setCreatedOn(createdOn);
		rafflePrizeDetails.setCreatedBy(createdBy);
		rafflePrizeDetails.setUpdatedOn(updatedOn);
		rafflePrizeDetails.setUpdatedBy(updatedBy);

		return rafflePrizeDetails;
	}
	


	
	
}
	
	

