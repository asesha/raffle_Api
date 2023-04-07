package com.alkimi.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkimi.dao.UserRepository;
import com.alkimi.entities.User;
import com.alkimi.vo.RafflePrizeDetailsVO;
import com.alkimi.vo.UserVO;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository repository;
	
	@Override
	public List<UserVO> getAllUser() {
		List<User> users = repository.findAll();
    	List<UserVO> userVOList = users.stream().map(UserVO::new).collect(Collectors.toList());
    	return userVOList;
	}

	@Override
	public UserVO createUser(UserVO vo) {
		User user = repository.save(vo.getUser());
		return user.getUserVO();
	}
	
	@Override
	public List<UserVO> createMultiUser(List<UserVO> userVOs){
		List<User> userList = userVOs.stream().map(User::new).collect(Collectors.toList());
		userList = repository.saveAll(userList);
		userVOs = userList.stream().map(UserVO::new).collect(Collectors.toList());
		return userVOs;
	}

	@Override
	public UserVO getUserById(int userId) {
		Optional<User> user = repository.findById(userId);
		return user.get().getUserVO();
	}

	@Override
	public UserVO updateUser(int userId,UserVO userDetails) {
		Optional<User> user = repository.findById(userId);
		User modifiedUser = null;
		if(user.get() != null) {
			modifiedUser = user.get();
			modifiedUser.setUpdatedOn(LocalDate.now());
			updateUser(modifiedUser,userDetails);
	    	modifiedUser = repository.save(modifiedUser);
	    }
		return modifiedUser.getUserVO();
	}

	
	@Override
	public UserVO deleteUser(int userId) {
		Optional<User> user = repository.findById(userId);
		if(user.get() != null) {
			repository.delete(user.get());
	    }
		return user.get().getUserVO();
	}
	
	private void updateUser(User modifiedUser,UserVO userDetails) {
		if( userDetails.getWalletId() != 0) {
			modifiedUser.setWalletId(userDetails.getWalletId());
		}
		
		if( userDetails.getEmail()!=null && userDetails.getEmail().trim().length()!=0) {
			modifiedUser.setEmail(userDetails.getEmail());
		}
		
		if( userDetails.getPhone()!=null && userDetails.getPhone().trim().length()!=0) {
			modifiedUser.setPhone(userDetails.getPhone());
		}
		
		if( userDetails.getAddress()!=null && userDetails.getAddress().trim().length()!=0) {
			modifiedUser.setAddress(userDetails.getAddress());
		}
		
	}
	
}
