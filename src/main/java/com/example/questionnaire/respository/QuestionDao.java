package com.example.questionnaire.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.questionnaire.entity.Question;
import com.example.questionnaire.entity.QuestionId;

@Repository
public interface QuestionDao extends JpaRepository<Question, QuestionId>{

	public void deleteAllByQnId(List<Integer>qnIdList);

	public List<Question> findByIdIn(List<Integer> quIdList);
	
}
