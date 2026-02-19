package org.gihara.micro.service;

import org.springframework.stereotype.Service;

@Service
public class QuestionService {

    public String getAllQuestions() {
        return "All questions";
    }
}

