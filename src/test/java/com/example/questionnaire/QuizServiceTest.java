package com.example.questionnaire;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.questionnaire.entity.Question;
import com.example.questionnaire.entity.Questionnaire;
import com.example.questionnaire.service.ifs.QuizService;
import com.example.questionnaire.vo.QuizReq;

@SpringBootTest
public class QuizServiceTest {
	
	@Autowired
	private QuizService service;
	
	@Test
	public void createTest() {
		Questionnaire questionnaire = new Questionnaire("test1", "test",false, 
				LocalDate.of(2023, 11, 17), LocalDate.of(2023, 11, 30));
		List<Question> questionList = new ArrayList<>();
		Question q1 = new Question("test_question_1","single",false,"AAA;BBB;CCC");
		Question q2 = new Question("test_question_2","multi",false,"AAA;BBB;CCC");
		Question q3 = new Question("test_question_3","text",false,"AAA;BBB;CCC");
		questionList.addAll(Arrays.asList(q1,q2,q3));
		
		QuizReq req = new QuizReq(questionnaire,questionList);
		QuizRes res = 
	}

}//
