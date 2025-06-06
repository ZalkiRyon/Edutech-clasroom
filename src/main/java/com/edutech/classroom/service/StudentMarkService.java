package com.edutech.classroom.service;

import com.edutech.classroom.dto.StudentMarkDTO;
import com.edutech.classroom.entity.StudentMark;
import com.edutech.classroom.entity.CourseQuiz;
import com.edutech.classroom.entity.User;
import com.edutech.classroom.exception.ResourceNotFoundException;
import com.edutech.classroom.repository.StudentMarkRepository;
import com.edutech.classroom.repository.CourseQuizRepository;
import com.edutech.classroom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentMarkService {
    private final StudentMarkRepository repo;
    private final CourseQuizRepository quizRepo;
    private final UserRepository userRepo;

    public List<StudentMarkDTO> findAll() {
        return repo.findAll().stream()
                .map(StudentMarkDTO::fromEntity)
                .toList();
    }

    public StudentMarkDTO findById(Integer id) {
        return StudentMarkDTO.fromEntity(
                repo.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Nota no encontrada"))
        );
    }

    public StudentMarkDTO create(StudentMarkDTO dto) {
        CourseQuiz quiz = quizRepo.findById(dto.getQuizId())
                .orElseThrow(() -> new ResourceNotFoundException("Quiz no encontrado"));
        User student = userRepo.findById(dto.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Estudiante no encontrado"));

        StudentMark entity = StudentMarkDTO.toEntity(dto);
        entity.setQuiz(quiz);
        entity.setStudent(student);
        if (entity.getGradedAt() == null) {
            entity.setGradedAt(java.time.Instant.now());
        }

        StudentMark saved = repo.save(entity);
        return StudentMarkDTO.fromEntity(saved);
    }

    public StudentMarkDTO update(Integer id, StudentMarkDTO dto) {
        StudentMark entity = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nota no encontrada"));

        CourseQuiz quiz = quizRepo.findById(dto.getQuizId())
                .orElseThrow(() -> new ResourceNotFoundException("Quiz no encontrado"));
        User student = userRepo.findById(dto.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Estudiante no encontrado"));

        entity.setQuiz(quiz);
        entity.setStudent(student);
        entity.setMark(dto.getMark());
        entity.setComments(dto.getComments());


        StudentMark updated = repo.save(entity);
        return StudentMarkDTO.fromEntity(updated);
    }

    public void delete(Integer id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("Nota no encontrada");
        }
        repo.deleteById(id);
    }
}