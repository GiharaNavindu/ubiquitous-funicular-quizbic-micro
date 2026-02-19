package org.gihara.micro.Repository;

import org.gihara.micro.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepo extends JpaRepository<Question, Integer> {

//    List<Question> findByCategory(String category);
//
//    @Query(value = "SELECT q.id FROM question q Where q.category=:category ORDER BY RANDOM() LIMIT :numQ", nativeQuery = true)
//    List<Integer> findRandomQuestionsByCategory(String category, int numQ);

    public List<Question> findByCategory(String category);

    public List<Question> findByQuestionTitle(String questionTitle);

    @Query(value = "SELECT * FROM question WHERE category = :category ORDER BY RAND() LIMIT :numQ",
            nativeQuery = true)
    public List<Question> findRandomQuestionsByCategory(@Param("category") String category,
                                                        @Param("numQ") int numQ);
}