package com.example.questionnaire.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.questionnaire.service.ifs.QuizService;
import com.example.questionnaire.vo.QuizReq;
import com.example.questionnaire.vo.QuizRes;
import com.example.questionnaire.vo.QuizSearchReq;

@RestController
@CrossOrigin
public class QuizController {
	
	@Autowired
	private QuizService service;
	
	@PostMapping(value = "api/quiz/create")	//提供http方法，定義外部使用之url
	public QuizRes create(@RequestBody QuizReq req) {	
		return service.create(req);
	}
	
	@GetMapping(value = "api/quiz/search")
	public QuizRes search(@RequestBody QuizSearchReq req) {
		return service.search(req.getTitle(), req.getStartDate(), req.getEndDate());
	}
}//
