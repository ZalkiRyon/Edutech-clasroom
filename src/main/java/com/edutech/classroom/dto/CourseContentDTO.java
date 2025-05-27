package com.edutech.classroom.dto;

import com.edutech.classroom.entity.CourseContent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CourseContentDTO {
    private Integer id;

    @NotNull
    private Integer courseId;

    @NotNull
    @Size(max = 200)
    private String title;

    @NotNull
    @Size(max = 50)
    private String contentType;

    @NotNull
    @Size(max = 800)
    private String url;

    @NotNull
    private Integer orderIndex;

    public static CourseContentDTO fromEntity(CourseContent entity) {
        CourseContentDTO dto = new CourseContentDTO();
        dto.setId(entity.getId());
        dto.setCourseId(entity.getCourse().getId());
        dto.setTitle(entity.getTitle());
        dto.setContentType(entity.getContentType());
        dto.setUrl(entity.getUrl());
        dto.setOrderIndex(entity.getOrderIndex());
        return dto;
    }

    public static CourseContent toEntity(CourseContentDTO dto) {
        CourseContent entity = new CourseContent();
        entity.setId(dto.getId());
        // course debe setearse en el servicio
        entity.setTitle(dto.getTitle());
        entity.setContentType(dto.getContentType());
        entity.setUrl(dto.getUrl());
        entity.setOrderIndex(dto.getOrderIndex());
        return entity;
    }
}