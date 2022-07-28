package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.Users;
import com.revature.utils.ConnectionUtil;

public class AuthDAO {

	public Users login(String username, String password) {
		// TODO Auto-generated method stub
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "select * from ers_users where ers_username = ? and ers_password = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				
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
				
				return u;
				
			}
			
		} catch (SQLException e) {
			System.out.println("LOGIN FAILED");
			e.printStackTrace();
		}
		
		return null;
	}
	
	
}
