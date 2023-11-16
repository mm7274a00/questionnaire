package com.example.questionnaire.service.Impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.questionnaire.constants.RtnCode;
import com.example.questionnaire.entity.Question;
import com.example.questionnaire.entity.Questionnaire;
import com.example.questionnaire.respository.QuestionDao;
import com.example.questionnaire.respository.QuestionnaireDao;
import com.example.questionnaire.service.ifs.QuizService;
import com.example.questionnaire.vo.QuizReq;
import com.example.questionnaire.vo.QuizRes;

@Service
public class QuizServiceImpl implements QuizService{
	
	@Autowired 
	private QuestionnaireDao qnDao;
	
	@Autowired 
	private QuestionDao quDao;

	@Transactional
	@Override
	public QuizRes create(QuizReq req) {
		QuizRes checkResult = checkParam(req);
		if(checkResult != null) {
			return checkResult;
		}
		qnDao.save(req.getQuestionnaire());
		List<Question> quList = req.getQuestionList();
		if(quList.isEmpty()) {
			return new QuizRes(RtnCode.SUCCESSFUL);
		}
		int quId = qnDao.findTopByOrderByIdDesc().getId();
		for(Question qu : quList) {
			qu.setId(quId);
		}
		quDao.saveAll(quList){
			qu.setQnId(quId);
		}
		
		@Override
		public QuizRes update(QuizReq req) {
		QuizRes checkResult = checkParam(req);
		if(checkResult != null){
			return checkResult;
		}
		checkResult = checkQuestionnaireId(req);
		if(checkResult != null);{
			return checkResult;
		}
		Optional<Questionnaire>qnOp = qnDao.findById(req.getQuestionnaire().getid());
		if(qnOp.isEmpty()) {
			return new QuizRes(RtnCode.QUESTIONNAIRE_ID_NOT_FOUND);
		}
		Questionnaire qn = qnOp.get();
		//1. is_published == flase，可以修改
		//2. 已發布但尚未開始進行：is_published == true + 當前時間必須小於 start_date
		if(!qn.isPublished() || qn.isPublished() && LocalDate.now().isBefore(qn.getStartDate())) {
			qnDao.save(req.getQuestionnaire());
			quDao.saveAll(req.getQuestionList());
			return new QuizRes(RtnCode.SUCCESSFUL);
		}
		return new QuizRes(RtnCode.UPDATE_ERROR);
	}

	public QuizRes checkParam(QuizReq req) {
		Questionnaire qn = req.getQuestionnaire();
		if(StringUtils.hasText(qn.getTitle())||StringUtils.hasText(qn.getDescrition())
				|| qn.getStartDate() == null || qn.getEndDate() == null
				|| qn.getStartDate().isAfter(qn.getEndDate())){
			return new QuizRes(RtnCode.QUESTIONNAIRE_PARAM_ERROR);
		}
		List<Question> quList = req.getQuestionList();
		for(Question qu:quList) {
		if(qu.getId() <= 0 || !StringUtils.hasText(qu.getqTitle())
				|| !StringUtils.hasText(qu.getOptionType())||!StringUtils.hasText(qu.getOption()) ) {
				return new QuizRes(RtnCode.QUESTIONNAIRE_ID_PARAM_ERROR);
			}
		}
		return null;
	}
	
	private QuizRes checkQuestionnaireId(QuizReq req){
		if(req.getQuestionnaire.getId() <= 0 ) {
			return new QuizRes(RtnCode.QUESTIONNAIRE_PARAM_ERROR);
		}
		List<Question> quList = req.getQuestionList();
		for(Question qu: quList) {
		if(qu.getQnId()	!= req.getQuestionnaire().getId()) {
			return new QuizRes(RtnCode.QUESTIONNAIRE_ID_PARAM_ERROR);
		}
	}return null;
	}
		
}//
