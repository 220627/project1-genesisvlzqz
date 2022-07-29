package com.revature.models;

public class Responses {
	
	private String statusMsg;
	
	private Object statusObject;

	public Responses() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Responses(String statusMsg, Object statusObject) {
		super();
		this.statusMsg = statusMsg;
		this.statusObject = statusObject;
	}

	public String getStatusMsg() {
		return statusMsg;
	}

	public void setStatusMsg(String statusMsg) {
		this.statusMsg = statusMsg;
	}

	public Object getStatusObject() {
		return statusObject;
	}

	public void setStatusObject(Object statusObject) {
		this.statusObject = statusObject;
	}
	
}
