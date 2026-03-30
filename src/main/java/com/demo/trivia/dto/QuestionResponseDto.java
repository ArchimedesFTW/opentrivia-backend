package com.demo.trivia.dto;

import com.demo.trivia.dto.QuestionDto;

public record QuestionResponseDto(
        QuestionDto[] questions
) {
}

