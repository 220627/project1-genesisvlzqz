package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.LogManager;

import com.revature.models.ReimbStatus;
import com.revature.models.Reimbursement;
import com.revature.utils.ConnectionUtil;

public class ReimbursementDAO implements ReimbursementDAOInterface {

	static java.util.logging.Logger log = LogManager.getLogManager().getLogger("");
	@Override
	public boolean insertReimbursement(Reimbursement reimb) {
		// TODO Auto-generated method stub
		try(Connection conn = ConnectionUtil.getConnection()){
		
			String sql = "insert into ers_reimbursement ("
					+ "reimb_amount,"
					+ "reimb_submitted,"
					+ "reimb_description,"
					+ "reimb_author,"
					+ "reimb_status_id,"
					+ "reimb_type_id)"
					+ "values (?,?,?,?,?,?);";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setFloat(1,reimb.getReimb_amount());
			ps.setTimestamp(2, reimb.getReimb_submitted());
			ps.setString(3, reimb.getReimb_description());
			ps.setInt(4, reimb.getReimb_author());
			ps.setInt(5, reimb.getReimb_status_id());
			ps.setInt(6, reimb.getReimb_type_id());

			ps.executeUpdate();
			log.info("Created Reimbursement");
			System.out.println("Reimbursement for " + reimb.getReimb_amount() + " has been requested..");
			return true;
			
		} catch(SQLException e) {
			log.info("Failed to create Reimbursement");
			System.out.println("INSERT REIMBURSEMENT FAILED"); 
			e.printStackTrace(); 
		}
		return false;
	}

