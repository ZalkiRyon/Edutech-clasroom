package com.edutech.classroom.dto;

import java.time.Instant;

import com.edutech.classroom.entity.SupportTicket;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SupportTicketDTO {
    private Integer id;

    @NotNull(message = "El ID del usuario es obligatorio")
    private Integer userId;

    private Integer supportUserId;

    @NotBlank(message = "El asunto no puede estar vacío")
    @Size(max = 200, message = "El asunto no puede superar los 200 caracteres")
    private String subject;

    @NotBlank(message = "La descripción no puede estar vacía")
    @Size(max = 800, message = "La descripción no puede superar los 800 caracteres")
    private String description;

    @NotBlank(message = "El estado no puede estar vacío")
    @Size(max = 20, message = "El estado no puede superar los 20 caracteres")
    private String status;

    @NotNull(message = "La fecha de creación es obligatoria")
    private Instant createdAt;

    private Instant closedAt;

    public static SupportTicketDTO fromEntity(SupportTicket entity) {
        SupportTicketDTO dto = new SupportTicketDTO();
        dto.setId(entity.getId());
        dto.setUserId(entity.getUser().getId());
        dto.setSupportUserId(entity.getSupportUser() != null ? entity.getSupportUser().getId() : null);
        dto.setSubject(entity.getSubject());
        dto.setDescription(entity.getDescription());
        dto.setStatus(entity.getStatus());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setClosedAt(entity.getClosedAt());
        return dto;
    }

    public static SupportTicket toEntity(SupportTicketDTO dto) {
        SupportTicket entity = new SupportTicket();
        entity.setId(dto.getId());
        // user y supportUser deben setearse en el servicio
        entity.setSubject(dto.getSubject());
        entity.setDescription(dto.getDescription());
        entity.setStatus(dto.getStatus());
        entity.setCreatedAt(dto.getCreatedAt());
        entity.setClosedAt(dto.getClosedAt());
        return entity;
    }
}