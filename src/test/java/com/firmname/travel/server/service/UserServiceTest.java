package com.firmname.travel.server.service;

import static org.junit.Assert.assertTrue;

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
	
	@Test
	public void loginTest(){
		UserService userService = getBean(UserService.class);
		assertTrue(null == userService.loginWithPhoneEmail(UUID.randomUUID().toString(), UUID.randomUUID().toString()));
		assertTrue(null == userService.loginWithPhoneEmail("13812345678", UUID.randomUUID().toString()));
		assertTrue(null == userService.loginWithPhoneEmail("errorAccount@163.com", UUID.randomUUID().toString()));
	}
}
