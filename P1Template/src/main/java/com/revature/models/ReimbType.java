package com.revature.models;

public class ReimbType {

	private int reimb_type_id;
	private String reimb_type;
	
	//Boilerplate-------------------------
	//Constructor-------------------------
	public ReimbType() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ReimbType(int reimb_type_id, String reimb_type) {
		super();
		this.reimb_type_id = reimb_type_id;
		this.reimb_type = reimb_type;
	}
	
	public ReimbType(String reimb_type) {
		super();
		this.reimb_type = reimb_type;
	}
	
	//Setters and Getters------------------
	public int getReimb_type_id() {
		return reimb_type_id;
	}
	public void setReimb_type_id(int reimb_type_id) {
		this.reimb_type_id = reimb_type_id;
	}
	public String getReimb_type() {
		return reimb_type;
	}
	public void setReimb_type(String reimb_type) {
		this.reimb_type = reimb_type;
	}
	
	//To String----------------------------
	@Override
	public String toString() {
		return "ReimbType [reimb_type_id=" + reimb_type_id + ", reimb_type=" + reimb_type + "]";
	}

}
