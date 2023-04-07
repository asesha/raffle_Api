package com.alkimi.service;

import java.util.List;

import com.alkimi.vo.UserVO;

public interface UserService {
	public List<UserVO> getAllUser();
	public UserVO createUser(UserVO user);
	public List<UserVO> createMultiUser(List<UserVO> userVOs);
	public UserVO getUserById(int userId);
	public UserVO updateUser(int userId,UserVO userDetails);
	public UserVO deleteUser(int userId);
}
