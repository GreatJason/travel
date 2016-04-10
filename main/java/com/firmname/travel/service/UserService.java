package com.firmname.travel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.firmname.travel.dao.UserDao;
import com.firmname.travel.model.ErrorCode;
import com.firmname.travel.model.User;
import com.firmname.travel.util.Logger;
import com.firmname.travel.util.Utils;
import com.google.common.base.Strings;

@Service
public final class UserService {
	
	@Autowired
	private UserDao userDao;
	
	public ErrorCode addUser(User user){
		if(user != null){
			user.populateId();
			Logger.debug("user trying to register {}", user.getId());
			ErrorCode ret = userDao.addUser(user);
			Logger.debug("user register status: {}", ret.errorMessage());
			return ret;
		} else{
			return ErrorCode.USER_INVALID;
		}
	}
	
	public User getUserById(String userId){
		return userDao.getUserById(userId);
	}

	public User loginWithPhone(String phone, String password) {
		if(!Utils.isValidPhone(phone)  || Strings.isNullOrEmpty(password)){
			Logger.debug("invalid user phone or password");
			return null;
		}
		User user = userDao.getUserByPhone(phone, password);
		Logger.debug("user login in with phone {} {}", phone, user != null ? "success" : "failed!");
		return user;
	}
	
	public User loginWithEmail(String email, String password) {
		if(!Utils.isValidEmail(email) || Strings.isNullOrEmpty(password)){
			Logger.debug("invalid user email or password");
			return null;
		}
		User user = userDao.getUserByEmail(email, password);
		Logger.debug("user login in with phone {} {}", email, user != null ? "success" : "failed!");
		return user;
	}
	
	/**
	 * 
	 * @param identifier
	 */
	public void logout(String identifier){
		throw new RuntimeException("not implemented yet!");
	}

	public void setUserDao(UserDao userDao){
		this.userDao = userDao;
	}
}
