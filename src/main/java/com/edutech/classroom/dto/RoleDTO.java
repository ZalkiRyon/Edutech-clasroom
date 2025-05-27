package com.edutech.classroom.dto;

import com.edutech.classroom.entity.Role;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RoleDTO {
    private Integer id;

    @NotNull
    @Size(max = 50)
    private String name;

    @NotNull
    @Size(max = 800)
    private String description;

    public static RoleDTO fromEntity(Role entity) {
        RoleDTO dto = new RoleDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        return dto;
    }

    public static Role toEntity(RoleDTO dto) {
        Role entity = new Role();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        return entity;
    }
}