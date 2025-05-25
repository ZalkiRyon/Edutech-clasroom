package com.edutech.classroom.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data

public class CourseCategoryDTO {

    private Integer id;

    @NotNull(message = "El nombre de la categoria no puede estar vacio")
    @Size(max = 100, message= "El nombre de la catergoria no puede superar los 100 caracteres")
    private String name;

    @NotNull(message = "La descriocion de la categoria no puede estar vacia")
    @Size(max = 800, message= "La descriocion  de la catergoria no puede superar los 800 caracteres")
    private String description;

    public static CourseCategoryDTO fromEntity(CourseCategoryDTO entity){
        CourseCategoryDTO dto = new CourseCategoryDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        return dto;
    }
    public static CourseCategoryDTO toEntity(CourseCategoryDTO dto){
        CourseCategoryDTO entity = new CourseCategoryDTO();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        return entity;

    }


}
