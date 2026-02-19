package org.gihara.micro.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "quiz_seq")
    @SequenceGenerator(name = "quiz_seq", sequenceName = "quiz_sequence", allocationSize = 1,
            initialValue = 1)
    private Integer id;
    private String title;
    @ManyToMany
    private List<Question> questions;
}
