package com.edutech.classroom.dto;

import com.edutech.classroom.entity.User;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.Instant;

@Data
public class UserDTO {
    private Integer id;

    @NotNull
    @Size(max = 100)
    private String firstName;

    @NotNull
    @Size(max = 100)
    private String lastName;

    @NotNull
    @Size(max = 255)
    private String email;

    @NotNull
    @Size(max = 255)
    private String passwordHash;

    @NotNull
    private Integer roleId;

    @NotNull
    private Boolean isActive;

    @NotNull
    private Instant createdAt;

    @NotNull
    private Instant updatedAt;

    public static UserDTO fromEntity(User entity) {
        UserDTO dto = new UserDTO();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setEmail(entity.getEmail());
        dto.setPasswordHash(entity.getPasswordHash());
        dto.setRoleId(entity.getRole().getId());
        dto.setIsActive(entity.getIsActive());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        return dto;
    }

    public static User toEntity(UserDTO dto) {
        User entity = new User();
        entity.setId(dto.getId());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());
        entity.setPasswordHash(dto.getPasswordHash());
        // role debe setearse en el servicio
        entity.setIsActive(dto.getIsActive());
        entity.setCreatedAt(dto.getCreatedAt());
        entity.setUpdatedAt(dto.getUpdatedAt());
        return entity;
    }
}