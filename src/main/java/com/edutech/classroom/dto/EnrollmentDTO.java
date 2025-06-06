package com.edutech.classroom.dto;

import com.edutech.classroom.entity.Enrollment;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.Instant;

@Data
public class EnrollmentDTO {
    private Integer id;

    @NotNull(message = "El ID del estudiante es obligatorio")
    private Integer studentId;

    @NotNull(message = "El ID del curso es obligatorio")
    private Integer courseId;


    private Instant enrolledAt;

    @NotBlank(message = "El estado de la inscripción no puede estar vacío")
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