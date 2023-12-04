package com.example.questionnaire.service.Impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.questionnaire.constants.RtnCode;
import com.example.questionnaire.entity.Question;
import com.example.questionnaire.entity.Questionnaire;
import com.example.questionnaire.respository.QuestionDao;
import com.example.questionnaire.respository.QuestionnaireDao;
import com.example.questionnaire.service.ifs.QuizService;
import com.example.questionnaire.vo.QnQuVo;
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
	public QuizRes create(QuizReq req) {	//撱箇��
		QuizRes checkResult = checkParam(req);
		if(checkResult != null) {
			return checkResult;
		}
		int quId = qnDao.save(req.getQuestionnaire()).getId();
		List<Question> quList = req.getQuestionList();
		if(quList.isEmpty()) {
			return new QuizRes(RtnCode.SUCCESSFUL);
		}
//		int quId = qnDao.findTopByOrderByIdDesc().getId();
		for(Question qu : quList) {
			qu.setQnId(quId);
		}
		quDao.saveAll(quList);
		return new QuizRes(RtnCode.SUCCESSFUL);
		}
	
	@Transactional	
	@Override
	public QuizRes update(QuizReq req) {	//���耨���
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
		//collect delected_question_id
		List<Integer> deletedQuIdList = new ArrayList<>();
		for(Question qu : req.getDeleteQuestionList()) {
			deletedQuIdList.add(qu.getQuId());
		}
		Questionnaire qn = qnOp.get();
		//�隞乩耨����辣嚗�
		//1.撠�撣�ublished == flase嚗隞乩耨�
		//2. 撌脩撣������脰��s_published == true + �������� start_date
		if(!qn.isPublished() || (qn.isPublished() && LocalDate.now().isBefore(qn.getStartDate()))) {
			qnDao.save(req.getQuestionnaire());
			quDao.saveAll(req.getQuestionList());
			if(!deletedQuIdList.isEmpty()) {
				quDao.deleteAllByQnIdAndQuIdIn(qn.getId(),deletedQuIdList);
			}
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
			if(qu.getQuId() <= 0 || !StringUtils.hasText(qu.getqTitle())
				|| !StringUtils.hasText(qu.getOptionType())||!StringUtils.hasText(qu.getOption()) ) {
				return new QuizRes(RtnCode.QUESTIONNAIRE_ID_PARAM_ERROR);
			}
		}
		return null;
	}
	
