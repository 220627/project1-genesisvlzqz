package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.revature.models.Users;
import com.revature.utils.ConnectionUtil;

public class UsersDAO implements UsersDAOInterface{

	@Override
	public boolean insertNewUser(Users user) {
		// TODO Auto-generated method stub
		try (Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "insert into ers_users ("
					+ "ers_username,"
					+ "ers_password,"
					+ "user_first_name,"
					+ "user_last_name,"
					+ "user_email,"
					+ "user_role_id)"
					+ "values (?,?,?,?,?,?);";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user.getErs_username());
			ps.setString(2, user.getErs_password());
			ps.setString(3, user.getUser_first_name());
			ps.setString(4, user.getUser_last_name());
			ps.setString(5, user.getUser_email());
			ps.setInt(6, user.getUser_role_id());
			
			ps.executeUpdate();
			System.out.println("User "+ user.getErs_username() +" added succesfully.");
			return true;
			
		}
		catch (SQLException e) {
			
			System.out.println("FAILED to add user: "+user.getErs_username()+".");
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Users getUserById(int user_id) {
		// TODO Auto-generated method stub
		try (Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "select * from ers_users where ers_users_id =?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, user_id);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				
				Users user = new Users (
						rs.getInt("ers_users_id"),
						rs.getString("ers_username"),
						rs.getString("ers_password"),
						rs.getString("user_first_name"),
						rs.getString("user_last_name"),
						rs.getString("user_email"),
						rs.getInt("user_role_id"),
						null
						);
				UserRolesDAO urolesDAO = new UserRolesDAO();
				int roleId = user.getUser_role_id();
				user.setUser_role(urolesDAO.getUserRoleById(roleId));
				return user;
			}
			
		} catch (SQLException e) {
			System.out.println("USER SELECTION FAILED");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<Users> getUsers() {
		// TODO Auto-generated method stub
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "select * from ers_users;";
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			ArrayList<Users> userList = new ArrayList<>();
			while(rs.next()) {
				Users u = new Users(
						rs.getInt("ers_users_id"),
						rs.getString("ers_username"),
						rs.getString("ers_password"),
						rs.getString("user_first_name"),
						rs.getString("user_last_name"),
						rs.getString("user_email"),
						rs.getInt("user_role_id"),
						null
						);
				int roleFk = u.getUser_role_id();
				UserRolesDAO uDAO = new UserRolesDAO();
				u.setUser_role(uDAO.getUserRoleById(roleFk));
				
				userList.add(u);
				
			}
			
			return userList;
			
		} catch(SQLException e) {
			System.out.println("GET USERS FAILED.");
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public Users getUserByUsername(String username) {
		// TODO Auto-generated method stub
		try (Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "select * from ers_users where ers_username =?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				
				Users user = new Users (
						rs.getInt("ers_users_id"),
						rs.getString("ers_username"),
						rs.getString("ers_password"),
						rs.getString("user_first_name"),
						rs.getString("user_last_name"),
						rs.getString("user_email"),
						rs.getInt("user_role_id"),
						null
						);
				user.setErs_password("**");
				UserRolesDAO urolesDAO = new UserRolesDAO();
				int roleId = user.getUser_role_id();
				user.setUser_role(urolesDAO.getUserRoleById(roleId));
				return user;
			}
			
		} catch (SQLException e) {
			System.out.println("USER SELECTION FAILED");
			e.printStackTrace();
		}
		return null;
	}

}
