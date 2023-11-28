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
import com.example.questionnaire.respository.QuestionDao;
import com.example.questionnaire.respository.QuestionnaireDao;
import com.example.questionnaire.service.ifs.QuizService;
import com.example.questionnaire.vo.QnQuVo;
import com.example.questionnaire.vo.QuizReq;
import com.example.questionnaire.vo.QuizRes;

@SpringBootTest
public class QuizServiceTest {
	
	@Autowired
	private QuizService service;
	
	@Autowired
	private QuestionnaireDao qnDao;
	
	@Autowired
	private QuestionDao qDao;
	
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
		Assert.isTrue(res.getRtnCode().getCode() == 200, "create error!");
	}
	
//    @Test
//    public void updateTest() {
//    	Questionnaire questionnaire = new Questionnaire(25,"test1", "test",false, 
//				LocalDate.of(2023, 11, 17), LocalDate.of(2023, 11, 30));
//		
//		List<Question> questionList = new ArrayList<>();
//		Question q1 = new Question(1,"test_question_111","single",false,"AAA;BBB;CCC");
//		Question q2 = new Question(2,"test_question_2","multi",false,"10;20;30;40");
//		Question q3 = new Question(3,"test_question_3","text",false,"ABC");
//		questionList.addAll(Arrays.asList(q1, q2, q3));
//		QuizReq req = new QuizReq(questionnaire,questionList);
//        QuizRes updateRes = service.update(req);
//		Assert.isTrue(updateRes.getRtnCode().getCode() == 200, "update error!");
//    }
    
    @Test
    public void insertTest() {
    	int res = qnDao.insertData( "qa_03", "qa_01 test", false, LocalDate.of(2023,11,24),LocalDate.of(2024, 01, 02) );
    	System.out.println(res);
    }
    
    @Test
    public void insertTest2() {
    	int res = qDao.insert( 1, 1, "qa_01 test", "single", false, "X;Y;Z");
    	System.out.println(res);
    }
    
    @Test
    public void updateTest() {
    	int res = qnDao.update(5, "qn_007", "qn_007_test");
    	System.out.println(res);	//�X����ơA�pid���s�b�|�L�X 0
    }
    
    @Test
    public void updateDataTest() {
    	int res = qnDao.updateData(4, "qn_099", "qn_099_test", LocalDate.of(2023, 11, 20));
    	System.out.println(res);
    }
    
    @Test
    public void selectTest1() {
//    	List<Questionnaire> res = qnDao.findByStartDate(LocalDate.of(2023, 11, 20));
//    	List<Questionnaire> res = qnDao.findByStartDate1(LocalDate.of(2023, 11, 20));
//    	List<Questionnaire> res = qnDao.findByStartDate2(LocalDate.of(2023, 11, 20));	
//    	List<Questionnaire> res = qnDao.findByStartDate3(LocalDate.of(2023, 11, 20),true);	
//    	List<Questionnaire> res = qnDao.findByStartDate4(LocalDate.of(2023, 11, 20),true);	
    	List<Questionnaire> res = qnDao.findByStartDate5(LocalDate.of(2023, 11, 20),true, 2);	
    	System.out.println(res.size());
    }
    
    @Test
    public void limitTest1() {
    	List<Questionnaire> res = qnDao.findWithLimitAndStartIndex(1, 3);
    	for(Questionnaire item :res) {
    		System.out.println(item.getId());
    	}
    	res.forEach(item ->{
    		System.out.println(item.getId());
    	});
    }
    
    @Test
    public void likeTest() {
    	List<Questionnaire> res = qnDao.searchTitleLike("test");
    	for(Questionnaire item :res) {
    		System.out.println(item.getTitle());
    	}
    }
    
    @Test
    public void regexpTest() {
    	List<Questionnaire> res = qnDao.searchDescriptionContaining("qa","qn");
    	for(Questionnaire item :res) {
    		System.out.println(item.getDescription());
    	}
    }
    
    @Test
    public void joinTest() {
    	List<QnQuVo> res = qnDao.selectJoinQnQu();
    	for(QnQuVo item : res) {
    		System.out.printf("id: %d, title: %s, qu_id: %d \n",
    				item.getId(), item.getTitle(), item.getQuId());
    	}
    }
//    
//    @Test
//    public void selectFuzzyTest() {
//    	QuizRes res = service.searchFuzzy("test", LocalDate.of(1971, 1, 1), LocalDate.of(2099, 1, 1));
//    	System.out.println(res.getQuizVoList().size());
//    }
}//
