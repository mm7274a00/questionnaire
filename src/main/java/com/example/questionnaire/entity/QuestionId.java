package com.example.questionnaire.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
public class QuestionId implements Serializable{
	
	private int qnId;
	
	private int quId;

	public QuestionId() {
		super();
		// TODO Auto-generated constructor stub
	}

	public QuestionId(int qnId, int quId) {
		super();
		this.qnId = qnId;
		this.quId = quId;
	}

	public int getQnId() {
		return qnId;
	}

	public void setQnId(int qnId) {
		this.qnId = qnId;
	}

	public int getQuId() {
		return quId;
	}

	public void setQuid(int quId) {
		this.quId = quId;
	}
	
}
