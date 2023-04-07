package com.alkimi.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alkimi.vo.UserVO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="USERS")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
	public User(int userId) {
		this.userId = userId;
	}
	
	public User(int userId,String userName) {
		this.userId = userId;
		this.userName = userName;
	}

	@Id
	@Column(name="USER_ID")
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int userId;
	
	@Column(name="USER_NAME")
	private String userName;
	
	@Column(name="WALLET_ID")
	private int walletId;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="PHONE")
	private String phone;
	
	@Column(name="ADDRESS")
	private String address;
	
	@Column(name="CREATED_ON")
	protected LocalDate createdOn;
	
	@Column(name="CREATED_BY")
	protected String createdBy;
	
	@Column(name="UPDATED_ON")
	protected LocalDate updatedOn;
	
	@Column(name="UPDATED_BY")
	protected String updatedBy;
	

	public User(UserVO vo) {
		this.userId = vo.getUserId();
		this.userName = vo.getUserName();
		this.walletId = vo.getWalletId();
		this.email = vo.getEmail();
		this.phone = vo.getPhone();
		this.address = vo.getAddress();
		this.createdOn = vo.getCreatedOn();
		this.createdBy = vo.getCreatedBy();
		this.updatedOn = vo.getUpdatedOn();
		this.updatedBy = vo.getUpdatedBy();
		
	}
	

	public UserVO getUserVO() {
		UserVO vo = new UserVO();
		vo.setUserId(userId);
		vo.setUserName(userName);
		vo.setWalletId(walletId);
		vo.setEmail(email);
		vo.setPhone(phone);
		vo.setAddress(address);
		
		vo.setCreatedOn(createdOn);
		vo.setCreatedBy(createdBy);
		vo.setUpdatedOn(updatedOn);
		vo.setUpdatedBy(updatedBy);
		return vo;
	}
	
	
}
	
	

