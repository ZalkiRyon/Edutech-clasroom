package com.edutech.classroom.dto;

import com.edutech.classroom.entity.QuizResponse;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.Instant;

@Data
public class QuizResponseDTO {
    private Integer id;

    @NotNull
    private Integer quizId;

    @NotNull
    private Integer studentId;

    @Size(max = 1)
    private String selectedOption;

    @Size(max = 800)
    private String responseContent;

    @Size(max = 800)
    private String assignmentUrl;

    @NotNull
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