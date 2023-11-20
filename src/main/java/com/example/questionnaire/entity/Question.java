package com.example.questionnaire.entity;

import java.time.chrono.ChronoLocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "question")
@IdClass(value = QuestionId.class)
public class Question {
	
	@Id
	@Column(name = "id")
	private int quId;
	
	@Column(name = "qn_id")
	private int qnId;
	
	@Column(name = "q_title")
	private String qTitle;
	
	@Column(name = "option_type")
	private String optionType;
	
	@Column(name = "is_necessary")
	private String necessary;
	
	@Column(name = "q_option")
	private String option;

	public Question() {
		super();
	}

	public Question(int quId, int qnId, String qTitle, String optionType, String necessary, String option) {
		super();
		this.quId = quId;
		this.qnId = qnId;
		this.qTitle = qTitle;
		this.optionType = optionType;
		this.necessary = necessary;
		this.option = option;
	}

	public Question(int quId,String qTitle, String optionType, boolean isPublished, String option) {
		// TODO Auto-generated constructor stub
		super();
		this.qnId = quId;
		this.qTitle = qTitle;
		this.optionType = optionType;
		this.option = option;
	}

	public int getQuId() {
		return quId;
	}

	public void setQuId(int quId) {
		this.quId = quId;
	}

	public int getQnId() {
		return qnId;
	}

	public void setQnId(int qnId) {
		this.qnId = qnId;
	}

	public String getqTitle() {
		return qTitle;
	}

	public void setqTitle(String qTitle) {
		this.qTitle = qTitle;
	}

	public String getOptionType() {
		return optionType;
	}

	public void setOptionType(String optionType) {
		this.optionType = optionType;
	}

	public String getNecessary() {
		return necessary;
	}

	public void setNecessary(String necessary) {
		this.necessary = necessary;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public boolean isPublished() {
		// TODO Auto-generated method stub
		return false;
	}

//	public ChronoLocalDate getStartDate() {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
