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

    // Update a question by ID
    @PutMapping("update/{id}")
    public String updateQuestion(@PathVariable Integer id, @RequestBody Question question)
    {
        // Directly returning the update message (success or failure)
        return questionService.updateQuestion(id, question);
    }
    // Delete a question by name
    @DeleteMapping("delete/name/{name}")
    public String deleteQuestionByName(@PathVariable String name) {
        // Returning the deletion result (success or failure)
        return questionService.deleteQuestionByName(name);
    }
    // Delete a question by ID
    @DeleteMapping("delete/{id}")
    public String deleteQuestionById(@PathVariable Integer id) {
        // Returning the deletion result (success or failure)
        return questionService.deleteQuestionById(id);
    }

}

