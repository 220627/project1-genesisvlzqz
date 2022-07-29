package com.revature.controllers;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.revature.daos.ReimbursementDAO;
import com.revature.models.Reimbursement;
import com.revature.models.Responses;

import io.javalin.http.Handler;

public class ReimbursementController {
	
	
	
	public static Handler getReimbursementsHandler = (ctx) -> {
		
		ReimbursementDAO rDAO = new ReimbursementDAO();
		if(AuthController.ses != null) {
			
			ArrayList<Reimbursement> reimbursements = rDAO.getAllReimbursement();

			Gson gson = new Gson();			
			Responses r = new Responses("success",reimbursements);
			ctx.status(200).json(r.getStatusObject());
			
			//gson.toJson gives ilegal access error on timestamp attribute
			//String JSONreimbursements = gson.toJson(reimbursements);
			
//			ctx.result(gson.toJson(r));
//			ctx.contentType("JSON");
//			ctx.status(200);

		} else {
			ctx.result("LOGIN REQUIRED");
			ctx.status(401);
			
		}
	};
	
	
}
