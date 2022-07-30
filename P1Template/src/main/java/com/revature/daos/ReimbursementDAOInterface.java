package com.revature.daos;

import java.util.ArrayList;

import com.revature.models.ReimbStatus;
import com.revature.models.Reimbursement;
import com.revature.models.Users;

public interface ReimbursementDAOInterface {
	
	//employees
	boolean insertReimbursement(Reimbursement reimb);
	ArrayList<Reimbursement> getReimbursementByUserID(int user_id);
	ArrayList<Reimbursement> getReimbursementByUserStatus(int user_id, ReimbStatus status);
	//employees/manager
	Reimbursement getReimbursementById(int reimb_id);
	
	//manager
	ArrayList<Reimbursement> getReimbursementByStatus(ReimbStatus status);
	ArrayList<Reimbursement> getAllReimbursement();
	boolean updateReimbursement(Reimbursement reimb);
	
}
