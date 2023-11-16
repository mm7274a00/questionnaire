package com.example.questionnaire.vo;

import java.util.ArrayList;
import java.util.List;

import org.aspectj.weaver.patterns.TypePatternQuestions.Question;

import com.example.questionnaire.entity.Questionnaire;


public class QuizReq {
	
	private Questionnaire questionnaire = new Questionnaire();
	
	private List<Question> questionList = new ArrayList<>(); //�����w�]�Ȭ��šA��֧P�_��

	public QuizReq() {
		super();
	}

	public QuizReq(Questionnaire questionnaire, List<Question> questionList) {
		super();
		this.questionnaire = questionnaire;
		this.questionList = questionList;
	}

	public Questionnaire getQuestionnaire() {
		return questionnaire;
	}

	public void setQuestionnaire(Questionnaire questionnaire) {
		this.questionnaire = questionnaire;
	}

	public List<Question> getQuestionList() {
		return questionList;
	}

	public void setQuestionList(List<Question> questionList) {
		this.questionList = questionList;
	}


	
}//