package com.demo.trivia.dto;

public record QuestionDto(
        Long id,

        String type,
        String difficulty,
        String category,
        String question,

        String[] answers
) {
}
