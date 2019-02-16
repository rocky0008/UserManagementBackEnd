package com.bridgeit.usermanagement.dao;

import java.util.List;

import com.bridgeit.usermanagement.model.User;

public interface IUserDao {

	boolean addUser(User user);

	List<User> getAllUser();

	boolean update(User user);

	User getuser(int id);

	boolean delete(User user);

}
