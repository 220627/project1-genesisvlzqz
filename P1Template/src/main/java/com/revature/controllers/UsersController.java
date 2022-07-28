package com.revature.controllers;

import com.revature.daos.UsersDAO;

import io.javalin.http.Handler;

public class UsersController {
	UsersDAO uDAO = new UsersDAO();
	
	public Handler getUsersHandler = (ctx) -> {
		if(AuthController.ses != null) {
			
		}
	};
}
