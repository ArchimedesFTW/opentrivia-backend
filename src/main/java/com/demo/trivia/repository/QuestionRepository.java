package com.demo.trivia.repository;

import com.demo.trivia.entity.Question;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface QuestionRepository extends CrudRepository<Question, Long> {
}
