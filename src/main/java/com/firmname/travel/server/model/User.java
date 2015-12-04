package com.firmname.travel.server.model;

import java.util.Date;
import java.util.UUID;

public final class User {
	private String id;
	private String nickName;
	private String name;
	private String sex;
	private String phone;
	private String email;
	private String password;
	private Date birthDate;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * populate UUID to user id
	 */
	public void populateId(){
		this.id = UUID.randomUUID().toString();
	}
	
	@Override
	public String toString(){
		//TODO: to finish
		return super.toString();
	}
}
