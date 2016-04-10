package com.firmname.travel.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.UUID;

import org.junit.Test;

import com.firmname.travel.common.BaseTest;
import com.firmname.travel.model.User;

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
		assertNull(userService.loginWithPhone(UUID.randomUUID().toString(), UUID.randomUUID().toString()));
		assertNull(userService.loginWithPhone("13812345678", UUID.randomUUID().toString()));
		assertNull(userService.loginWithEmail("errorAccount@163.com", UUID.randomUUID().toString()));
		assertNotNull(userService.loginWithPhone("13512345678", "dasiyebushuo"));
		assertNotNull(userService.loginWithEmail("registerpro@163.com", "dasiyebushuo"));
	}
}
