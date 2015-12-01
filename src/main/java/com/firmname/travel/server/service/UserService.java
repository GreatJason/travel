package com.firmname.travel.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.firmname.travel.server.dao.UserDao;
import com.firmname.travel.server.model.User;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	public void addUser(User user){
		userDao.addUser(user);
	}
	
	public User getUser(String userId){
		return userDao.getUser(userId);
	}
	
	public void setUserDao(UserDao userDao){
		this.userDao = userDao;
	}
}
