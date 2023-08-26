package com.quiz.application.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.quiz.application.model.Question;

import java.util.List;



public interface QuestionDao extends JpaRepository<Question,Integer>{

 List<Question> findByCategory(String category);


 @Query(value = "SELECT * from question as q where q.category=:category ORDER BY RAND() LIMIT :numsQ",nativeQuery = true)
List<Question> findRandomQues(String category, int numsQ);


    
}
