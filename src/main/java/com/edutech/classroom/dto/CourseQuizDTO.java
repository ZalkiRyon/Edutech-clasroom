package com.edutech.classroom.dto;

import java.time.Instant;

import com.edutech.classroom.entity.CourseQuiz;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CourseQuizDTO {
    private Integer id;

    @NotNull
    private Integer courseId;

    @NotBlank(message = "El título del quiz no puede estar vacío")
    @Size(max = 200, message = "El título del quiz no puede superar los 200 caracteres")
    private String title;

    @NotBlank(message = "La descripción del quiz no puede estar vacía")
    @Size(max = 800, message = "La descripción del quiz no puede superar los 800 caracteres")
    private String description;

    @NotBlank(message = "El tipo de quiz no puede estar vacío")
    @Size(max = 50, message = "El tipo de quiz no puede superar los 50 caracteres")
    private String quizType;

    @NotNull
    private Instant createdAt;

    public static CourseQuizDTO fromEntity(CourseQuiz entity) {
        CourseQuizDTO dto = new CourseQuizDTO();
        dto.setId(entity.getId());
        dto.setCourseId(entity.getCourse().getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setQuizType(entity.getQuizType());
        dto.setCreatedAt(entity.getCreatedAt());
        return dto;
    }

    public static CourseQuiz toEntity(CourseQuizDTO dto) {
        CourseQuiz entity = new CourseQuiz();
        entity.setId(dto.getId());
        // course debe setearse en el servicio
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setQuizType(dto.getQuizType());
        entity.setCreatedAt(dto.getCreatedAt());
        return entity;
    }
}