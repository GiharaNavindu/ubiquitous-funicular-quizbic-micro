package org.gihara.micro.Repository;

import org.gihara.micro.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepo extends JpaRepository<Question, Integer> {

}
