package com.alkimi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkimi.dao.UserRaffleRepository;
import com.alkimi.entities.UserRaffle;
import com.alkimi.vo.UserRaffleVO;

@Service
public class UserRaffleServiceImpl implements UserRaffleService{

	@Autowired
	UserRaffleRepository repository;

	@Override
	public UserRaffleVO createUserRaffle(UserRaffleVO vo) {
		UserRaffle userRaffle = repository.save(vo.getUserRaffle());
		return userRaffle.getUserRaffleVO();
	}

	@Override
	public List<UserRaffleVO> getUserListByRaffleId(int raffleId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserRaffleVO> getRaffleListUserId(int userId) {
		// TODO Auto-generated method stub
		return null;
	}
	

	
	
}
