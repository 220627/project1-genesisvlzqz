package com.revature.daos;

import java.util.ArrayList;

import com.revature.models.Users;

public interface UsersDAOInterface {
	
	boolean insertNewUser(Users user);
	Users getUserById(int user_id);
	ArrayList<Users> getUsers();
	Users getUserByUsername(String username);
}
