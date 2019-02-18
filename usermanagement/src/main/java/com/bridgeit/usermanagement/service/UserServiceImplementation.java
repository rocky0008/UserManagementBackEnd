package com.bridgeit.usermanagement.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.bridgeit.usermanagement.dao.IUserDao;
import com.bridgeit.usermanagement.model.User;
import com.bridgeit.usermanagement.utility.EncryptAndDecrypt;
import com.bridgeit.usermanagement.utility.SendEmail;
import com.bridgeit.usermanagement.utility.UserToken;

@Transactional
public class UserServiceImplementation implements IUserService
{

	@Autowired
	IUserDao userDao;
	
	
	@Autowired
	String key;
	
	@Override
	public boolean createUser(User user) 
	{
		Date date=new Date();
		try {
			String token=UserToken.generateToken(user.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		user.setCreatedStamp(date);
		SendEmail.sendEmail(user.getEmail(),user.getUserName(), user.getPassword());
		String encryptedPassword=EncryptAndDecrypt.encrypt(user.getPassword(), key);
		user.setPassword(encryptedPassword);
//		Date dob=user.getDateOfBirth();
//		user.setDateOfBirth(dob);
		userDao.addUser(user);
	
		
		return true;
	}

	@Override
	public List<User> getAllUser() 
	{
		List<User> userList=userDao.getAllUser();
		return userList;
		
	}

	@Override
	public boolean updateUser(int id,User updateUser) 
	{
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
		Date date=new Date();
		updateUser.setLastUpdateStamp(date);
		userDao.update(updateUser);
		return false;
	}

	@Override
	public boolean deleteUser(int id) {
		User user=userDao.getuser(id);
		userDao.delete(user);
		return true; 
	}

}
