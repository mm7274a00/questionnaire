package com.example.questionnaire.vo;

import java.util.List;

import com.example.questionnaire.constants.RtnCode;
import com.example.questionnaire.entity.Question;
import com.example.questionnaire.entity.Questionnaire;

public class QuizRes {
	
//	private List<Questionnaire> questionnaireList;	//問卷
//	
//	private List<Question> questionList;	//題目
	//使用組合統整上面兩行
	private RtnCode rtnCode;

	public QuizRes() {
		super();
	}

	public QuizRes(RtnCode rtnCode) {
		super();
		this.rtnCode = rtnCode;
	}

	public RtnCode getRtnCode() {
		return rtnCode;
	}

	public void setRtnCode(RtnCode rtnCode) {
		this.rtnCode = rtnCode;
	}

}
