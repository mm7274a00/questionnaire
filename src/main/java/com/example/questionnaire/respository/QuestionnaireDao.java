package com.example.questionnaire.respository;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.questionnaire.entity.Questionnaire;

@Repository
public interface QuestionnaireDao extends JpaRepository<Questionnaire, Integer>{
	
	public List<Questionnaire> findByIdIn(List<Integer> idList);

	public List<Questionnaire> findByTitleContainingAndStartDateGreaterThanEqualAndEndDateLessThanEqual(String title,LocalDate startDate,LocalDate endDate);

	public Questionnaire findTopByOrderByIdDesc();

	public List<Questionnaire> findByTitleContainingAndStartDateGreaterThanEqualAndEndDateLessThanEqualAndPublishedIsTrue(String title,
			LocalDate startDate, LocalDate endDate);
	
	@Modifying
	@Transactional
	@Query(value = "insert into questionnaire(title, description, is_published, start_Date, end_Date)" 
			+ "values(:title,:desp,:isPublished,:startDate,:endDate)", nativeQuery = true )
	public int insert(
			@Param("title")String title,
			@Param("desp")String description ,
			@Param("isPublished")boolean isPublished, 
			@Param("startDate")LocalDate startDate ,
			@Param("endDate")LocalDate endDate);
	
	@Modifying
	@Transactional
	@Query(value = "insert into questionnaire(title, description, is_published, start_Date, end_Date) " 
			+ " values(?1, ?2, ?3, ?4, ?5)", nativeQuery = true )
	public int insertData(
			String title,
			String description ,
			boolean isPublished, 
			LocalDate startDate ,
			LocalDate endDate);
	
	//========================
	//update
	@Modifying
	@Transactional
	@Query(value = "update questionnaire set title = :title, description = :desp"
			+ " where id = :id",nativeQuery = true)	//���g nativeQuery ���P�� nativeQuery = false	//�n�[where�A���[�|����
	public int update(
			@Param("id")int id,
			@Param("title")String title, 
			@Param("desp")String description);
	/**
	 * 
	 * ���g nativeQuery ���P�� nativeQuery = false
	 * �y�k�����W�٭n�ܦ� entity �� class �W��:���W�٭n�ܦ��ݩʦW��	
	 */
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "update questionnaire set title = :title, description = :desp, start_Date = :startDate "
			+ " where id = :id",nativeQuery = true)
	public int updateData(
			@Param("id")int id,
			@Param("title")String title, 
			@Param("desp")String description,
			@Param("startDate")LocalDate startDate);
	
	//=========================================
	//select
	@Query(value = "select * from questionnaire "
			+ " where start_date > :startDate", nativeQuery = true)
	public List<Questionnaire> findByStartDate(@Param("startDate")LocalDate startDate);
	
	@Query(value = "select new Questionnaire(id, title, description, published, startDate, endDate )"
			+ " from Questionnaire where startDate > :startDate")
	public List<Questionnaire> findByStartDate1(@Param("startDate")LocalDate startDate);
	
	//	nativeQuery = false, select�����n�ϥΫغc��k�A�B Entity ���]�n���������غc��k�A�_�h�|����
	@Query(value = "select new Questionnaire(id, title, published )"
			+ " from Questionnaire where startDate > :startDate")
	public List<Questionnaire> findByStartDate2(@Param("startDate")LocalDate startDate);
	
	//	�ϥΧO�W�A�y�k as �O�W
	@Query(value = "select qu from Questionnaire as qu "
			+ " where startDate > :startDate or published = :isPublished")
	public List<Questionnaire> findByStartDate3(
			@Param("startDate")LocalDate startDate, 
			@Param("isPublished")boolean published);
	
	//order by
	@Query(value = "select qu from Questionnaire as qu "
			+ " where startDate > :startDate or published = :isPublished order by id desc")
	public List<Questionnaire> findByStartDate4(
			@Param("startDate")LocalDate startDate, 
			@Param("isPublished")boolean published);
	
	//order by + limit(limit�y�k�u��ϥΦb nativeQuery = true)
	@Query(value = "select * from Questionnaire as qu "
			+ " where start_date > :startDate or is_published = :isPublished order by id desc limit :num "
			, nativeQuery = true)
	public List<Questionnaire> findByStartDate5(
			@Param("startDate")LocalDate startDate, 
			@Param("isPublished")boolean published,
			@Param("num")int limitNum);

}//
