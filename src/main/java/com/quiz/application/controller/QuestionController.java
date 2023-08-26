package com.quiz.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.application.model.Question;
import com.quiz.application.service.questionSeervice;

@RestController
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    questionSeervice qs;

    @GetMapping("/allQuestions")
    public ResponseEntity<List<Question>> AllQuestions(){
        return qs.getAllQuestions();
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Question>> getQuesByCategory(@PathVariable String category){
        return qs.getQuesByCategory(category);
    }
     
    @PostMapping("/add")
    public ResponseEntity<String> addQuestion(@RequestBody Question q){
        return qs.addQuestion(q);

    }

    @PutMapping("/allQuestions/{id}")
    public ResponseEntity<String> putQuestion(@RequestBody Question q,@PathVariable Integer id ){
        return qs.putQuestion(q,id);
    }

    @DeleteMapping("/allQuestions/{id}")
    public ResponseEntity<String> delQuestion(@PathVariable Integer id){
        return qs.delQuestion(id);
    }



}
