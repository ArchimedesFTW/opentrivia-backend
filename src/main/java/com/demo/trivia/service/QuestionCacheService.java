package com.demo.trivia.service;

import com.demo.trivia.dto.*;
import com.demo.trivia.dto.external.TriviaResponseDto;
import com.demo.trivia.entity.Question;
import com.demo.trivia.external.OpenTriviaBackend;
import com.demo.trivia.mapper.OpenTriviaMapper;
import com.demo.trivia.repository.QuestionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;


@Service
public class QuestionCacheService {
    private final QuestionRepository questionRepository;
    private final OpenTriviaBackend openTriviaBackend;

    public QuestionCacheService(QuestionRepository questionRepository, OpenTriviaBackend openTriviaBackend) {
        this.questionRepository = questionRepository;
        this.openTriviaBackend = openTriviaBackend;
    }

    public QuestionResponseDto getQuestions(int amount) {
        TriviaResponseDto response = null;
        try {
            response = this.openTriviaBackend.getQuestion(amount);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "OpenTrivia API failed", ex);
        }

        List<Question> questions = OpenTriviaMapper.toQuestionEntity(response.results());

        // Cache them, so we can find them when verifying the correct answer.
        this.questionRepository.saveAll(questions);

        // Convert them to show the possible answers without indication of solution.
        List<QuestionDto> questionDtos = OpenTriviaMapper.toQuestionDto(questions);

        return new QuestionResponseDto(questionDtos.toArray(QuestionDto[]::new));
    }

    public AnswerResponseDto checkAnswers(AnswerRequestDto requestDto) {
        List<Long> ids = Arrays.stream(requestDto.answers()).map(AnswerDto::questionId).toList();

        // Fetch questions while maintaining order
        SolutionDto[] solutionDtos = new SolutionDto[ids.size()];
        for (Question question : this.questionRepository.findAllById(ids)) {
            int i = ids.indexOf(question.getId());

            // Each solution exists of the question, given answer, solution
            QuestionDto questionDto = OpenTriviaMapper.toQuestionDto(question);
            int selectedIndex = requestDto.answers()[i].selectedIndex();
            String correctAnswer = question.getCorrectAnswer();
            int correctIndex = Arrays.asList(questionDto.answers()).indexOf(correctAnswer);
            solutionDtos[i] = new SolutionDto(questionDto, selectedIndex, correctIndex);

            // Possibly check if answer is (in) question answer.
            // I'll leave that to the front-end
        }

        if (Arrays.stream(solutionDtos).anyMatch(Objects::isNull)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Not all question id's are found"
            );
        }

        return new AnswerResponseDto(solutionDtos);
    }
}
