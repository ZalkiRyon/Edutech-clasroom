package com.edutech.classroom.dto;

import java.time.Instant;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EnrollmentDTO {

    private Integer id;

    @NotNull
    private Integer studentId;

    @NotNull
    private Integer courseId;

    private Instant enrolledAt;
    private String status;

}