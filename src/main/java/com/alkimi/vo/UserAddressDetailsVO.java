package com.alkimi.vo;

import java.time.LocalDate;

import javax.persistence.Column;

import com.alkimi.entities.User;
import com.alkimi.entities.UserAddressDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class UserAddressDetailsVO {

	private int addressDetailsId;

	private UserRefVO userId;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String country;
	
	private String addressOne;
	
	private String addressTwo;
	
	private String city;

	private String state;

	private String pincode;
	
	private String phone;
	
	private String addressCategory;
	
	private String status;

	private LocalDate createdOn;

	private String createdBy;

	private LocalDate updatedOn;

	private String updatedBy;

	public UserAddressDetailsVO(UserAddressDetails userAddressDetails) {
		UserRefVO user = new UserRefVO(userAddressDetails.getUserId().getUserId());
		this.setUserId(user);
		this.firstName = userAddressDetails.getFirstName();
		this.lastName = userAddressDetails.getLastName();
		this.email = userAddressDetails.getEmail();
		this.country = userAddressDetails.getCountry();
		this.addressOne = userAddressDetails.getAddressOne();
		this.addressTwo = userAddressDetails.getAddressTwo();
		this.city = userAddressDetails.getCity();
		this.state = userAddressDetails.getState();
		this.pincode = userAddressDetails.getPincode();
		this.phone = userAddressDetails.getPhone();
		this.addressCategory = userAddressDetails.getAddressCategory();
		this.status = userAddressDetails.getStatus();
		this.createdOn = userAddressDetails.getCreatedOn();
		this.createdBy = userAddressDetails.getCreatedBy();
		this.updatedOn = userAddressDetails.getUpdatedOn();
		this.updatedBy = userAddressDetails.getUpdatedBy();
	}

	@JsonIgnore
	public UserAddressDetails getUserAddressDetails() {
		UserAddressDetails details = new UserAddressDetails();
		
		User user = new User(this.getUserId().getUserId());
		details.setUserId(user);
		details.setAddressDetailsId(addressDetailsId);
		details.setFirstName(firstName);
		details.setLastName(lastName);
		details.setEmail(email);
		details.setCountry(country);
		details.setAddressOne(addressOne);
		details.setAddressTwo(addressTwo);
		details.setCity(city);
		details.setState(state);
		details.setPincode(pincode);
		details.setPhone(phone);
		details.setAddressCategory(addressCategory);
		details.setStatus(status);
		details.setCreatedOn(createdOn);
		details.setCreatedBy(createdBy);
		details.setUpdatedOn(updatedOn);
		details.setUpdatedBy(updatedBy);

		return details;
	}

}
