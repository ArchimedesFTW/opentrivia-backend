package com.demo.trivia.dto;

import jakarta.validation.constraints.NotNull;

public record QuestionDto(
        @NotNull
        Long id,

        @NotNull
        String type,

        @NotNull
        String difficulty,

        @NotNull
        String category,

        @NotNull
        String question,

        @NotNull
        String[] answers
) {
}
