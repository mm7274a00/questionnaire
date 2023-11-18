package com.example.questionnaire;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import com.example.questionnaire.entity.Question;
import com.example.questionnaire.entity.Questionnaire;
import com.example.questionnaire.service.ifs.QuizService;
import com.example.questionnaire.vo.QuizReq;
import com.example.questionnaire.vo.QuizRes;

@SpringBootTest
public class QuizServiceTest {
	
	@Autowired
	private QuizService service;
	
	@Test
	public void createTest() {
		Questionnaire questionnaire = new Questionnaire("test1", "test",false, 
				LocalDate.of(2023, 11, 17), LocalDate.of(2023, 11, 30));
		
		List<Question> questionList = new ArrayList<>();
		Question q1 = new Question(1,"test_question_1","single",false,"AAA;BBB;CCC");
		Question q2 = new Question(2,"test_question_2","multi",false,"10;20;30;40");
		Question q3 = new Question(3,"test_question_3","text",false,"ABC");
		questionList.addAll(Arrays.asList(q1, q2, q3));
		
		QuizReq req = new QuizReq(questionnaire,questionList);
		QuizRes res = service.create(req);
		System.out.println("Returned RtnCode: " + res.getRtnCode().getCode());
		System.out.println("Returned Message: " + res.getRtnCode().getMessage());
		System.out.println("Questionnaire: " + questionnaire);
		System.out.println("Question List: " + questionList);
		System.out.println("Questionnaire: " + req.getQuestionnaire());
		System.out.println("Question List: " + req.getQuestionList());
		System.out.println("Received QuizReq: " + req);
		Assert.isTrue(res.getRtnCode().getCode() == 200, "create error!");
	}
}//
