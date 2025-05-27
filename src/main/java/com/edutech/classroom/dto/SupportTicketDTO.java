package com.edutech.classroom.dto;

import com.edutech.classroom.entity.SupportTicket;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.Instant;

@Data
public class SupportTicketDTO {
    private Integer id;

    @NotNull
    private Integer userId;

    private Integer supportUserId;

    @NotNull
    @Size(max = 200)
    private String subject;

    @NotNull
    @Size(max = 800)
    private String description;

    @NotNull
    @Size(max = 20)
    private String status;

    @NotNull
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