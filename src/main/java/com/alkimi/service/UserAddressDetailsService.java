package com.alkimi.service;

import com.alkimi.vo.UserAddressDetailsVO;

public interface UserAddressDetailsService {
	public UserAddressDetailsVO createUserAddressDetails(UserAddressDetailsVO vo);
	public UserAddressDetailsVO getUserAddressDetailsById(int addressDetailsId);
	public UserAddressDetailsVO updateUserAddressDetails(int addressDetailsId,UserAddressDetailsVO vo);
	public UserAddressDetailsVO deleteUserAddressDetails(int addressDetailsId);
}
