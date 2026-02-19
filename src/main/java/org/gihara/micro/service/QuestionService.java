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


}


