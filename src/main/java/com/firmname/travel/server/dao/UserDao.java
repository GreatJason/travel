package com.firmname.travel.server.dao;

import com.firmname.travel.server.model.ErrorCode;
import com.firmname.travel.server.model.User;

public interface UserDao {
	ErrorCode addUser(User user);
	User getUserById(String userId);
	User getUserByPhone(String phone, String password);
	User getUserByEmail(String email, String password);
}
