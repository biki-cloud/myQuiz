package com.example.myquiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.myquiz.model.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long> {

}
