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
import com.alkimi.vo.UserAddressDetailsVO;
import com.alkimi.vo.UserRefVO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "USER_ADDRESS_DETAILS")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserAddressDetails {

	@Id
	@Column(name="ADDRESS_DETAILS_ID")
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int addressDetailsId;

	@ManyToOne
	@JoinColumn(name = "USER_ID_FK", referencedColumnName="USER_ID", nullable = false)
	private User userId;
	
	@Column(name="FIRST_NAME")
	private String firstName;
	
	@Column(name="LAST_NAME")
	private String lastName;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="COUNTRY")
	private String country;
	
	@Column(name="STREET_ADDRESS1")
	private String addressOne;
	
	@Column(name="STREET_ADDRESS2")
	private String addressTwo;
	
	@Column(name="CITY")
	private String city;

	@Column(name="STATE")
	private String state;

	@Column(name="PINCODE")
	private String pincode;
	
	@Column(name="PHONE")
	private String phone;
	

	@Column(name="ADDRESS_CATEGORY")
	private String addressCategory;
	
	@Column(name="STATUS")
	private String status;

	@Column(name="CREATED_ON")
	private LocalDate createdOn;

	@Column(name="CREATED_BY")
	private String createdBy;

	@Column(name="UPDATED_ON")
	private LocalDate updatedOn;

	@Column(name="UPDATED_BY")
	private String updatedBy;

	public UserAddressDetails(UserAddressDetailsVO vo) {

		this.firstName = vo.getFirstName();
		this.lastName = vo.getLastName();
		this.email = vo.getEmail();
		this.country = vo.getCountry();
		this.addressOne = vo.getAddressOne();
		this.addressTwo = vo.getAddressTwo();
		this.city = vo.getCity();
		this.state = vo.getState();
		this.pincode = vo.getPincode();
		this.phone = vo.getPhone();
		this.addressCategory = vo.getAddressCategory();
		this.status = vo.getStatus();
		this.createdOn = vo.getCreatedOn();
		this.createdBy = vo.getCreatedBy();
		this.updatedOn = vo.getUpdatedOn();
		this.updatedBy = vo.getUpdatedBy();
	}

	public UserAddressDetailsVO getUserAddressDetailsVO() {
		UserAddressDetailsVO vo = new UserAddressDetailsVO();
		
		UserRefVO user = new UserRefVO(userId.getUserId(), userId.getUserName());
		vo.setUserId(user);
		vo.setAddressDetailsId(addressDetailsId);
		vo.setFirstName(firstName);
		vo.setLastName(lastName);
		vo.setEmail(email);
		vo.setCountry(country);
		vo.setAddressOne(addressOne);
		vo.setAddressTwo(addressTwo);
		vo.setCity(city);
		vo.setState(state);
		vo.setPincode(pincode);
		vo.setPhone(phone);
		vo.setAddressCategory(addressCategory);
		vo.setStatus(status);
		vo.setCreatedOn(createdOn);
		vo.setCreatedBy(createdBy);
		vo.setUpdatedOn(updatedOn);
		vo.setUpdatedBy(updatedBy);

		return vo;
	}

}
