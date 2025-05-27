package com.edutech.classroom.dto;

import com.edutech.classroom.entity.StudentMark;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
public class StudentMarkDTO {
    private Integer id;

    @NotNull
    private Integer quizId;

    @NotNull
    private Integer studentId;

    @NotNull
    private BigDecimal mark;

    @Size(max = 800)
    private String comments;

    @NotNull
    private Instant gradedAt;

    public static StudentMarkDTO fromEntity(StudentMark entity) {
        StudentMarkDTO dto = new StudentMarkDTO();
        dto.setId(entity.getId());
        dto.setQuizId(entity.getQuiz().getId());
        dto.setStudentId(entity.getStudent().getId());
        dto.setMark(entity.getMark());
        dto.setComments(entity.getComments());
        dto.setGradedAt(entity.getGradedAt());
        return dto;
    }

    public static StudentMark toEntity(StudentMarkDTO dto) {
        StudentMark entity = new StudentMark();
        entity.setId(dto.getId());
        // quiz y student deben setearse en el servicio
        entity.setMark(dto.getMark());
        entity.setComments(dto.getComments());
        entity.setGradedAt(dto.getGradedAt());
        return entity;
    }
}