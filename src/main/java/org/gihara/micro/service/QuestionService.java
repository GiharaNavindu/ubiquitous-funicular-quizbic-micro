package org.gihara.micro.service;

import org.gihara.micro.Repository.QuestionRepo;
import org.gihara.micro.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionRepo questionRepo;

    // Get all questions
    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionRepo.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    // Get questions by category
    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try {
            return new ResponseEntity<>(questionRepo.findByCategory(category), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    // Add a new question
    public ResponseEntity<String> addQuestion(Question question) {
        Question out = questionRepo.save(question);
        if (out != null)
            return new ResponseEntity<>("Question added successfully with id: " + out.getId(),
                    HttpStatus.CREATED);
        else
            return new ResponseEntity<>("Something went wrong", HttpStatus.NO_CONTENT);
    }
    // Update an existing question by ID
    public ResponseEntity<String> updateQuestion(Integer id, Question updatedQuestion) {
        try {
            Question existingQuestion = questionRepo.findById(id).orElse(null);
            if (existingQuestion != null) {
                // Update question details
                existingQuestion.setQuestionTitle(updatedQuestion.getQuestionTitle());
                existingQuestion.setOption1(updatedQuestion.getOption1());
                existingQuestion.setOption2(updatedQuestion.getOption2());
                existingQuestion.setOption3(updatedQuestion.getOption3());
                existingQuestion.setOption4(updatedQuestion.getOption4());
                existingQuestion.setCategory(updatedQuestion.getCategory());
                existingQuestion.setRightAnswer(updatedQuestion.getRightAnswer());
                existingQuestion.setDifficultyLevel(updatedQuestion.getDifficultyLevel());

                questionRepo.save(existingQuestion);
                return new ResponseEntity<>("Question updated successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Question not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
    }
    // Delete a question by name
    public ResponseEntity<String> deleteQuestionByName(String name) {
        try {
            List<Question> questions = questionRepo.findByQuestionTitle(name);
            if (!questions.isEmpty()) {
                questionRepo.deleteAll(questions);
                return new ResponseEntity<>("Questions with name '" + name + "' deleted successfully",
                        HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Question with name '" + name + "' not found",
                        HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
    }
    // Delete a question by ID
    public ResponseEntity<String> deleteQuestionById(Integer id) {
        try {
            if (questionRepo.existsById(id)) {
                questionRepo.deleteById(id);
                return new ResponseEntity<>("Question deleted successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Question with ID " + id + " not found",
                        HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
    }
}


