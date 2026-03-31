package com.demo.trivia.controller;

import com.demo.trivia.dto.AnswerDto;
import com.demo.trivia.dto.AnswerRequestDto;
import com.demo.trivia.dto.AnswerResponseDto;
import com.demo.trivia.dto.QuestionResponseDto;
import com.demo.trivia.service.QuestionCacheService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class AnswerController {
    private final QuestionCacheService questionCacheService;

    public AnswerController(QuestionCacheService questionCacheService) {
        this.questionCacheService = questionCacheService;
    }

    @PostMapping(value = "/checkanswers", consumes = MediaType.APPLICATION_JSON_VALUE)
    public AnswerResponseDto checkAnswers(@Valid @RequestBody AnswerRequestDto answers) {

        return this.questionCacheService.checkAnswers(answers);
    }
}
