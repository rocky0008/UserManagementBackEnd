package com.bridgeit.usermanagement.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="User")
public class User implements Serializable
{
	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	
	@Column(name="firstName")
	private String firstName;
	
	@Column(name="lastName")
	private String lastName;
	
	@Column(name="middleName")
	private String middleName;
	
	@Column(name="gender")
	private String gender;
	
	@Column(name="dateOfBirth")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(style ="dd-MM-yyyy")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	private Date dateOfBirth;
	
	@Column(name="country")
	private String country;
	
	@Column(name="phoneNumber")
	private String phoneNumber;
	
	@Column(name="email")
	private String email;
	
	@Column(name="address")
	private String address;
	
	@Column(name="userName")
	private String userName;
	
	@Column(name="password")
	private String password;
	
	@Column(name="status")
	private boolean status;
	
	@Column(name="createdStamp")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(style ="dd-MM-yyyy")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	private Date createdStamp;
	
	@Column(name="lastLoginStamp")
	private Date lastLoginStamp;
	
	@Column(name="lastUpdateStamp")
	private Date lastUpdateStamp;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Date getCreatedStamp() {
		return createdStamp;
	}
	public void setCreatedStamp(Date createdStamp) {
		this.createdStamp = createdStamp;
	}
	public Date getLastLoginStamp() {
		return lastLoginStamp;
	}
	public void setLastLoginStamp(Date lastLoginStamp) {
		this.lastLoginStamp = lastLoginStamp;
	}
	public Date getLastUpdateStamp() {
		return lastUpdateStamp;
	}
	public void setLastUpdateStamp(Date lastUpdateStamp) {
		this.lastUpdateStamp = lastUpdateStamp;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", middleName=" + middleName
				+ ", gender=" + gender + ", dateOfBirth=" + dateOfBirth + ", country=" + country + ", phoneNumber="
				+ phoneNumber + ", email=" + email + ", address=" + address + ", userName=" + userName + ", password="
				+ password + ", status=" + status + ", createdStamp=" + createdStamp + ", lastLoginStamp="
				+ lastLoginStamp + ", lastUpdateStamp=" + lastUpdateStamp + "]";
	}
	
	
}
