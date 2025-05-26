package com.edutech.classroom.repository;

import com.edutech.classroom.entity.QuizResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizResponseRepository extends JpaRepository<QuizResponse, Integer> {
}