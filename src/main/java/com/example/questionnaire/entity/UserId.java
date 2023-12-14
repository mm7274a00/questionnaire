package com.example.questionnaire.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class UserId implements Serializable{
	
	private String phoneNumber;
	
	private int qnId;
	
	
	public UserId() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserId(String phoneNumber, int qnId, int qId) {
		super();
		this.phoneNumber = phoneNumber;
		this.qnId = qnId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getQnId() {
		return qnId;
	}

	public void setQnId(int qnId) {
		this.qnId = qnId;
	}


}
