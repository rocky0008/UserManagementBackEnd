package com.bridgeit.usermanagement.service;

import java.util.List;

import com.bridgeit.usermanagement.dto.CountUser;
import com.bridgeit.usermanagement.dto.UserDto;
import com.bridgeit.usermanagement.model.User;

public interface IUserService {

	boolean createUser(User user);

	List<User> getAllUser();

	boolean updateUser(String token,User user);

	boolean deleteUser(int id);

	String validateUser(UserDto userInfo);

	boolean sendUserNameByEmail(String email);

	User getUser(String token);

	CountUser getCount(String token);

}
