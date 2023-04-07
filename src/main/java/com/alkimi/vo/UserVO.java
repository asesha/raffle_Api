package com.alkimi.vo;

import java.time.LocalDate;

import com.alkimi.entities.User;
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
public class UserVO {
	
	public UserVO(int userId,String userName) {
		this.userId = userId;
		this.userName = userName;
	}

	private int userId;
	
	private String userName;
	
	private int walletId;
	
	private String email;
	
	private String phone;
	
	private String address;
	
	protected LocalDate createdOn;
	
	protected String createdBy;
	
	protected LocalDate updatedOn;
	
	protected String updatedBy;
	

	public UserVO(User user) {
		this.userId = user.getUserId();
		this.userName = user.getUserName();
		this.walletId = user.getWalletId();
		this.email = user.getEmail();
		this.phone = user.getPhone();
		this.address = user.getAddress();
		this.createdOn = user.getCreatedOn();
		this.createdBy = user.getCreatedBy();
		this.updatedOn = user.getUpdatedOn();
		this.updatedBy = user.getUpdatedBy();
	}
	
	
	@JsonIgnore
	public User getUser() {
		User user = new User();
		user.setUserId(userId);
		user.setUserName(userName);
		user.setWalletId(walletId);
		user.setEmail(email);
		user.setPhone(phone);
		user.setAddress(address);
		
		user.setCreatedOn(createdOn);
		user.setCreatedBy(createdBy);
		user.setUpdatedOn(updatedOn);
		user.setUpdatedBy(updatedBy);
		return user;
	}
	
}
	
	

	
	

