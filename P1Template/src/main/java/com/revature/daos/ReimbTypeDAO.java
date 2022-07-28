package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.ReimbType;
import com.revature.utils.ConnectionUtil;

public class ReimbTypeDAO implements ReimbTypeDAOInterface {

	@Override
	public ReimbType getTypeById(int type_id) {
		// TODO Auto-generated method stub
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "select * from ers_reimbursement_type where reimb_type_id = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, type_id);
			ResultSet rs = ps.executeQuery(); 
			
			while (rs.next()) {
				ReimbType type = new ReimbType(
						rs.getInt("reimb_type_id"),
						rs.getString("reimb_type")
						);
				return type;
			}
			
		} catch(SQLException e) {
			System.out.println("GET STATUS FAILED");
			e.printStackTrace();
		}

		return null;
	}

}
