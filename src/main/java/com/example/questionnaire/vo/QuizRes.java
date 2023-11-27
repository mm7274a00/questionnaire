package com.example.questionnaire.vo;

import java.util.List;

import com.example.questionnaire.constants.RtnCode;
//import com.example.questionnaire.entity.Question;
//import com.example.questionnaire.entity.Questionnaire;

public class QuizRes {
	
//	private List<Questionnaire> questionnaireList;	//�ݨ�
//	
//	private List<Question> questionList;	//�D��
	//�ϥβզX�ξ�W�����
	private List<QuizVo> quizVoList;
	
	private List<QnQuVo> qnQuVoList;
	
	private RtnCode rtnCode;

	public QuizRes() {
		super();
	}
	
	public QuizRes(RtnCode rtnCode) {
		super();
		this.rtnCode = rtnCode;
	}
	
	
	public QuizRes(List<QuizVo> quizVoList, RtnCode rtnCode) {
		super();
		this.quizVoList = quizVoList;
		this.rtnCode = rtnCode;
	}

	public List<QuizVo> getQuizVoList() {
		return quizVoList;
	}

	public void setQuizVoList(List<QuizVo> quizVoList) {
		this.quizVoList = quizVoList;
	}

	public RtnCode getRtnCode() {
		return rtnCode;
	}

	public void setRtnCode(RtnCode rtnCode) {
		this.rtnCode = rtnCode;
	}

}
