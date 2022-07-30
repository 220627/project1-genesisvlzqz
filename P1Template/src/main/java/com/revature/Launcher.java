package com.revature;

import java.sql.Timestamp;

import com.revature.controllers.AuthController;
import com.revature.controllers.ReimbursementController;
import com.revature.controllers.UsersController;
import com.revature.daos.ReimbStatusDAO;
import com.revature.daos.ReimbTypeDAO;
import com.revature.daos.ReimbursementDAO;
import com.revature.daos.UserRolesDAO;
import com.revature.daos.UsersDAO;
import com.revature.models.ReimbStatus;
import com.revature.models.ReimbType;
import com.revature.models.Reimbursement;
import com.revature.models.UserRoles;
import com.revature.models.Users;

import io.javalin.Javalin;

public class Launcher {

	public static void main(String[] args) {
		
		//Welcome to P1! 
//               __
//          (___()'`;
//          /,    /`
//          \\"--\\
//
		System.out.println("------ReimbStatus Test");
		ReimbStatus stat1 = new ReimbStatus(1,"Pending");
		System.out.println(stat1.toString());
		
		System.out.println("\n------ReimbType Test");
		ReimbType type1 = new ReimbType(1,"TRAVEL");
		System.out.println(type1.toString());
		
		System.out.println("\n------UserRoles Test");
		UserRoles role1 = new UserRoles(1,"Employee");
		System.out.println(role1.toString());	
		
		System.out.println("\n------Users Test");
		Users user1 = new Users("emptest","password","employee","test","emptest@rev.net",1);
		user1.setUser_role(role1);
		System.out.println(user1.toString());
		
		System.out.println("\n------Reimbursement Test");
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		Reimbursement reimb1 = new Reimbursement(2562.23f,ts,null,"Relocation to Cali",null,1,1,1);
		reimb1.setAuthor(user1);
		reimb1.setType(type1);
		reimb1.setStatus(stat1);
		System.out.println(reimb1.toString());
		
		System.out.println("\n------UserRolesDAO test");
		UserRolesDAO uroleDAO = new UserRolesDAO();
		System.out.println(uroleDAO.getUserRoleById(1).toString());
		
		System.out.println("\n------UsersDAO test");
		UsersDAO uDAO = new UsersDAO();
		System.out.println(uDAO.getUserById(3).toString()); //getUser
		//System.out.println(uDAO.insertNewUser(user1)); //insertUser
		
		System.out.println("\n------ReimTypeDAO test");
		ReimbTypeDAO tDAO = new ReimbTypeDAO();
		System.out.println(tDAO.getTypeById(2));
		
		System.out.println("\n------ReimbStatusDAO test");
		ReimbStatusDAO sDAO = new ReimbStatusDAO();
		System.out.println(sDAO.getStatusById(2));
		
		System.out.println("\n------ReimbursementDAO test");
		ReimbursementDAO rDAO = new ReimbursementDAO();
		System.out.println(rDAO.getAllReimbursement());
		System.out.println(rDAO.getReimbursementByStatus(stat1));
		System.out.println(rDAO.getReimbursementByUserID(3));
		reimb1.setReimb_resolver(4);
		reimb1.setReimb_status_id(3);
		System.out.println(rDAO.updateReimbursement(reimb1));
		reimb1.setReimb_author(3);
		reimb1.setAuthor(uDAO.getUserById(3));
		//rDAO.insertReimbursement(reimb1);
		System.out.println(rDAO.getReimbursementById(1));
		
		//------------------
		Javalin app = Javalin.create(
				config -> {
				config.enableCorsForAllOrigins(); 
			}
			
			).start(3000); 
	
		AuthController ac = new AuthController();
		UsersController uc = new UsersController();
		ReimbursementController rc = new ReimbursementController();
		
		
		app.post("/login",ac.loginHandler);
		app.get("/users",uc.getUsersHandler);
		app.get("/reimbursements", rc.getReimbursementsHandler);
		app.post("/reimbursement", rc.insertReimbursementHandler);
		app.put("/reimbursements/update", rc.updateReimbursementsHandler);
		app.get("/reimbursements/status/{status}", rc.getReimbursementsStatusHandler);
		app.get("/reimbursements/{username}/{status}", rc.getReimbursementsByIDStatusHandler);
		app.get("/reimbursements/{username}", rc.getReimbursementsByIDHandler);

	}
	
}
