package com.firmname.travel.dao;

import com.firmname.travel.model.ErrorCode;
import com.firmname.travel.model.User;

public interface UserDao {
	ErrorCode addUser(User user);
	User getUserById(String userId);
	User getUserByPhone(String phone, String password);
	User getUserByEmail(String email, String password);
}
