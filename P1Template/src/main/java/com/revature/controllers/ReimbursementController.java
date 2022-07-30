package com.revature.controllers;


import java.sql.Timestamp;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.revature.daos.ReimbStatusDAO;
import com.revature.daos.ReimbursementDAO;
import com.revature.daos.UsersDAO;
import com.revature.models.Reimbursement;


import io.javalin.http.Handler;

public class ReimbursementController {
	
	
	
	public Handler getReimbursementsHandler = (ctx) -> {
		
		ReimbursementDAO rDAO = new ReimbursementDAO();
		if(AuthController.ses != null) {
			
			ArrayList<Reimbursement> reimbursements = rDAO.getAllReimbursement();
			Gson gson = new Gson();			
			String jsonReimbs = gson.toJson(reimbursements);
			ctx.status(200).json(jsonReimbs);

		} else {
			ctx.result("LOGIN REQUIRED");
			ctx.status(401);
			
		}
	};	
	
	public Handler insertReimbursementHandler = (ctx) -> {
		if(AuthController.ses!=null) {
			
			ReimbursementDAO rDAO = new ReimbursementDAO();
			String body = ctx.body();
			Gson gson = new Gson();
			Reimbursement reimb = gson.fromJson(body, Reimbursement.class);
			reimb.setReimb_submitted(new Timestamp(System.currentTimeMillis()));
			if(rDAO.insertReimbursement(reimb)) {
				ctx.status(200).result("Reimbursement for $" + reimb.getReimb_amount() +" has been requested.");
			} else {
				ctx.status(406);
			}
			
		}else {
			ctx.result("LOGIN REQUIRED");
			ctx.status(401);
		}
	};	
	
	public Handler updateReimbursementsHandler = (ctx) -> {
		
		if(AuthController.ses != null) {
			
			ReimbursementDAO rDAO = new ReimbursementDAO();
			String body = ctx.body();

			Gson gson = new Gson();
			Reimbursement reimb = gson.fromJson(body, Reimbursement.class);

			if(rDAO.updateReimbursement(reimb)) {
				ctx.status(200).result("Reimbursement ID# " + reimb.getReimb_id() +" has been updated.");
			} else {
				ctx.status(406);
			}
			
		} else {
			ctx.result("LOGIN REQUIRED");
			ctx.status(401);
		}
			
	};

	public Handler getReimbursementsStatusHandler = (ctx) -> {
		
		ReimbursementDAO rDAO = new ReimbursementDAO();
		ReimbStatusDAO sDAO = new ReimbStatusDAO();
		if(AuthController.ses != null) {
			
			String status = ctx.pathParam("status");
			
			ArrayList<Reimbursement> reimbursements = rDAO.getReimbursementByStatus(sDAO.getStatusByName(status));
			Gson gson = new Gson();			
			String jsonReimbs = gson.toJson(reimbursements);
			ctx.status(200).json(jsonReimbs);

		} else {
			ctx.result("LOGIN REQUIRED");
			ctx.status(401);
			
		}
	};

	public Handler getReimbursementsByIDHandler = (ctx) -> {
		
		ReimbursementDAO rDAO = new ReimbursementDAO();
		UsersDAO uDAO = new UsersDAO();
		
		if(AuthController.ses != null) {

			String username = ctx.pathParam("username");
			int id = uDAO.getUserByUsername(username).getErs_users_id();
			ArrayList<Reimbursement> reimbursements = rDAO.getReimbursementByUserID(id);
			
			Gson gson = new Gson();			
			String jsonReimbs = gson.toJson(reimbursements);
			ctx.status(200).json(jsonReimbs);

		} else {
			ctx.result("LOGIN REQUIRED");
			ctx.status(401);
			
		}
		
	};

	public Handler getReimbursementsByIDStatusHandler = (ctx) -> {
		ReimbursementDAO rDAO = new ReimbursementDAO();
		UsersDAO uDAO = new UsersDAO();
		ReimbStatusDAO sDAO = new ReimbStatusDAO();
		
		if(AuthController.ses != null) {

			String username = ctx.pathParam("username");
			String status = ctx.pathParam("status").toUpperCase();
			int id = uDAO.getUserByUsername(username).getErs_users_id();
			ArrayList<Reimbursement> reimbursements = rDAO.getReimbursementByUserStatus(uDAO.getUserByUsername(username).getErs_users_id(), sDAO.getStatusByName(status));
			
			Gson gson = new Gson();			
			String jsonReimbs = gson.toJson(reimbursements);
			ctx.status(200).json(jsonReimbs);

		} else {
			ctx.result("LOGIN REQUIRED");
			ctx.status(401);
			
		}
		
	};		
	
	
}
