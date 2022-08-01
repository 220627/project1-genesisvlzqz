package com.revature.controllers;

import java.util.logging.LogManager;

import javax.servlet.http.HttpSession;

import org.eclipse.jetty.server.Authentication.User;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.revature.models.LoginDTO;
import com.revature.models.Users;
import com.revature.services.AuthService;

import io.javalin.http.Handler;

public class AuthController {
	
	static java.util.logging.Logger log = LogManager.getLogManager().getLogger("");
	
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
			log.info("User Logged in");
			//writting session value
			ctx.sessionAttribute("permLevel", user.getUser_role_id());
			ctx.result("Welcome "+ lDTO.getUsername());
			ctx.status(202);
		} else {
			log.info("invalid login");
			ctx.result("Invalid Credentials");
			ctx.status(401);
		}
	};
	
}
