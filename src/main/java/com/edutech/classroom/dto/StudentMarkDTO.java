package com.edutech.classroom.dto;

import java.math.BigDecimal;
import java.time.Instant;

import com.edutech.classroom.entity.StudentMark;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class StudentMarkDTO {
    private Integer id;

    @NotNull(message = "El ID del quiz es obligatorio")
    private Integer quizId;

    @NotNull(message = "El ID del estudiante es obligatorio")
    private Integer studentId;

    @NotNull(message = "La nota es obligatoria")
    private BigDecimal mark;

    @Size(max = 800, message = "Los comentarios no pueden superar los 800 caracteres")
    private String comments;


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