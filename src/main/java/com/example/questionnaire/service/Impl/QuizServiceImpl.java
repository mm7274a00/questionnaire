package com.example.questionnaire.service.Impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
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
import com.example.questionnaire.vo.QuestionRes;
import com.example.questionnaire.vo.QuestionnaireRes;
import com.example.questionnaire.vo.QuizReq;
import com.example.questionnaire.vo.QuizRes;
import com.example.questionnaire.vo.QuizVo;

@Service
public class QuizServiceImpl implements QuizService{
	
	@Autowired 
	private QuestionnaireDao qnDao;

	@Autowired 
	private QuestionDao quDao;
	
	@Transactional
	@Override
	public QuizRes create(QuizReq req) {	//�إ߰ݨ�
		QuizRes checkResult = checkParam(req);
		if(checkResult != null) {
			return checkResult;
		}
		qnDao.save(req.getQuestionnaire());
		List<Question> quList = req.getQuestionList();
		if(quList.isEmpty()) {
			return new QuizRes(RtnCode.SUCCESSFUL);
		}
//		int quId = qnDao.save(getQuestionnaire()).getId();
		int quId = qnDao.findTopByOrderByIdDesc().getId();
		for(Question qu : quList) {
			qu.setQuId(quId);
		}
		quDao.saveAll(quList);
		return new QuizRes(RtnCode.SUCCESSFUL);
		}
	
	@Transactional	
	@Override
	public QuizRes update(QuizReq req) {	//��s�B�ק�ݨ�
			QuizRes checkResult = checkParam(req);
			if(checkResult != null){
			return checkResult;
		}
		checkResult = checkQuestionnaireId(req);
		if(checkResult != null){
			return checkResult;
		}
		Optional<Questionnaire> qnOp = qnDao.findById(req.getQuestionnaire().getId());
		if(qnOp.isEmpty()) {
			return new QuizRes(RtnCode.QUESTIONNAIRE_ID_NOT_FOUND);
		}
		Questionnaire qn = qnOp.get();
		//�i�H�ק諸����G
		//1.�|���o���Gpublished == flase�A�i�H�ק�
		//2. �w�o�����|���}�l�i��Gis_published == true + ��e�ɶ������p�� start_date
		if(!qn.isPublished() || qn.isPublished() && LocalDate.now().isBefore(qn.getStartDate())) {
			qnDao.save(req.getQuestionnaire());
			quDao.saveAll(req.getQuestionList());
			return new QuizRes(RtnCode.SUCCESSFUL);
		}
		return new QuizRes(RtnCode.UPDATE_ERROR);
	}

	private QuizRes checkParam(QuizReq req) {
		Questionnaire qn = req.getQuestionnaire();
		if(!StringUtils.hasText(qn.getTitle()) || !StringUtils.hasText(qn.getDescription())
				|| qn.getStartDate() == null || qn.getEndDate() == null
				|| qn.getStartDate().isAfter(qn.getEndDate())){
			return new QuizRes(RtnCode.QUESTIONNAIRE_PARAM_ERROR);
		}
		List<Question> quList = req.getQuestionList();
		for(Question qu:quList) {
			if(qu.getQnId() <= 0 || !StringUtils.hasText(qu.getqTitle())
				|| !StringUtils.hasText(qu.getOptionType())||!StringUtils.hasText(qu.getOption()) ) {
				return new QuizRes(RtnCode.QUESTIONNAIRE_ID_PARAM_ERROR);
			}
		}
		return null;
	}
	
