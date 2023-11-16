package com.example.questionnaire.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.questionnaire.entity.Questionnaire;

@Repository
public interface QuestionnaireDao extends JpaRepository<Questionnaire, Integer>{
	
	/**
	 * ���o�̷s�@����ơG����������ƫ�˧ǡA�̷s��������Ʒ|�ܦ��Ĥ@��
	 */
	public Questionnaire findTopByOrderByIdDesc();
	
}
