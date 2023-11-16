package com.example.questionnaire.entity;

import java.util.List;

public class Quizs {
	
	private List<Questionnaire> questionnaireList;
    private List<Question> questionList;
	public Quizs() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Quizs(List<Questionnaire> questionnaireList, List<Question> questionList) {
		super();
		this.questionnaireList = questionnaireList;
		this.questionList = questionList;
	}
	public List<Questionnaire> getQuestionnaireList() {
		return questionnaireList;
	}
	public void setQuestionnaireList(List<Questionnaire> questionnaireList) {
		this.questionnaireList = questionnaireList;
	}
	public List<Question> getQuestionList() {
		return questionList;
	}
	public void setQuestionList(List<Question> questionList) {
		this.questionList = questionList;
	}

    	
}
