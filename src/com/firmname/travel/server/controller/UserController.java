package com.firmname.travel.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.firmname.travel.server.model.User;
import com.firmname.travel.server.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@ResponseBody
	@RequestMapping(value="/{userId}", method = RequestMethod.GET)
	public User getUser(@PathVariable String userId){
		return userService.getUser(userId);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public void addUser(User user){
		userService.addUser(user);
	}

}
