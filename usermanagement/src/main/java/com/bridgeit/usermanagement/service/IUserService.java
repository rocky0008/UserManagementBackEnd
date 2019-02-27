package com.bridgeit.usermanagement.service;

import java.util.List;

import com.bridgeit.usermanagement.dto.CountUser;
import com.bridgeit.usermanagement.dto.UserDto;
import com.bridgeit.usermanagement.dto.UserListDto;
import com.bridgeit.usermanagement.model.User;
import com.bridgeit.usermanagement.model.UserLogin;

public interface IUserService {

	boolean createUser(User user);

	List<UserListDto> getAllUser();

	boolean updateUser(String token,User user);

	boolean deleteUser(int id);

	String validateUser(UserDto userInfo);

	boolean sendUserNameByEmail(String email);

	User getUser(String token);

	CountUser getCount(String token);

	List<UserLogin> getUserLogin(String token);

}
