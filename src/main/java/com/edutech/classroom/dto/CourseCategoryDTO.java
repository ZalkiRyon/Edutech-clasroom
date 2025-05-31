package com.edutech.classroom.dto;

import com.edutech.classroom.entity.CourseCategory;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CourseCategoryDTO {

    private Integer id;

    @NotBlank(message = "El nombre de la categoria no puede estar vacio")
    @Size(max = 100, message= "El nombre de la categoria no puede superar los 100 caracteres")
    private String name;

    @NotBlank(message = "La descripcion de la categoria no puede estar vacia")
    @Size(max = 800, message= "La descripcion de la categoria no puede superar los 800 caracteres")
    private String description;

    public static CourseCategoryDTO fromEntity(CourseCategory entity){
        CourseCategoryDTO dto = new CourseCategoryDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        return dto;
    }

    public static CourseCategory toEntity(CourseCategoryDTO dto){
        CourseCategory entity = new CourseCategory();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        return entity;
    }
}