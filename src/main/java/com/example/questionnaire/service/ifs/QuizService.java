package com.example.questionnaire.service.ifs;

import java.time.LocalDate;
import java.util.List;

import com.example.questionnaire.entity.User;
import com.example.questionnaire.vo.QuestionRes;
import com.example.questionnaire.vo.QuestionnaireRes;
import com.example.questionnaire.vo.QuizReq;
import com.example.questionnaire.vo.QuizRes;
import com.example.questionnaire.vo.QuizVo;
import com.example.questionnaire.vo.UserRes;

public interface QuizService {
	
	public QuizRes create(QuizReq req);

	public QuizRes update(QuizReq req);
	
	public QuizRes deleteQuestionnaire(List<Integer> qnIdList);
	
	public QuizRes deleteQuestion(int qnId, List<Integer>quIdList);	//�R���P�@�i�ݨ��� �@�� �� �h���D��
	
	public QuizRes search(String title, LocalDate startDate, LocalDate endDate);
	
	public QuizRes searchFuzzy(String title, LocalDate startDate, LocalDate endDate);
	
	public QuestionnaireRes searchQuestionnaireList(String title, LocalDate startDate, LocalDate endDate, boolean isPublished);

	public QuestionRes searchQuestionList(int qnId);

	public QuestionnaireRes searchQuestionnaire(int qnId);
	
	public UserRes getAns(String phoneNumber, List<User> userList);
	
	public UserRes getAns1(List<User> userList);
	
}
