package com.demo.trivia.dto;

import jakarta.validation.constraints.NotNull;

public record QuestionResponseDto(
        @NotNull
        QuestionDto[] questions
) {
}

