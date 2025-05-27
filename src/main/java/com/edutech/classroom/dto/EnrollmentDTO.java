package com.edutech.classroom.dto;

import com.edutech.classroom.entity.Enrollment;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.Instant;

@Data
public class EnrollmentDTO {
    private Integer id;

    @NotNull
    private Integer studentId;

    @NotNull
    private Integer courseId;

    private Instant enrolledAt;
    private String status;

    public static EnrollmentDTO fromEntity(Enrollment entity) {
        EnrollmentDTO dto = new EnrollmentDTO();
        dto.setId(entity.getId());
        dto.setStudentId(entity.getStudent().getId());
        dto.setCourseId(entity.getCourse().getId());
        dto.setEnrolledAt(entity.getEnrolledAt());
        dto.setStatus(entity.getStatus());
        return dto;
    }

    public static Enrollment toEntity(EnrollmentDTO dto) {
        Enrollment entity = new Enrollment();
        entity.setId(dto.getId());
        // student y course deben setearse en el servicio
        entity.setEnrolledAt(dto.getEnrolledAt());
        entity.setStatus(dto.getStatus());
        return entity;
    }
}