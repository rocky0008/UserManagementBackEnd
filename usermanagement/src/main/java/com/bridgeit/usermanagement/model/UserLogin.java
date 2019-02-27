package com.bridgeit.usermanagement.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "UserLogin")
public class UserLogin 
{
	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;
	
	
	@Column(name = "lastLoginStamp")
	@DateTimeFormat(style = "dd-MM-yyyy hh:mm:ss")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private Date lastLoginStamp;
	
	
	@Column(name = "userId")
	private int userId;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Date getLastLoginStamp() {
		return lastLoginStamp;
	}


	public void setLastLoginStamp(Date lastLoginStamp) {
		this.lastLoginStamp = lastLoginStamp;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	@Override
	public String toString() {
		return "UserLogin [id=" + id + ", lastLoginStamp=" + lastLoginStamp + ", userId=" + userId + "]";
	}
	
	
	
}
