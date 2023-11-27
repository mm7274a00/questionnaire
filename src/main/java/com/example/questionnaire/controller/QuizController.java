package com.example.questionnaire.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.questionnaire.service.ifs.QuizService;
import com.example.questionnaire.vo.QuizReq;
import com.example.questionnaire.vo.QuizRes;
import com.example.questionnaire.vo.QuizSearchReq;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class QuizController {
	
	@Autowired
	private QuizService service;
	
	@PostMapping(value = "api/quiz/create")	//提供http方法，定義外部使用之url
	public QuizRes create(@RequestBody QuizReq req) {	
		return service.create(req);
	}
//	
//	@GetMapping(value = "api/quiz/search")
//	public QuizRes search(@RequestBody(required = false) QuizSearchReq req) {
//		String title = StringUtils.hasText(req.getTitle())? req.getTitle():"";
//		LocalDate startDate = req.getStartDate() != null? req.getStartDate() : LocalDate.of(1971, 1,1);
//		LocalDate endDate = req.getEndDate() !=null? req.getEndDate() :LocalDate.of(2099, 12,31);
//		return service.search(title, startDate, endDate);
//	}
	@GetMapping(value = "api/quiz/search")
	public QuizRes search(
	    @RequestParam(required = false) String title,
	    @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
	    @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
	    
	    title = StringUtils.hasText(title) ? title : "";
	    startDate = startDate != null ? startDate : LocalDate.of(1971, 1, 1);
	    endDate = endDate != null ? endDate : LocalDate.of(2099, 12, 31);
	    
	    return service.search(title, startDate, endDate);
	}
	
	
	
	@PostMapping(value = "api/quiz/update")	
	public QuizRes update(@RequestBody QuizReq req) {	
		return service.update(req);
	}
}//
