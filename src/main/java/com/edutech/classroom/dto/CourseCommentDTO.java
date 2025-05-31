package com.edutech.classroom.dto;

import java.time.Instant;

import com.edutech.classroom.entity.CourseComment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CourseCommentDTO {

    private Integer id;

    @NotNull(message = "El ID del curso es obligatorio")
    private Integer courseId;

    @NotNull(message = "El ID del usuario es obligatorio")
    private Integer userId;

    @NotBlank(message = "El comentario no puede estar vacío")
    @Size(max = 800, message = "El comentario no puede superar los 800 caracteres")
    private String commentText;

    private Integer rating;
    private Instant createdAt;

    public static CourseCommentDTO fromEntity(CourseComment entity) {
        CourseCommentDTO dto = new CourseCommentDTO();
        dto.setId(entity.getId());
        dto.setCourseId(entity.getCourse() != null ? entity.getCourse().getId() : null);
        dto.setUserId(entity.getUser() != null ? entity.getUser().getId() : null);
        dto.setCommentText(entity.getCommentText());
        dto.setRating(entity.getRating());
        dto.setCreatedAt(entity.getCreatedAt());
        return dto;
    }

    public static CourseComment toEntity(CourseCommentDTO dto) {
        CourseComment entity = new CourseComment();
        entity.setId(dto.getId());
        entity.setCommentText(dto.getCommentText());
        entity.setRating(dto.getRating());
        entity.setCreatedAt(dto.getCreatedAt());
        // Asignar course y user en el service
        return entity;
    }
}