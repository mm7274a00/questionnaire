package com.example.questionnaire.service.ifs;

import com.example.questionnaire.vo.QuizReq;
import com.example.questionnaire.vo.QuizRes;

public interface QuizService {
	
	public QuizRes create(QuizReq req);

	public QuizRes update(QuizReq req);
}
