package com.edutech.classroom.dto;

import com.edutech.classroom.entity.CourseContent;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CourseContentDTO {

    private Integer id;

    @NotNull(message = "El ID del curso es obligatorio")
    private Integer courseId;

    @NotBlank(message = "El título no puede estar vacío")
    @Size(max = 200, message = "El título no puede superar los 200 caracteres")
    private String title;

    @NotBlank(message = "El tipo de contenido no puede estar vacío")
    @Size(max = 50, message = "El tipo de contenido no puede superar los 50 caracteres")
    private String contentType;

    @NotBlank(message = "La URL no puede estar vacía")
    @Size(max = 500, message = "La URL no puede superar los 500 caracteres")
    private String url;

    @NotNull(message = "El orden es obligatorio")
    private Integer orderIndex;

    public static CourseContentDTO fromEntity(CourseContent entity) {
        CourseContentDTO dto = new CourseContentDTO();
        dto.setId(entity.getId());
        dto.setCourseId(entity.getCourse() != null ? entity.getCourse().getId() : null);
        dto.setTitle(entity.getTitle());
        dto.setContentType(entity.getContentType());
        dto.setUrl(entity.getUrl());
        dto.setOrderIndex(entity.getOrderIndex());
        return dto;
    }

    public static CourseContent toEntity(CourseContentDTO dto) {
        CourseContent entity = new CourseContent();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setContentType(dto.getContentType());
        entity.setUrl(dto.getUrl());
        entity.setOrderIndex(dto.getOrderIndex());
        // Asignar course en el service
        return entity;
    }
}