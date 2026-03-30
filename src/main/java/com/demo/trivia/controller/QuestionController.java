package com.demo.trivia.controller;

import com.demo.trivia.dto.QuestionResponseDto;
import com.demo.trivia.service.QuestionCacheService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class QuestionController {
    private final QuestionCacheService questionCacheService;

    public QuestionController(QuestionCacheService questionCacheService) {
        this.questionCacheService = questionCacheService;
    }

    @GetMapping("/questions")
    public QuestionResponseDto getQuestions(@RequestParam
                                            @Min(value = 1, message = "amount must be at least 1")
                                            @Max(value = 50, message = "amount must be at most 50")
                                            int amount) {
        System.out.println("Received request");

        return this.questionCacheService.getQuestions(amount);
    }
}
