package com.edutech.classroom.repository;

import com.edutech.classroom.entity.CourseQuizQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseQuizQuestionRepository extends JpaRepository<CourseQuizQuestion, Integer> {
}