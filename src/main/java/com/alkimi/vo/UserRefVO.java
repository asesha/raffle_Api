package com.alkimi.vo;

import com.alkimi.entities.User;
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
public class UserRefVO {

	private int userId;
	private String userName;

	public UserRefVO(User user) {
		this.userId = user.getUserId();
	}
	
	public UserRefVO(int userId) {
		this.userId = userId;
	}

}
