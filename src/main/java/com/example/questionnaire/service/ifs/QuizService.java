package com.example.questionnaire.service.ifs;

import java.time.LocalDate;
import java.util.List;

import com.example.questionnaire.vo.QuizReq;
import com.example.questionnaire.vo.QuizRes;
import com.example.questionnaire.vo.QuizVo;

public interface QuizService {
	
	public QuizRes create(QuizReq req);

	public QuizRes update(QuizReq req);
	
	public QuizRes deleteQuestionnaire(List<Integer> qnIdList);
	
	public QuizRes deleteQuestion(int qnId, List<Integer>quIdList);	//�R���P�@�i�ݨ��� �@�� �� �h���D��
	
	public QuizRes serach(String title, LocalDate startDate, LocalDate endDate);

}
