package org.gihara.micro.controller;

import org.gihara.micro.model.Question;
import org.gihara.micro.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    // Get all questions
    @GetMapping("allQuestions")
    public List<Question> getAllQuestions() {
        // Returning the list of questions directly
        return questionService.getAllQuestions();
    }
    // Get questions by category
    @GetMapping("category/{category}")
    public List<Question> getQuestionsByCategory(@PathVariable String category) {
        // Returning the list of questions by category directly
        return questionService.getQuestionsByCategory(category);
    }
    // Add a new question
    @PostMapping("add")
    public String addQuestion(@RequestBody Question question) {
        // Returning the success or failure message directly
        return questionService.addQuestion(question);
    }
}

