package com.bridgeit.usermanagement.service;

import java.util.List;

import com.bridgeit.usermanagement.model.User;

public interface IUserService {

	boolean createUser(User user);

	List<User> getAllUser();

	boolean updateUser(int id,User user);

	boolean deleteUser(int id);

}