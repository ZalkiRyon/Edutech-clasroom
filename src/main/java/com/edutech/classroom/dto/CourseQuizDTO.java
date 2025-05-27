package com.edutech.classroom.dto;

import com.edutech.classroom.entity.CourseQuiz;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.Instant;

@Data
public class CourseQuizDTO {
    private Integer id;

    @NotNull
    private Integer courseId;

    @NotNull
    @Size(max = 200)
    private String title;

    @NotNull
    @Size(max = 800)
    private String description;

    @NotNull
    @Size(max = 50)
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