package com.quiz.application.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.quiz.application.dao.QuestionDao;
import com.quiz.application.model.Question;

@Service
public class questionSeervice {
    @Autowired
    QuestionDao questionDao;

    public   ResponseEntity<List<Question>> getAllQuestions() {
      try{
      return new ResponseEntity<List<Question>>(questionDao.findAll(),HttpStatus.OK);
      } catch (Exception e) {
        e.printStackTrace();
      }
      return new ResponseEntity<List<Question>>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuesByCategory(String category) {
         try{
      return new ResponseEntity<List<Question>>(questionDao.findByCategory(category),HttpStatus.OK);
      } catch (Exception e) {
        e.printStackTrace();
      }
      return new ResponseEntity<List<Question>>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addQuestion(Question q) {
        questionDao.save(q);
        return new ResponseEntity<String>("success",HttpStatus.CREATED) ;
    }

    public ResponseEntity<String> delQuestion(Integer id) {
     questionDao.deleteById(id);
     return new ResponseEntity<String>("success",HttpStatus.OK) ;
    }

    public ResponseEntity<String> putQuestion(Question q, Integer id) {
      questionDao.save(q);
           return new ResponseEntity<String>("success",HttpStatus.OK) ;

    }
    
}
