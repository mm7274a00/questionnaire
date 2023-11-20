package com.example.questionnaire.vo;

import java.util.List;

import com.example.questionnaire.entity.Question;
import com.example.questionnaire.entity.Questionnaire;

public class QuizReq extends QuizVo{

	public List<Question>DeleteQuestionList;
	
	public QuizReq() {
		super();
	}

	public QuizReq(Questionnaire questionnaire, List<Question> questionList) {
		super(questionnaire, questionList);

	}

	public List<Question> getDeleteQuestionList() {
		// TODO Auto-generated method stub
		return null;
	}
	
}//