package com.revature.models;

public class ReimbStatus {
	
	private int reimb_status_id;
	private String reimb_status;
	
	//Boilerplate==============================
	//Constructors==============
	public ReimbStatus() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ReimbStatus(int reimb_status_id, String reimb_status) {
		super();
		this.reimb_status_id = reimb_status_id;
		this.reimb_status = reimb_status;
	}
	
	public ReimbStatus(String reimb_status) {
		super();
		this.reimb_status = reimb_status;
	}
	
	//Getters and Setters===============
	public int getReimb_status_id() {
		return reimb_status_id;
	}
	public void setReimb_status_id(int reimb_status_id) {
		this.reimb_status_id = reimb_status_id;
	}
	public String getReimb_status() {
		return reimb_status;
	}
	public void setReimb_status(String reimb_status) {
		this.reimb_status = reimb_status;
	}
	
	//To string
	@Override
	public String toString() {
		return "ReimbStatus [reimb_status_id=" + reimb_status_id + ", reimb_status=" + reimb_status + "]";
	}
	
	
}
