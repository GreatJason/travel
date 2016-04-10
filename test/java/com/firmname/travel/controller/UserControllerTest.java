package com.firmname.travel.controller;

import java.util.UUID;

import org.junit.Test;

import com.firmname.travel.common.HttpTestBase;
import com.firmname.travel.model.User;

public class UserControllerTest extends HttpTestBase{
	@Test
	public void loginGetTest(){
		System.out.println(this.getForObject("user/login?phone=13812345678", String.class));
	}
	
	@Test
	public void loginPostTest(){
		System.out.println(this.postForEntity("user/login?phone=13512345678&password=dasiyebushuo", null, String.class));
	}
	
	@Test
	public void registerPutTest(){
		User user = new User();
		user.setId(UUID.randomUUID().toString());
		user.setName("ÕÅÈý");
		user.setPhone("13687654321");
		user.setPassword(UUID.randomUUID().toString());
		this.put("user/register", jsonHeader(user));
	}
}
