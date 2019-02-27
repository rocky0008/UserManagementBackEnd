package com.bridgeit.usermanagement.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.bridgeit.usermanagement.model.User;
import com.bridgeit.usermanagement.model.UserLogin;

public class UserDaoImplementation implements IUserDao {

	@Autowired
	SessionFactory factory;

	@Override
	public boolean addUser(User user) {
		System.out.println(factory);
		if (factory != null) {
			System.out.println(user);
			factory.getCurrentSession().save(user);
			System.out.println("added successfully");
			return true;
		}
		return false;
	}

	@Override
	public List<User> getAllUser() {
		if (factory != null) {
			Query query = factory.getCurrentSession().createQuery("from User");
			@SuppressWarnings("unchecked")
			List<User> userList = query.list();
			return userList;
		}
		return null;
	}

	@Override
	public boolean update(User user) {
		if (factory != null) {
			factory.getCurrentSession().update(user);
			System.out.println("updated successfully");
			System.out.println("Hhihih");
			return true;
		}
		return false;
	}

	@Override
	public User getuser(int id) {
		if (factory != null) {
			User user = (User) factory.getCurrentSession().get(User.class, id);
			return user;
		}
		return null;
	}

	@Override
	public boolean delete(User user) {
		if (factory != null) {
			factory.getCurrentSession().delete(user);
			System.out.println("deleted successfully");
			return true;
		}
		return false;
	}

	@Override
	public User getUserByEmail(String email) {
		if (factory != null) {
			System.out.println(factory);
			System.out.println(email);
			Query query = factory.getCurrentSession().createQuery("from User where email = :email");
			query.setParameter("email", email);
			System.out.println("hihih");
			System.out.println((boolean) query.list().isEmpty());
			if ((boolean) query.list().isEmpty())
				return null;
			else
				return (User) query.list().get(0);
		}
		return null;
	}

	@Override
	public boolean save(UserLogin login) {
		if(factory!=null)
		{
			factory.getCurrentSession().save(login);
			System.out.println("added successfully");
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserLogin> getAllUserLogin() {
		if(factory != null)
		{
			Query query = factory.getCurrentSession().createQuery("from UserLogin");
			List<UserLogin> userList = query.list();
			return userList;
		}
		return null;
	}

}
