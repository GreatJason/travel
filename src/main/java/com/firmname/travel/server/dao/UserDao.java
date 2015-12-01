package com.firmname.travel.server.dao;

import com.firmname.travel.server.model.User;

public interface UserDao {
	void addUser(User user);
	User getUser(String userId);
}
