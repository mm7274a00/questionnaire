package com.example.questionnaire.vo;

import java.util.ArrayList;
import java.util.List;

import com.example.questionnaire.entity.Question;
import com.example.questionnaire.entity.Questionnaire;

public class QuizReq {
	
	private Questionnaire questionnaire = new Questionnaire();
	
	private List<Question> questionList = new ArrayList<>(); //給予預設值為空，減少判斷式

	public Object getQuestionnaire;

	public QuizReq() {
		super();
	}

	public QuizReq(Questionnaire questionnaire, List<Question> questionList) {
		super();
		this.questionnaire = questionnaire;
		this.questionList = questionList;
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

	public Question getQuestion() {
		// TODO Auto-generated method stub
		return null;
	}

	public Questionnaire getQuestionnaire() {
		// TODO Auto-generated method stub
		return null;
	}

}
