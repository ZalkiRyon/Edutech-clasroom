package com.edutech.classroom.dto;

import com.edutech.classroom.entity.CourseQuizQuestion;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.Instant;

@Data
public class CourseQuizQuestionDTO {
    private Integer id;

    @NotNull
    private Integer quizId;

    @Size(max = 800)
    private String questionText;

    @Size(max = 800)
    private String optionA;

    @Size(max = 800)
    private String optionB;

    @Size(max = 800)
    private String optionC;

    @Size(max = 800)
    private String optionD;

    @Size(max = 800)
    private String optionE;

    @Size(max = 800)
    private String correctAnswer;

    @Size(max = 1)
    private String correctOption;

    @NotNull
    private Integer orderIndex;

    @NotNull
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