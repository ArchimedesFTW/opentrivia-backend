package com.demo.trivia.dto;

import jakarta.validation.constraints.NotNull;

public record AnswerResponseDto(
    @NotNull
    SolutionDto[] solutions
) {
}
