package com.example.questionnaire.vo;

import java.util.List;

import com.example.questionnaire.entity.Question;
import com.example.questionnaire.entity.Questionnaire;

public class QuizReq extends QuizVo{

	public QuizReq() {
		super();
	}

	public QuizReq(Questionnaire questionnaire, List<Question> questionList) {
		super(questionnaire, questionList);

	}
	
}//