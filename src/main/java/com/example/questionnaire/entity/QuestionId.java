package com.example.questionnaire.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "questionId")
public class QuestionId {
	
	@Id
	@Column(name = "id")
	private int id;
	
	@Column(name = "qn_id")
	private int qnId;
	
	@Column(name = "q_title")
	private int qTitle;
	
	@Column(name = "option_type")
	private int optionType;
}
