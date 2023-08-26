package com.quiz.application.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quiz.application.model.Quiz;


public interface QuizDAo extends JpaRepository<Quiz,Integer> {

}