	@Override
	public ArrayList<Reimbursement> getReimbursementByUserID(int user_id) {
		// TODO Auto-generated method stub
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "select * from ers_reimbursement "
					+ "where reimb_author = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ArrayList<Reimbursement> reimbList = new ArrayList<Reimbursement>();
			ps.setInt(1, user_id);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				Reimbursement r = new Reimbursement(
						rs.getInt("reimb_id"),
						rs.getFloat("reimb_amount"),
						rs.getTimestamp("reimb_submitted"),
						rs.getString("reimb_description"),
						rs.getInt("reimb_author"),
						rs.getInt("reimb_status_id"),
						rs.getInt("reimb_type_id")
						);
				//Initiating Objects within Reimbursement
				UsersDAO uDAO = new UsersDAO();
				ReimbTypeDAO tDAO = new ReimbTypeDAO();
				ReimbStatusDAO sDAO = new ReimbStatusDAO();
				r.setAuthor(uDAO.getUserById(r.getReimb_author()));
				r.setStatus(sDAO.getStatusById(r.getReimb_status_id()));
				r.setType(tDAO.getTypeById(r.getReimb_type_id()));
				
				//Dealing with possible null columns
				Timestamp resolve = rs.getTimestamp("reimb_resolved");
				if(rs.wasNull()) {
					r.setReimb_resolved(null);
				} else {
					r.setReimb_resolved(resolve);
					r.setResolver(uDAO.getUserById(r.getReimb_resolver()));
				}
				
				int resolver = rs.getInt("reimb_resolver");
				if(rs.wasNull()) {
					r.setResolver(null);
				} else {
					r.setResolver(uDAO.getUserById(resolver));
				}
				
				//receipt would go here
			
				reimbList.add(r);
			}
			log.info("User accessed Reimbursements");
			return reimbList;
		} catch(SQLException e) {
			log.info("User failed accessed Reimbursements");
			System.out.println("GET REIMBURSEMENT BY USER FAILED"); 
			e.printStackTrace(); 
		}
		
		return null;
	}

	@Override
	public Reimbursement getReimbursementById(int reimb_id) {
		// TODO Auto-generated method stub
		try(Connection conn = ConnectionUtil.getConnection()){

			String sql = "select * from ers_reimbursement "
					+ "where reimb_id = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, reimb_id);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				Reimbursement r = new Reimbursement(
						rs.getInt("reimb_id"),
						rs.getFloat("reimb_amount"),
						rs.getTimestamp("reimb_submitted"),
						rs.getString("reimb_description"),
						rs.getInt("reimb_author"),
						rs.getInt("reimb_status_id"),
						rs.getInt("reimb_type_id")
						);
				//Initiating Objects within Reimbursement
				UsersDAO uDAO = new UsersDAO();
				ReimbTypeDAO tDAO = new ReimbTypeDAO();
				ReimbStatusDAO sDAO = new ReimbStatusDAO();
				r.setAuthor(uDAO.getUserById(r.getReimb_author()));
				r.setStatus(sDAO.getStatusById(r.getReimb_status_id()));
				r.setType(tDAO.getTypeById(r.getReimb_type_id()));
				
				//Dealing with possible null columns
				Timestamp resolve = rs.getTimestamp("reimb_resolved");
				if(rs.wasNull()) {
					r.setReimb_resolved(null);
				} else {
					r.setReimb_resolved(resolve);
				}
				
				int resolver = rs.getInt("reimb_resolver");
				if(rs.wasNull()) {
					r.setResolver(null);
				} else {
					r.setResolver(uDAO.getUserById(resolver));
				}
				//receipt would go here
				
				return r;
			}//end while
			
		} catch(SQLException e) {
			System.out.println("GET REIMBURSEMENT BY ID FAILED"); 
			e.printStackTrace(); 
		}
		return null;
	}

	@Override
	public ArrayList<Reimbursement> getReimbursementByStatus(ReimbStatus status) {
		// TODO Auto-generated method stub
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "select * from ers_reimbursement "
					+ "where reimb_status_id = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ArrayList<Reimbursement> reimbList = new ArrayList<Reimbursement>();
			ps.setInt(1, status.getReimb_status_id());
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				Reimbursement r = new Reimbursement(
						rs.getInt("reimb_id"),
						rs.getFloat("reimb_amount"),
						rs.getTimestamp("reimb_submitted"),
						rs.getString("reimb_description"),
						rs.getInt("reimb_author"),
						rs.getInt("reimb_status_id"),
						rs.getInt("reimb_type_id")
						);
				//Initiating Objects within Reimbursement
				UsersDAO uDAO = new UsersDAO();
				ReimbTypeDAO tDAO = new ReimbTypeDAO();
				ReimbStatusDAO sDAO = new ReimbStatusDAO();
				r.setAuthor(uDAO.getUserById(r.getReimb_author()));
				r.setStatus(sDAO.getStatusById(r.getReimb_status_id()));
				r.setType(tDAO.getTypeById(r.getReimb_type_id()));
				
				//Dealing with possible null columns
				Timestamp resolve = rs.getTimestamp("reimb_resolved");
				if(rs.wasNull()) {
					r.setReimb_resolved(null);
				} else {
					r.setReimb_resolved(resolve);
				}
				
				int resolver = rs.getInt("reimb_resolver");
				if(rs.wasNull()) {
					r.setResolver(null);
				} else {
					r.setResolver(uDAO.getUserById(resolver));
				}
				//receipt would go here
				
				reimbList.add(r);
			}
			return reimbList;
			
		} catch(SQLException e) {
			System.out.println("GET REIMBURSEMENT BY STATUS FAILED"); 
			e.printStackTrace(); 
		}
		return null;
	}

	@Override
	public ArrayList<Reimbursement> getAllReimbursement() {
		// TODO Auto-generated method stub
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "select * from ers_reimbursement;";
			Statement s = conn.createStatement();
			ArrayList<Reimbursement> reimbList = new ArrayList<Reimbursement>();
			ResultSet rs = s.executeQuery(sql);
			
			while(rs.next()) {
				
				Reimbursement r = new Reimbursement(
						rs.getInt("reimb_id"),
						rs.getFloat("reimb_amount"),
						rs.getTimestamp("reimb_submitted"),
						rs.getString("reimb_description"),
						rs.getInt("reimb_author"),
						rs.getInt("reimb_status_id"),
						rs.getInt("reimb_type_id")
						);
				//Initiating Objects within Reimbursement
				UsersDAO uDAO = new UsersDAO();
				ReimbTypeDAO tDAO = new ReimbTypeDAO();
				ReimbStatusDAO sDAO = new ReimbStatusDAO();
				r.setAuthor(uDAO.getUserById(r.getReimb_author()));
				r.setStatus(sDAO.getStatusById(r.getReimb_status_id()));
				r.setType(tDAO.getTypeById(r.getReimb_type_id()));
				
				//Dealing with possible null columns
				Timestamp resolve = rs.getTimestamp("reimb_resolved");
				if(rs.wasNull()) {
					r.setReimb_resolved(null);
				} else {
					r.setReimb_resolved(resolve);
				}
				
				int resolver = rs.getInt("reimb_resolver");
				if(rs.wasNull()) {
					r.setResolver(null);
				} else {
					r.setResolver(uDAO.getUserById(resolver));
				}
				//receipt would go here
				
				reimbList.add(r);
			}
			log.info("Acessed All Reimbursement");
			return reimbList;
			
		} catch(SQLException e) {
			log.info("Failed Acess All Reimbursement");
			System.out.println("GET ALL REIMBURSEMENT FAILED"); 
			e.printStackTrace(); 
		}
		return null;
	}

	@Override
	public boolean updateReimbursement(Reimbursement reimb) {
		// TODO Auto-generated method stub
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "update ers_reimbursement "
					+ "set reimb_resolved = ?,"
					+ "reimb_resolver = ?,"
					+ "reimb_status_id = ?"
					+ "where reimb_id = ?;";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			Timestamp ts = new Timestamp(System.currentTimeMillis());
			ps.setTimestamp(1, ts);
			ps.setInt(2, reimb.getReimb_resolver() );
			ps.setInt(3, reimb.getReimb_status_id());
			ps.setInt(4, reimb.getReimb_id());
			
			ps.executeUpdate();
			ReimbStatusDAO sDAO = new ReimbStatusDAO();
			ReimbStatus status = sDAO.getStatusById(reimb.getReimb_status_id());
			
			System.out.println("Reimbursement ID# "+ reimb.getReimb_id() + " updated to " + status.getReimb_status());
			return true;
			
		} catch(SQLException e) {
			System.out.println("UPDATE REIMBURSEMENT FAILED"); 
			e.printStackTrace(); 
		}
		return false;
	}

	@Override
	public ArrayList<Reimbursement> getReimbursementByUserStatus(int user_id, ReimbStatus status) {
		// TODO Auto-generated method stub
try(Connection conn = ConnectionUtil.getConnection()){
	
			ArrayList<Reimbursement> reimbList = new ArrayList<Reimbursement>();
			
			String sql = "select * from ers_reimbursement "
					+ "where reimb_author = ? AND reimb_status_id = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, user_id);
			ps.setInt(2, status.getReimb_status_id());	
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				Reimbursement r = new Reimbursement(
						rs.getInt("reimb_id"),
						rs.getFloat("reimb_amount"),
						rs.getTimestamp("reimb_submitted"),
						rs.getString("reimb_description"),
						rs.getInt("reimb_author"),
						rs.getInt("reimb_status_id"),
						rs.getInt("reimb_type_id")
						);
				//Initiating Objects within Reimbursement
				UsersDAO uDAO = new UsersDAO();
				ReimbTypeDAO tDAO = new ReimbTypeDAO();
				ReimbStatusDAO sDAO = new ReimbStatusDAO();
				r.setAuthor(uDAO.getUserById(r.getReimb_author()));
				r.setStatus(sDAO.getStatusById(r.getReimb_status_id()));
				r.setType(tDAO.getTypeById(r.getReimb_type_id()));
				
				//Dealing with possible null columns
				Timestamp resolve = rs.getTimestamp("reimb_resolved");
				if(rs.wasNull()) {
					r.setReimb_resolved(null);
				} else {
					r.setReimb_resolved(resolve);
				}
				
				int resolver = rs.getInt("reimb_resolver");
				if(rs.wasNull()) {
					r.setResolver(null);
				} else {
					r.setResolver(uDAO.getUserById(resolver));
				}
				//receipt would go here
				
				reimbList.add(r);
			}
			return reimbList;
			
		} catch(SQLException e) {
			System.out.println("GET REIMBURSEMENT BY STATUS FAILED"); 
			e.printStackTrace(); 
		}
		return null;
	}
	

}
