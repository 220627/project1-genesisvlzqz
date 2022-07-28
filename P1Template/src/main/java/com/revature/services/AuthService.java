package com.revature.services;



import com.revature.daos.AuthDAO;
import com.revature.models.Users;

public class AuthService {
	
	AuthDAO aDAO = new AuthDAO();
	
	public Users login(String username, String password) {
		
		if(aDAO.login(username, password)!=null) {
			return aDAO.login(username,password);
		}
		
		return null; //upon fail
	}
}
