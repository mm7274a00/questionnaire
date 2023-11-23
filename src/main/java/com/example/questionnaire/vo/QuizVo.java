package com.example.questionnaire.vo;

import java.util.ArrayList;
import java.util.List;

import com.example.questionnaire.entity.Question;
import com.example.questionnaire.entity.Questionnaire;
import com.fasterxml.jackson.annotation.JsonProperty;

public class QuizVo {
	
	private Questionnaire questionnaire = new Questionnaire();	//�ݨ�
	
	@JsonProperty("question_list")
	private List<Question> questionList = new ArrayList<>(); //�����w�]�Ȭ��šA��֧P�_�� //�ϥ�List�]�t�h�i�ݨ�

	public QuizVo() {
		super();
	}

	public QuizVo(Questionnaire questionnaire, List<Question> questionList) {
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
	
}
