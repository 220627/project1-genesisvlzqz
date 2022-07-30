package com.revature.controllers;

import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.revature.models.LoginDTO;
import com.revature.models.Users;
import com.revature.services.AuthService;

import io.javalin.http.Handler;

public class AuthController {

	AuthService as = new AuthService();
	
	public static HttpSession ses;
	
	public Handler loginHandler = (ctx) -> {
		
		String body = ctx.body();
		Gson gson = new Gson();
		
		LoginDTO lDTO = gson.fromJson(body, LoginDTO.class);
		Users user = as.login(lDTO.getUsername(), lDTO.getPassword());
		if(user!=null) {
			ses = ctx.req.getSession();
			String userJson = gson.toJson(user);
			ctx.result("Welcome \n"
					+ userJson);
			ctx.status(202);
		} else {
			ctx.result("Invalid Credentials");
			ctx.status(401);
		}
	};
	
}
