package com.exam.controller;

import com.exam.model.exam.Quiz;
import com.exam.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/quiz")
@CrossOrigin("*")
public class QuizController {
    @Autowired
    private QuizService quizService;

    //ADD Quiz
    @PostMapping("/")
    public ResponseEntity<Quiz> addQuiz(@RequestBody Quiz quiz) {
        return  ResponseEntity.ok(this.quizService.addQuiz(quiz));
    }
    //UPDATE QUIZ
    @PutMapping("/")
    public ResponseEntity<Quiz> updateQuiz(@RequestBody Quiz quiz) {
        return  ResponseEntity.ok(this.quizService.updateQuiz(quiz));
    }

    // GET ALL QUIZZES
    @GetMapping("/")
    public ResponseEntity<Set<Quiz>> getAllQuiz() {
        return ResponseEntity.ok(this.quizService.getAllQuizzes());
    }

    // GET QUIZ BY ID
    @GetMapping("/{quizId}")
    public Quiz getQuiz(@PathVariable("quizId") Long quizId) {
        return this.quizService.getQuiz(quizId);
    }

    // DELETE QUIZ BY ID
    @DeleteMapping("/{quizId}")
    public void deleteQuiz(@PathVariable("quizId") Long quizId) {
        this.quizService.deleteQuiz(quizId);
    }

}
