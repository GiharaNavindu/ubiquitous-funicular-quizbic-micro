package org.gihara.micro.service;

import org.gihara.micro.Repository.QuestionRepo;
import org.gihara.micro.Repository.QuizRepo;
import org.gihara.micro.model.Question;
import org.gihara.micro.model.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class QuizService {
    @Autowired
    QuizRepo quizRepository;
    @Autowired
    QuestionRepo questionRepository;
    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        try {
            List<Question> questions =
                    questionRepository.findRandomQuestionsByCategory(category, numQ);

            if (questions.isEmpty()) {
                return new ResponseEntity<>("Not enough questions available for the given
                        category", HttpStatus.BAD_REQUEST);
            }
            Quiz quiz = new Quiz();
            quiz.setTitle(title);
            quiz.setQuestions(questions);
            quizRepository.save(quiz);
            return new ResponseEntity<>("Quiz created successfully with title: " + title,
                    HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error occurred while creating the quiz",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}