//	private QuizRes checkQuestionnaireId(QuizReq req){
//		if(req.getQuestionnaire().getId() <= 0 ) {
//			return new QuizRes(RtnCode.QUESTIONNAIRE_ID_PARAM_ERROR);	
//		}
//		List<Question> quList = req.getQuestionList();
//		for(Question qu: quList) {
//			if(qu.getQnId()	!= req.getQuestionnaire().getId()) {
//				return new QuizRes(RtnCode.QUESTIONNAIRE_ID_PARAM_ERROR);
//			}
//		}
//		List<Question> quDelList = req.getDeleteQuestionList();
//		for(Question qu : quDelList) {
//			if(qu.getQnId() != req.getQuestionnaire().getId()) {
//				return new QuizRes(RtnCode.QUESTIONNAIRE_ID_PARAM_ERROR);
//			}
//		}
//		return null;
//	}
	
	private QuizRes checkQuestionnaireId(QuizReq req) {
	    System.out.println("Questionnaire ID: " + req.getQuestionnaire().getId()); // 瑼Ｘ��ID
	    if (req.getQuestionnaire().getId() <= 0) {
	        System.out.println("Error: Invalid Questionnaire ID");
	        return new QuizRes(RtnCode.QUESTIONNAIRE_ID_PARAM_ERROR);
	    }

	    List<Question> quList = req.getQuestionList();
	    Questionnaire qn = req.getQuestionnaire();
	    for (Question qu : quList) {
	        System.out.println("Question ID: " + qn.getId()); // 瑼Ｘ瘥�����ID

	        if (qn.getId() != req.getQuestionnaire().getId()) {
	        	System.out.println(qn.getId());
	        	System.out.println(qu.getQnId());
	        	System.out.println(req.getQuestionnaire().getId());
	            System.out.println("Error: Question ID does not match Questionnaire ID");
	            return new QuizRes(RtnCode.QUESTIONNAIRE_ID_PARAM_ERROR);
	        }
	    }    
	    for (Question qu : req.getQuestionList()) {
	        qu.setQnId(req.getQuestionnaire().getId()); // 雿輻敶����d�霈曄蔭�憸�nId
	    }
	    quDao.saveAll(req.getQuestionList());

	    List<Question> quDelList = req.getDeleteQuestionList();
	    for (Question qu : quDelList) {
	        System.out.println("Deleted Question ID: " + qn.getId()); // 瑼Ｘ瘥��������ID
        	System.out.println(qn.getId());
        	System.out.println("33" + qu.getQnId());
        	System.out.println(req.getQuestionnaire().getId());
	        if (qn.getId() != req.getQuestionnaire().getId()) {
	            System.out.println("Error: Deleted Question ID does not match Questionnaire ID22");
	            return new QuizRes(RtnCode.QUESTIONNAIRE_ID_PARAM_ERROR);
	        }
	    }

	    return null;
	}
	
	@Transactional
	@Override	
	public QuizRes deleteQuestionnaire(List<Integer>qnIdList) {	//��憭����
		List<Questionnaire> qnList = qnDao.findByIdIn(qnIdList);
		List<Integer> idList = new ArrayList<>();
		for(Questionnaire qn:qnList) {
			if(!qn.isPublished() || qn.isPublished() && LocalDate.now().isBefore(qn.getStartDate())) {
//				qnDao.deleteById(qn.getId());
				idList.add(qn.getId());
			}
		}
		if(!idList.isEmpty()) {	//瑼Ｘ�摰寞��蝛綽��蝛箇�����
			qnDao.deleteAllById(idList);	//��"��"
			quDao.deleteAllByQnIdIn(idList);	//��������"憿"
		}
		return new QuizRes(RtnCode.SUCCESSFUL);
	}
	
	@Transactional
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
	
//	@Cacheable(cacheNames = "search", 
//			// key = #title_#startDate_#endDate
//			// key = "test_2023-11-10_2023-11-31"
//			key = "#title.concat('_').concat(#startDate.toString()).concat('_').concat(#endDate.toString())", 
//			unless = "#result.rtnCode.code != 200") 
	@Override
	public QuizRes search(String title, LocalDate startDate, LocalDate endDate) {
		title = StringUtils.hasText(title)? title:"";	//銝��神瘜����椰����撘���摰孵��璅���摰寞���蝛箏�葡
		startDate = startDate != null? startDate : LocalDate.of(1971, 1,1);
		endDate = endDate !=null? endDate :LocalDate.of(2099, 12,31);	//甇支���隞乩�神瘜�
//		if(StringUtils.hasText(title)) {
//			title = "";	//憒頛詨璅��摮��蝛箏�葡嚗�����������
//		}
//		if(startDate == null) {
//			startDate = LocalDate.of(1971, 1,1);	//憒頛詨�����策銝���������
//		}
//		if(endDate == null) {
//			endDate = LocalDate.of(2099, 12,31);	//憒頛詨�����策銝���������
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
			boolean isPublished) {	//��皜
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

	@Transactional
	@Override
	public QuestionRes searchQuestionList(int qnId) {
		if(qnId <= 0) {
			return new QuestionRes(null,RtnCode.QUESTIONNAIRE_ID_PARAM_ERROR);
		}
		List<Question> quList = quDao.findAllByQnIdIn(Arrays.asList(qnId));
		return new QuestionRes(quList,RtnCode.SUCCESSFUL);
	}

	@Override
	public QuizRes searchFuzzy(String title, LocalDate startDate, LocalDate endDate) {
		// TODO Auto-generated method stub
		return null;
	}


//	@Override
//	public QuizRes searchFuzzy(String title, LocalDate startDate, LocalDate endDate) {
//		// TODO Auto-generated method stub
//		List<QnQuVo> res = null;
//		return new QuizRes(null, res, RtnCode.SUCCESSFUL);
//	}
	
	}//
