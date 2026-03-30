package com.demo.trivia.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    public String type;
    private String difficulty;
    private String category;
    private String question;

    private String correctAnswer;

    @ElementCollection
    @CollectionTable(
            name = "question_incorrect_answers",
            joinColumns = @JoinColumn(name = "question_id")
    )
    @Column(name = "answer")
    private List<String> incorrectAnswers = new ArrayList<>();

    protected Question() {}

    public Question(String type, String difficulty, String category, String question,
                    String correctAnswer, String[] incorrectAnswers) {
        this.type = type;
        this.difficulty = difficulty;
        this.category = category;
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.incorrectAnswers = Arrays.stream(incorrectAnswers).toList();
    }

    public Long getId() { return id; }
    public String getType() { return type; }
    public String getDifficulty() { return difficulty; }
    public String getCategory() { return category; }
    public String getQuestion() { return question; }
    public String getCorrectAnswer() { return correctAnswer; }
    public String[] getIncorrectAnswers() { return incorrectAnswers.toArray(String[]::new); }
}

