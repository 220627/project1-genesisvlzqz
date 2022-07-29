package com.revature.controllers;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.revature.daos.UsersDAO;
import com.revature.models.Users;

import io.javalin.http.Handler;

public class UsersController {
	
	UsersDAO uDAO = new UsersDAO();
	
	public Handler getUsersHandler = (ctx) -> {
		
		if(AuthController.ses != null) {
			ArrayList<Users> users = uDAO.getUsers();
			Gson gson = new Gson();
			String JSONusers = gson.toJson(users);
			
			ctx.result(JSONusers);
			ctx.contentType("JSON");
			ctx.status(200);
		} else {
			ctx.result("LOGIN REQUIRED");
			ctx.status(401);
		}
	};
	
	
}
