package com.revature.daos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;

import com.revature.models.UserRoles;
import com.revature.utils.ConnectionUtil;

public class UserRolesDAO implements UserRolesDAOInterface{

	@Override
	public UserRoles getUserRoleById(int user_role_id) {
		// TODO Auto-generated method stub
		
		try (Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "select * from ers_user_roles where ers_user_role_id =?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, user_role_id);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				
				UserRoles role = new UserRoles(
						rs.getInt("ers_user_role_id"),
						rs.getString("user_role")
						);
				return role;
			}
			
		} catch (SQLException e) {
			System.out.println("ROLE SELECTION FAILED");
			e.printStackTrace();
		}
		return null;
	}

}
