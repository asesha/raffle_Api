package com.alkimi.service;

import java.util.List;

import com.alkimi.vo.UserRaffleVO;

public interface UserRaffleService {
	public UserRaffleVO createUserRaffle(UserRaffleVO userRaffle);
	public List<UserRaffleVO> getUserListByRaffleId(int raffleId);
	public List<UserRaffleVO> getRaffleListUserId(int userId);

}
