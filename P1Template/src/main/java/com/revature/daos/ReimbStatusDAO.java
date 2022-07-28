package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.ReimbStatus;
import com.revature.utils.ConnectionUtil;

public class ReimbStatusDAO implements ReimbStatusDAOInterface{

	@Override
	public ReimbStatus getStatusById(int status_id) {
		// TODO Auto-generated method stub
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "select * from ers_reimbursement_status where reimb_status_id = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, status_id);
			ResultSet rs = ps.executeQuery(); 
			
			while (rs.next()) {
				ReimbStatus status = new ReimbStatus(
						rs.getInt("reimb_status_id"),
						rs.getString("reimb_status")
						);
				return status;
			}
			
		} catch(SQLException e) {
			System.out.println("GET STATUS FAILED");
			e.printStackTrace();
		}
		return null;
	}

}
