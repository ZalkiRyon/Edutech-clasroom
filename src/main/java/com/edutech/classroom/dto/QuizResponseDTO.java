package com.edutech.classroom.dto;

import java.time.Instant;

import com.edutech.classroom.entity.QuizResponse;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class QuizResponseDTO {
    private Integer id;

    @NotNull(message = "El ID del quiz es obligatorio")
    private Integer quizId;

    @NotNull(message = "El ID del estudiante es obligatorio")
    private Integer studentId;

    @NotBlank(message = "La opción seleccionada no puede estar vacía")
    @Size(max = 1, message = "La opción seleccionada debe ser una sola letra")
    private String selectedOption;

    @NotBlank(message = "La respuesta no puede estar vacía")
    @Size(max = 800, message = "La respuesta no puede superar los 800 caracteres")
    private String responseContent;

    @Size(max = 800, message = "La URL de la tarea no puede superar los 800 caracteres")
    private String assignmentUrl;

    @NotNull(message = "La fecha de envío es obligatoria")
    private Instant submittedAt;

    public static QuizResponseDTO fromEntity(QuizResponse entity) {
        QuizResponseDTO dto = new QuizResponseDTO();
        dto.setId(entity.getId());
        dto.setQuizId(entity.getQuiz().getId());
        dto.setStudentId(entity.getStudent().getId());
        dto.setSelectedOption(entity.getSelectedOption());
        dto.setResponseContent(entity.getResponseContent());
        dto.setAssignmentUrl(entity.getAssignmentUrl());
        dto.setSubmittedAt(entity.getSubmittedAt());
        return dto;
    }

    public static QuizResponse toEntity(QuizResponseDTO dto) {
        QuizResponse entity = new QuizResponse();
        entity.setId(dto.getId());
        // quiz y student deben setearse en el servicio
        entity.setSelectedOption(dto.getSelectedOption());
        entity.setResponseContent(dto.getResponseContent());
        entity.setAssignmentUrl(dto.getAssignmentUrl());
        entity.setSubmittedAt(dto.getSubmittedAt());
        return entity;
    }
}