package com.firmname.travel.server.service;

import java.util.UUID;

import org.junit.Test;

import com.firmname.travel.server.common.BaseTest;
import com.firmname.travel.server.model.User;

public class UserServiceTest extends BaseTest{
	
	@Test
	public void addUserTest(){
		User user = new User();
		user.setId(UUID.randomUUID().toString());
		user.setName("Jason");
		user.setEmail("registerpro@163.com");
		UserService userService = getBean(UserService.class);
		userService.addUser(user);
	}
}
