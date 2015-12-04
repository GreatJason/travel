package com.firmname.travel.server.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.firmname.travel.server.model.ErrorCode;
import com.firmname.travel.server.model.User;
import com.firmname.travel.server.service.UserService;
import com.firmname.travel.server.util.Logger;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@ResponseBody
	@RequestMapping(value="/{userId}", method = RequestMethod.GET)
	public User getUserById(@PathVariable String userId){
		return userService.getUserById(userId);
	}
	
	/**
	 * login
	 * @param identifier phone, or email
	 * @param password
	 * @return user info
	 */
	@ResponseBody
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request){
		String ret = ErrorCode.USER_INVALID.errorMessage();
		try {
			String phone = request.getParameter("phone");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			if(phone != null || email != null){
				User user = phone != null ? userService.loginWithPhone(phone, password) :
							userService.loginWithEmail(email, password);
				if(user != null){
					HttpSession session = request.getSession();
					session.setAttribute("id", phone != null ? phone : email);
					session.setAttribute("idType", phone != null ? "Phone" : "Email");
					ret = "name=" + user.getName();
				} 
			}
		} catch(Exception e){
			Logger.error("exception happened while login!", e);
			ret = ErrorCode.EXCEPTION.errorMessage();
		}
		return ret;
	}
	@ResponseBody
	@RequestMapping(value="/register", method = RequestMethod.PUT)
	public String register(@RequestBody User user){
		try{
			String message = userService.addUser(user).errorMessage();
			return message;
		} catch(Exception e){
			Logger.error("exception happened while register user!", e);
			return ErrorCode.EXCEPTION.errorMessage();
		}
	}

}
