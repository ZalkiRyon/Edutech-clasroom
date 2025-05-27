package com.edutech.classroom.service;

import com.edutech.classroom.dto.QuizResponseDTO;
import com.edutech.classroom.entity.QuizResponse;
import com.edutech.classroom.entity.CourseQuiz;
import com.edutech.classroom.entity.User;
import com.edutech.classroom.exception.ResourceNotFoundException;
import com.edutech.classroom.repository.QuizResponseRepository;
import com.edutech.classroom.repository.CourseQuizRepository;
import com.edutech.classroom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizResponseService {
    private final QuizResponseRepository repo;
    private final CourseQuizRepository quizRepo;
    private final UserRepository userRepo;

    public List<QuizResponseDTO> findAll() {
        return repo.findAll().stream()
                .map(QuizResponseDTO::fromEntity)
                .toList();
    }

    public QuizResponseDTO findById(Integer id) {
        return QuizResponseDTO.fromEntity(
                repo.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Respuesta no encontrada"))
        );
    }

    public QuizResponseDTO create(QuizResponseDTO dto) {
        CourseQuiz quiz = quizRepo.findById(dto.getQuizId())
                .orElseThrow(() -> new ResourceNotFoundException("Quiz no encontrado"));
        User student = userRepo.findById(dto.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Estudiante no encontrado"));

        QuizResponse entity = QuizResponseDTO.toEntity(dto);
        entity.setQuiz(quiz);
        entity.setStudent(student);

        QuizResponse saved = repo.save(entity);
        return QuizResponseDTO.fromEntity(saved);
    }

    public QuizResponseDTO update(Integer id, QuizResponseDTO dto) {
        QuizResponse entity = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Respuesta no encontrada"));

        CourseQuiz quiz = quizRepo.findById(dto.getQuizId())
                .orElseThrow(() -> new ResourceNotFoundException("Quiz no encontrado"));
        User student = userRepo.findById(dto.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Estudiante no encontrado"));

        entity.setQuiz(quiz);
        entity.setStudent(student);
        entity.setSelectedOption(dto.getSelectedOption());
        entity.setResponseContent(dto.getResponseContent());
        entity.setAssignmentUrl(dto.getAssignmentUrl());
        entity.setSubmittedAt(dto.getSubmittedAt());

        QuizResponse updated = repo.save(entity);
        return QuizResponseDTO.fromEntity(updated);
    }

    public void delete(Integer id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("Respuesta no encontrada");
        }
        repo.deleteById(id);
    }
}