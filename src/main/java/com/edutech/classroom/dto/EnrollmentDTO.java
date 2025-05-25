package com.edutech.classroom.dto;


import com.edutech.classroom.entity.Course;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Data
public class EnrollmentDTO {

    private Integer id;
    private Integer studentId;
    private Integer courseId;
    private Instant enrolledAt;
    private String status;

}
