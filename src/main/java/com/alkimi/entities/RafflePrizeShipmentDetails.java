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

import com.alkimi.vo.RafflePrizeShipmentDetailsVO;
import com.alkimi.vo.RaffleRefVO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "RAFFLE_PRIZE_SHIPMENT_DETAILS")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RafflePrizeShipmentDetails {

	@Id
	@Column(name = "RAFFLE_PRIZE_SHIPMENT_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int rafflePrizeShipmentId;

	@ManyToOne
	@JoinColumn(name = "RAFFLE_ID_FK", referencedColumnName = "RAFFLE_ID", nullable = false)
	private Raffle raffleId;

	@Column(name = "PRIZE_POSITION")
	private int prizePosition;
	
	@Column(name = "PRIZE_INDEX")
	private int prizeIndex;
	
	
	
	@Column(name = "PRIZE_SHIPMENT_METHOD")
	private String prizeShipmentMethod;
	
	@Column(name = "THIRD_PARTY_ID_FK")
	private int thirdPartyId;
	
	@Column(name = "CREATED_ON")
	private LocalDate createdOn;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "UPDATED_ON")
	private LocalDate updatedOn;

	@Column(name = "UPDATED_BY")
	private String updatedBy;

	public RafflePrizeShipmentDetails(RafflePrizeShipmentDetailsVO vo) {
		Raffle raffle = new Raffle(vo.getRaffleId().getRaffleId());
		this.raffleId = raffle;
		this.rafflePrizeShipmentId = vo.getRafflePrizeShipmentId();
		this.prizeShipmentMethod = vo.getPrizeShipmentMethod();
		this.prizePosition = vo.getPrizePosition();
		this.prizeIndex = vo.getPrizeIndex();
		this.createdOn = vo.getCreatedOn();
		this.createdBy = vo.getCreatedBy();
		this.updatedOn = vo.getUpdatedOn();
		this.updatedBy = vo.getUpdatedBy();
	}

	public RafflePrizeShipmentDetailsVO getRafflePrizeShipmentDetailsVO() {
		RafflePrizeShipmentDetailsVO vo = new RafflePrizeShipmentDetailsVO();
		vo.setRafflePrizeShipmentId(rafflePrizeShipmentId);

		RaffleRefVO raffleVO = new RaffleRefVO(this.getRaffleId().getRaffleId(),
				this.getRaffleId().getRaffleName());
		vo.setRaffleId(raffleVO);
		vo.setPrizeShipmentMethod(prizeShipmentMethod);
		vo.setPrizePosition(prizePosition);
		vo.setPrizeIndex(prizeIndex);

		vo.setCreatedOn(createdOn);
		vo.setCreatedBy(createdBy);
		vo.setUpdatedOn(updatedOn);
		vo.setUpdatedBy(updatedBy);

		return vo;
	}

}
