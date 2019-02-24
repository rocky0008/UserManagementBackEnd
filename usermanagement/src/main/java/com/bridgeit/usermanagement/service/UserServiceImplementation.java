package com.bridgeit.usermanagement.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.bridgeit.usermanagement.dao.IUserDao;
import com.bridgeit.usermanagement.dto.UserDto;
import com.bridgeit.usermanagement.model.User;
import com.bridgeit.usermanagement.utility.EncryptAndDecrypt;
import com.bridgeit.usermanagement.utility.SendEmail;
import com.bridgeit.usermanagement.utility.UserToken;

@Transactional
public class UserServiceImplementation implements IUserService {

	@Autowired
	IUserDao userDao;

	@Autowired
	String key;

	@Override
	public boolean createUser(User user) {
		Date date = new Date();
		
		User userInfo = userDao.getUserByEmail(user.getEmail());
		if (userInfo==null) {
			user.setCreatedStamp(date);
			user.setLastUpdateStamp(date);	
			SendEmail.sendEmail(user.getEmail(), user.getUserName(), user.getPassword(),"Account Activation Message");
			String encryptedPassword = EncryptAndDecrypt.encrypt(user.getPassword(), key);
			user.setPassword(encryptedPassword);
			userDao.addUser(user);
			return true;
		}

//		Date dob=user.getDateOfBirth();
//		user.setDateOfBirth(dob);
		return false;

	}

	@Override
	public List<User> getAllUser() {
		List<User> userList = userDao.getAllUser();
		return userList;

	}

	@Override
	public boolean updateUser(int id, User updateUser) {
//		User user=new User();
//		user=userDao.getuser(id);
//		
//		if(id==updateUser.getId())
//		{
//			Date date=new Date();
//			
//			Date loginDate=user.getCreatedStamp();
//			updateUser.setCreatedStamp(loginDate);
//			System.out.println("time");
//			updateUser.setLastUpdateStamp(date);
//			System.out.println(updateUser);
//			userDao.update(updateUser);
//			System.out.println("success");
//			return true;
//		}
//		
		Date date = new Date();
		updateUser.setLastUpdateStamp(date);
		userDao.update(updateUser);
		return false;
	}

	@Override
	public boolean deleteUser(int id) {
		User user = userDao.getuser(id);
		userDao.delete(user);
		return true;
	}

	@Override
	public String validateUser(UserDto userInfo) {
		User user = userDao.getUserByEmail(userInfo.getEmail());
		if (user != null && user.getEmail().equals(userInfo.getEmail()) && user.getPassword().equals(userInfo.getPassword()) && user.getRole().equals("admin")) 
		{
			try {
				String token = UserToken.generateToken(user.getId());
				Date date = new Date();
				user.setLastLoginStamp(date);
				System.out.println("user " +user.getLastLoginStamp());
				userDao.update(user);
				return token;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return null;

	}

	@Override
	public boolean sendUserNameByEmail(String email) 
	{
		System.out.println(email);
		User user=userDao.getUserByEmail(email);
		System.out.println(user);
		if(user!=null && user.getEmail().equals(email))
		{
			String decryptPassword=EncryptAndDecrypt.decrypt(user.getPassword(), key);
			SendEmail.sendEmail(email, user.getUserName(), decryptPassword,"Recovery Password");
			return true;
		}
		return false;
	}

	@Override
	public User getUser(String token) {
		try {
			int id=UserToken.tokenVerify(token);
			User user=userDao.getuser(id);
			return user;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
