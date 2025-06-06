package com.edutech.classroom.dto;

import java.time.Instant;

import com.edutech.classroom.entity.CourseQuizQuestion;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CourseQuizQuestionDTO {
    private Integer id;

    @NotNull
    private Integer quizId;

    @NotBlank(message = "La pregunta no puede estar vacía")
    @Size(max = 800, message = "La pregunta no puede superar los 800 caracteres")
    private String questionText;

    @NotBlank(message = "La opción A no puede estar vacía")
    @Size(max = 800, message = "La opción A no puede superar los 800 caracteres")
    private String optionA;

    @NotBlank(message = "La opción B no puede estar vacía")
    @Size(max = 800, message = "La opción B no puede superar los 800 caracteres")
    private String optionB;

    @NotBlank(message = "La opción C no puede estar vacía")
    @Size(max = 800, message = "La opción C no puede superar los 800 caracteres")
    private String optionC;

    @NotBlank(message = "La opción D no puede estar vacía")
    @Size(max = 800, message = "La opción D no puede superar los 800 caracteres")
    private String optionD;

    @Size(max = 800, message = "La opción E no puede superar los 800 caracteres")
    private String optionE;

    @NotBlank(message = "La respuesta correcta no puede estar vacía")
    @Size(max = 800, message = "La respuesta correcta no puede superar los 800 caracteres")
    private String correctAnswer;

    @NotBlank(message = "La opción correcta no puede estar vacía")
    @Size(max = 1, message = "La opción correcta debe ser una sola letra")
    private String correctOption;

    @NotNull
    private Integer orderIndex;

    
    private Instant createdAt;

    public static CourseQuizQuestionDTO fromEntity(CourseQuizQuestion entity) {
        CourseQuizQuestionDTO dto = new CourseQuizQuestionDTO();
        dto.setId(entity.getId());
        dto.setQuizId(entity.getQuiz().getId());
        dto.setQuestionText(entity.getQuestionText());
        dto.setOptionA(entity.getOptionA());
        dto.setOptionB(entity.getOptionB());
        dto.setOptionC(entity.getOptionC());
        dto.setOptionD(entity.getOptionD());
        dto.setOptionE(entity.getOptionE());
        dto.setCorrectAnswer(entity.getCorrectAnswer());
        dto.setCorrectOption(entity.getCorrectOption());
        dto.setOrderIndex(entity.getOrderIndex());
        dto.setCreatedAt(entity.getCreatedAt());
        return dto;
    }

    public static CourseQuizQuestion toEntity(CourseQuizQuestionDTO dto) {
        CourseQuizQuestion entity = new CourseQuizQuestion();
        entity.setId(dto.getId());
        // quiz debe setearse en el servicio
        entity.setQuestionText(dto.getQuestionText());
        entity.setOptionA(dto.getOptionA());
        entity.setOptionB(dto.getOptionB());
        entity.setOptionC(dto.getOptionC());
        entity.setOptionD(dto.getOptionD());
        entity.setOptionE(dto.getOptionE());
        entity.setCorrectAnswer(dto.getCorrectAnswer());
        entity.setCorrectOption(dto.getCorrectOption());
        entity.setOrderIndex(dto.getOrderIndex());
        entity.setCreatedAt(dto.getCreatedAt());
        return entity;
    }
}