	private QuizRes checkQuestionnaireId(QuizReq req){
		if(req.getQuestionnaire().getId() <= 0 ) {
			return new QuizRes(RtnCode.QUESTIONNAIRE_PARAM_ERROR);
		}
		List<Question> quList = req.getQuestionList();
		for(Question qu: quList) {
		if(qu.getQnId()	!= req.getQuestionnaire().getId()) {
			return new QuizRes(RtnCode.QUESTIONNAIRE_ID_PARAM_ERROR);
		}
	}
		List<Question> quDeList = req.getDeleteQuestionList();
		for(Question qu: quDeList) {
		if(qu.getQnId()	!= req.getQuestionnaire().getId()) {
			return new QuizRes(RtnCode.QUESTIONNAIRE_ID_PARAM_ERROR);
		}
		return null;
	}
	
	@Override	
	public QuizRes deleteQuestionnaire(List<Integer>qnIdList) {	//�R���h�����
		List<Questionnaire> qnList = qnDao.findByIdIn(qnIdList);
		List<Integer> idList = new ArrayList<>();
		for(Questionnaire qn:qnList) {
			if(!qn.isPublished() || qn.isPublished() && LocalDate.now().isBefore(qn.getStartDate())) {
//				qnDao.deleteById(qn.getId());
				idList.add(qn.getId());
			}
		}
		if(!idList.isEmpty()) {	//�ˬd���e�O�_���šA�p�O�Ū��h���ΧR��
			qnDao.deleteAllById(idList);	//�R��"�ݨ�"
			quDao.deleteAllByQnIdIn(idList);	//�R���ݨ�����"�D��"
		}
		return new QuizRes(RtnCode.SUCCESSFUL);
	}
	
	@Override
	public QuizRes deleteQuestion(int qnId,List<Integer>quIdList){
		//							1.				1,2,3
		Optional<Questionnaire>qnOp = qnDao.findById(qnId);
		if(qnOp.isEmpty()) {	
			return new QuizRes(RtnCode.SUCCESSFUL);
		}
		Questionnaire qn = qnOp.get();
			if(!qn.isPublished() || qn.isPublished() && LocalDate.now().isBefore(qn.getStartDate())) {
				quDao.deleteAllByQnIdAndQuIdIn(qnId,quIdList);
		}
		return new QuizRes(RtnCode.SUCCESSFUL);

	}

	@Override
	public QuizRes search(String title, LocalDate startDate, LocalDate endDate) {
		title = StringUtils.hasText(title)? title:"";	//�T�����g�k�G�ݸ������䬰�P�_���A�p�����e�h�^�Ǽ��D�A�L���e���D����Ŧr��
		startDate = startDate != null? startDate : LocalDate.of(1971, 1,1);
		endDate = endDate !=null? endDate :LocalDate.of(2099, 12,31);	//���T�浥��H�U�g�k
//		if(StringUtils.hasText(title)) {
//			title = "";	//�p����J���D����r�A�h���Ŧr��A�~�༴�o�X�Ҧ����
//		}
//		if(startDate == null) {
//			startDate = LocalDate.of(1971, 1,1);	//�p����J�ɶ��A�h���@�ӶW���}�l�ɶ�
//		}
//		if(endDate == null) {
//			endDate = LocalDate.of(2099, 12,31);	//�p����J�ɶ��A�h���@�ӶW�ߵ����ɶ�
//		}
		List<Questionnaire> qnList = qnDao.findByTitleContainingAndStartDateGreaterThanEqualAndEndDateLessThanEqual(title, startDate, endDate);
		List<Integer> qnIds = new ArrayList<>();
		for(Questionnaire qu: qnList) {
			qnIds.add(qu.getId());
		}
		List<Question> quList = quDao.findAllByQnIdIn(qnIds);
		List<QuizVo> quizVoList = new ArrayList<>();
		for(Questionnaire qn : qnList) {
			QuizVo vo = new QuizVo();
			vo.setQuestionnaire(qn);
			List<Question> questionList = new ArrayList<>();
			for(Question qu:quList) {
				if(qu.getQnId() == qn.getId()) {
					questionList.add(qu);
				}
			}
			vo.setQuestionList(questionList);
			quizVoList.add(vo);
		}
		return new QuizRes(quizVoList,RtnCode.SUCCESSFUL);
	}

	@Override
	public QuestionnaireRes searchQuestionnaireList(String title, LocalDate startDate, LocalDate endDate, 
			boolean isPublished) {	//�ݨ��M��
		title = StringUtils.hasText(title)? title:"";
		startDate = startDate != null? startDate : LocalDate.of(1971, 1,1);
		endDate = endDate !=null? endDate :LocalDate.of(2099, 12,31);
		List<Questionnaire> qnList = qnDao.findByTitleContainingAndStartDateGreaterThanEqualAndEndDateLessThanEqual(title, startDate, endDate);
//		List<Questionnaire> qnList = qnDao.findByTitleContainingAndStartDateGreaterThanEqualAndEndDateLessThanEqual(title, startDate, endDate);
		List<Questionnaire> newQnList = new ArrayList<>();
		for(Questionnaire qn:qnList) {
			if(qn.isPublished() == isPublished) {
				newQnList.add(qn);
			}
		}
		return new QuestionnaireRes(qnList,RtnCode.SUCCESSFUL);
	}

	@Override
	public QuestionRes searchQuestionList(int qnId) {
		if(qnId <= 0) {
			return new QuestionRes(null,RtnCode.QUESTIONNAIRE_ID_PARAM_ERROR);
		}
		List<Question> quList = quDao.findAllByQnIdIn(Arrays.asList(qnId));
		return new QuestionRes(quList,RtnCode.SUCCESSFUL);
	}
	
	}//
