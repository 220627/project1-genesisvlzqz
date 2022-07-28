package com.revature.models;

import java.io.FileInputStream;
import java.sql.Timestamp;

public class Reimbursement {
	
	private int reimb_id;
	private float reimb_amount;
	private Timestamp reimb_submitted;
	private Timestamp reimb_resolved;
	private String reimb_description;
	private FileInputStream reimb_receipt;
	private int reimb_author;
	private Users author;
	private int reimb_resolver;
	private Users resolver;
	private int reimb_status_id;
	private ReimbStatus status;
	private int reimb_type_id;
	private ReimbType type;
	
	//Constructors
	public Reimbursement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Reimbursement(int reimb_id, float reimb_amount, Timestamp reimb_submitted, Timestamp reimb_resolved,
			String reimb_description, FileInputStream reimb_receipt, int reimb_author, int reimb_resolver,
			int reimb_status_id, int reimb_type_id) {
		super();
		this.reimb_id = reimb_id;
		this.reimb_amount = reimb_amount;
		this.reimb_submitted = reimb_submitted;
		this.reimb_resolved = reimb_resolved;
		this.reimb_description = reimb_description;
		this.reimb_receipt = reimb_receipt;
		this.reimb_author = reimb_author;
		this.reimb_resolver = reimb_resolver;
		this.reimb_status_id = reimb_status_id;
		this.reimb_type_id = reimb_type_id;
	}

	//for originating request
	public Reimbursement(int reimb_id, float reimb_amount, Timestamp reimb_submitted, String reimb_description,
			int reimb_author, int reimb_status_id, int reimb_type_id) {
		super();
		this.reimb_id = reimb_id;
		this.reimb_amount = reimb_amount;
		this.reimb_submitted = reimb_submitted;
		this.reimb_description = reimb_description;
		this.reimb_author = reimb_author;
		this.reimb_status_id = reimb_status_id;
		this.reimb_type_id = reimb_type_id;
	}

	public Reimbursement(int reimb_id, float reimb_amount, Timestamp reimb_submitted, String reimb_description,
			FileInputStream reimb_receipt, int reimb_author, int reimb_resolver, int reimb_status_id,
			int reimb_type_id) {
		super();
		this.reimb_id = reimb_id;
		this.reimb_amount = reimb_amount;
		this.reimb_submitted = reimb_submitted;
		this.reimb_description = reimb_description;
		this.reimb_receipt = reimb_receipt;
		this.reimb_author = reimb_author;
		this.reimb_resolver = reimb_resolver;
		this.reimb_status_id = reimb_status_id;
		this.reimb_type_id = reimb_type_id;
	}

	public Reimbursement(float reimb_amount, Timestamp reimb_submitted, Timestamp reimb_resolved, String reimb_description,
			FileInputStream reimb_receipt, int reimb_author, int reimb_resolver, int reimb_status_id,
			int reimb_type_id) {
		super();
		this.reimb_amount = reimb_amount;
		this.reimb_submitted = reimb_submitted;
		this.reimb_resolved = reimb_resolved;
		this.reimb_description = reimb_description;
		this.reimb_receipt = reimb_receipt;
		this.reimb_author = reimb_author;
		this.reimb_resolver = reimb_resolver;
		this.reimb_status_id = reimb_status_id;
		this.reimb_type_id = reimb_type_id;
	}
	public Reimbursement(float reimb_amount, Timestamp reimb_submitted, Timestamp reimb_resolved, String reimb_description,
			FileInputStream reimb_receipt, int reimb_author, int reimb_status_id,
			int reimb_type_id) {
		super();
		this.reimb_amount = reimb_amount;
		this.reimb_submitted = reimb_submitted;
		this.reimb_resolved = reimb_resolved;
		this.reimb_description = reimb_description;
		this.reimb_receipt = reimb_receipt;
		this.reimb_author = reimb_author;
		this.reimb_status_id = reimb_status_id;
		this.reimb_type_id = reimb_type_id;
	}
	//Setters and Getters
	public int getReimb_id() {
		return reimb_id;
	}

	public void setReimb_id(int reimb_id) {
		this.reimb_id = reimb_id;
	}

	public float getReimb_amount() {
		return reimb_amount;
	}

	public void setReimb_amount(float reimb_amount) {
		this.reimb_amount = reimb_amount;
	}

	public Timestamp getReimb_submitted() {
		return reimb_submitted;
	}

	public void setReimb_submitted(Timestamp reimb_submitted) {
		this.reimb_submitted = reimb_submitted;
	}

	public Timestamp getReimb_resolved() {
		return reimb_resolved;
	}

	public void setReimb_resolved(Timestamp reimb_resolved) {
		this.reimb_resolved = reimb_resolved;
	}

	public String getReimb_description() {
		return reimb_description;
	}

	public void setReimb_description(String reimb_description) {
		this.reimb_description = reimb_description;
	}

	public FileInputStream getReimb_receipt() {
		return reimb_receipt;
	}

	public void setReimb_receipt(FileInputStream reimb_receipt) {
		this.reimb_receipt = reimb_receipt;
	}

	public int getReimb_author() {
		return reimb_author;
	}

	public void setReimb_author(int reimb_author) {
		this.reimb_author = reimb_author;
	}

	public Users getAuthor() {
		return author;
	}

	public void setAuthor(Users author) {
		this.author = author;
	}

	public int getReimb_resolver() {
		return reimb_resolver;
	}

	public void setReimb_resolver(int reimb_resolver) {
		this.reimb_resolver = reimb_resolver;
	}

	public Users getResolver() {
		return resolver;
	}

	public void setResolver(Users resolver) {
		this.resolver = resolver;
	}

	public int getReimb_status_id() {
		return reimb_status_id;
	}

	public void setReimb_status_id(int reimb_status_id) {
		this.reimb_status_id = reimb_status_id;
	}

	public ReimbStatus getStatus() {
		return status;
	}

	public void setStatus(ReimbStatus status) {
		this.status = status;
	}

	public int getReimb_type_id() {
		return reimb_type_id;
	}

	public void setReimb_type_id(int reimb_type_id) {
		this.reimb_type_id = reimb_type_id;
	}

	public ReimbType getType() {
		return type;
	}

	public void setType(ReimbType type) {
		this.type = type;
	}

	//to string
	@Override
	public String toString() {
		return "Reimbursement [reimb_id=" + reimb_id + ", reimb_amount=" + reimb_amount + ", reimb_submitted="
				+ reimb_submitted + ", reimb_resolved=" + reimb_resolved + ", reimb_description=" + reimb_description
				+ ", reimb_receipt=" + reimb_receipt + ", reimb_author=" + reimb_author + ", author=" + author
				+ ", reimb_resolver=" + reimb_resolver + ", resolver=" + resolver + ", reimb_status_id="
				+ reimb_status_id + ", status=" + status + ", reimb_type_id=" + reimb_type_id + ", type=" + type + "]";
	}
	
	

}
