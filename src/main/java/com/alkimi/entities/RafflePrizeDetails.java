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

import com.alkimi.vo.RafflePrizeDetailsVO;
import com.alkimi.vo.RaffleRefVO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "RAFFLE_PRIZE_DETAILS")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RafflePrizeDetails {

	@Id
	@Column(name = "RAFFLE_PRIZE_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int rafflePrizeId;

	@ManyToOne
	@JoinColumn(name = "RAFFLE_ID_FK", referencedColumnName = "RAFFLE_ID", nullable = false)
	private Raffle raffleId;

	@ManyToOne
	@JoinColumn(name = "PRODUCT_ID_FK", referencedColumnName = "PRODUCT_ID", nullable = false)
	private Product productId;

	@ManyToOne
	@JoinColumn(name = "WINNER_ID_FK", referencedColumnName = "USER_ID", nullable = true)
	private User winnerId;

	@Column(name = "PRIZE_POSITION")
	private int prizePosition;
	
	@Column(name = "PRIZE_INDEX")
	private int prizeIndex;
	
	@Column(name="TICKET_ID")
	private Integer ticketId;

	@Column(name = "CREATED_ON")
	protected LocalDate createdOn;

	@Column(name = "CREATED_BY")
	protected String createdBy;

	@Column(name = "UPDATED_ON")
	protected LocalDate updatedOn;

	@Column(name = "UPDATED_BY")
	protected String updatedBy;

	public RafflePrizeDetails(RafflePrizeDetailsVO vo) {
		Raffle raffle = new Raffle(vo.getRaffleId().getRaffleId());
		this.raffleId = raffle;
		Product product = new Product(vo.getProductId().getProductId());
		this.productId = product;
		this.prizePosition = vo.getPrizePosition();
		this.prizeIndex = vo.getPrizeIndex();
		this.createdOn = vo.getCreatedOn();
		this.createdBy = vo.getCreatedBy();
		this.updatedOn = vo.getUpdatedOn();
		this.updatedBy = vo.getUpdatedBy();
	}

	public RafflePrizeDetailsVO getRafflePrizeDetailsVO() {
		RafflePrizeDetailsVO vo = new RafflePrizeDetailsVO();
		vo.setRafflePrizeId(rafflePrizeId);
		RaffleRefVO raffleVO = new RaffleRefVO(this.getRaffleId().getRaffleId(),
				this.getRaffleId().getRaffleName());
		vo.setRaffleId(raffleVO);
		
		vo.setPrizePosition(prizePosition);
		vo.setPrizeIndex(prizeIndex);

		vo.setCreatedOn(createdOn);
		vo.setCreatedBy(createdBy);
		vo.setUpdatedOn(updatedOn);
		vo.setUpdatedBy(updatedBy);

		return vo;
	}

}
