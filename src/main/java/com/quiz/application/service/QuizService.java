package com.quiz.application.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.quiz.application.dao.QuestionDao;
import com.quiz.application.dao.QuizDAo;
import com.quiz.application.model.Question;
import com.quiz.application.model.QuestonWrapper;
import com.quiz.application.model.Quiz;
import com.quiz.application.model.Response;

@Service
public class QuizService {

    @Autowired
    QuizDAo quizDAo;

    @Autowired
    QuestionDao qquestionDao;

    public ResponseEntity<String> createQuiz(String category, int numsQ, String title) {

        List<Question>  questions = qquestionDao.findRandomQues(category,numsQ);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDAo.save(quiz);
        return new ResponseEntity<>("Success",HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestonWrapper>> getQuizQuestion(Integer id) {
        Optional<Quiz> quiz = quizDAo.findById(id);
        List<Question> quesFromDb = quiz.get().getQuestions();
        List<QuestonWrapper> quesForUser = new ArrayList<>();
        for( Question q: quesFromDb){
            QuestonWrapper qw = new QuestonWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(),q.getOption2(), q.getOption3(), q.getOption4());
            quesForUser.add(qw);
        }

        return new ResponseEntity<List<QuestonWrapper>>(quesForUser, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Quiz quiz = quizDAo.findById(id).get();
        List<Question> questions = quiz.getQuestions();
        int right=0,i=0;
        for(Response response:responses){
            if(response.getResponse().equals(questions.get(i).getRightAns())){
                right++;
            }
                i++;
        }
        return new ResponseEntity<Integer>(right, HttpStatus.OK);
    }

    
}
