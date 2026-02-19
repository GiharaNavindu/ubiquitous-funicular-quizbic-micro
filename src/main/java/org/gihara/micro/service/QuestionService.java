package org.gihara.micro.service;

import org.gihara.micro.Repository.QuestionRepo;
import org.gihara.micro.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionRepo questionRepo;

    public List<Question> getAllQuestions() {
        return questionRepo.findAll();
    }

    // Get questions by category
    public List<Question> getQuestionsByCategory(String category) {
        // Returning the result without error checking or exception handling
        return questionRepo.findByCategory(category);
    }

    // Add a question without response entity
    // Add a question without response entity
    public String addQuestion(Question question) {
        Question out = questionRepo.save(question);
        // Directly returning a success message or null check without detailed status codes
        return out != null ? "Question added successfully with id: " + out.getId() : "Failed to add question ";
    }

        // Update question without response entity
    public String updateQuestion(Integer id, Question updatedQuestion) {
        Question existingQuestion = questionRepo.findById(id).orElse(null);
        if (existingQuestion != null) {
            // Updating and saving the existing question
            existingQuestion.setQuestionTitle(updatedQuestion.getQuestionTitle());
            existingQuestion.setOption1(updatedQuestion.getOption1());
            existingQuestion.setOption2(updatedQuestion.getOption2());
            existingQuestion.setOption3(updatedQuestion.getOption3());
            existingQuestion.setOption4(updatedQuestion.getOption4());
            existingQuestion.setCategory(updatedQuestion.getCategory());
            existingQuestion.setRightAnswer(updatedQuestion.getRightAnswer());
            existingQuestion.setDifficultyLevel(updatedQuestion.getDifficultyLevel());
            questionRepo.save(existingQuestion);
            return "Question updated successfully";
        } else {
            return "Question not found";
        }
    }
    // Delete question by name
    public String deleteQuestionByName(String name) {
        List<Question> questions = questionRepo.findByQuestionTitle(name);
        if (!questions.isEmpty()) {
            questionRepo.deleteAll(questions);
            return "Questions with name '" + name + "' deleted successfully";
        } else {
            return "Question not found";
        }
    }
}


