package com.quiz.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.application.model.QuestonWrapper;
import com.quiz.application.model.Response;
import com.quiz.application.service.QuizService;

@RestController
@RequestMapping("/quiz")
public class QuizController {
    
    @Autowired
    QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numsQ,@RequestParam String title){
            return quizService.createQuiz(category,numsQ,title);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<List<QuestonWrapper>> getQuizQues(@PathVariable Integer id){
           return  quizService.getQuizQuestion(id);
    }

    @PostMapping("/submit/{id}")
    public ResponseEntity<Integer> subQuiz(@PathVariable Integer id,@RequestBody List<Response> response){
        return quizService.calculateResult(id,response);
    }

}
