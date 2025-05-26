package com.edutech.classroom.dto;

import com.edutech.classroom.entity.CourseComment;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.Instant;

@Data
public class CourseCommentDTO {

    private Integer id;

    @NotNull
    private Integer courseId;

    @NotNull
    private Integer userId;

    @NotNull(message = "El comentario no puede estar vacío")
    @Size(max = 800, message = "El comentario no puede superar los 800 caracteres")
    private String commentText;

    @NotNull
    private Integer rating;

    private Instant createdAt;

    public static CourseCommentDTO fromEntity(CourseComment entity) {
        CourseCommentDTO dto = new CourseCommentDTO();
        dto.setId(entity.getId());
        dto.setCourseId(entity.getCourse().getId());
        dto.setUserId(entity.getUser().getId());
        dto.setCommentText(entity.getCommentText());
        dto.setRating(entity.getRating());
        dto.setCreatedAt(entity.getCreatedAt());
        return dto;
    }

    public static CourseComment toEntity(CourseCommentDTO dto) {
        CourseComment entity = new CourseComment();
        entity.setId(dto.getId());
        // Debes setear Course y User usando sus repositorios o servicios en la capa de servicio
        entity.setCommentText(dto.getCommentText());
        entity.setRating(dto.getRating());
        entity.setCreatedAt(dto.getCreatedAt());
        return entity;
    }
}