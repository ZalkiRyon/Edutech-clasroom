package com.edutech.classroom.repository;

import com.edutech.classroom.entity.StudentMark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentMarkRepository extends JpaRepository<StudentMark, Integer> {
}