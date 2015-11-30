package com.firmname.travel.server.service;

import org.junit.Test;

import com.firmname.travel.server.common.BaseTest;
import com.firmname.travel.server.model.User;

public class UserServiceTest extends BaseTest{
	@Test
	public void addUserTest(){
		User user = new User();
		user.setId("user01");
		user.setName("Jason");
		user.setEmail("registerpro@163.com");
		UserService userService = new UserService();
		userService.addUser(user);
	}
}
