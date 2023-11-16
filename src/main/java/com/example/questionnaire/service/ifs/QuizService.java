package com.example.questionnaire.service.ifs;

import java.util.List;

import com.example.questionnaire.vo.QuizReq;
import com.example.questionnaire.vo.QuizRes;

public interface QuizService {
	
	public QuizRes create(QuizReq req);

	public QuizRes update(QuizReq req);
	
	public QuizRes deleteQuestionnaire(List<Integer>qnIdList);
	
	public QuizRes deleteQuestion(int qnId,List<Integer>quIdList);	//刪除同一張問卷的 一個 或 多個題目
}
