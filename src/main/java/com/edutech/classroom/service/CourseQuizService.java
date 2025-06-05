package com.edutech.classroom.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.edutech.classroom.dto.CourseQuizDTO;
import com.edutech.classroom.entity.Course;
import com.edutech.classroom.entity.CourseQuiz;
import com.edutech.classroom.exception.ResourceNotFoundException;
import com.edutech.classroom.repository.CourseQuizRepository;
import com.edutech.classroom.repository.CourseRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseQuizService {
    private final CourseQuizRepository repo;
    private final CourseRepository courseRepo;

    public List<CourseQuizDTO> findAll() {
        return repo.findAll().stream()
                .map(CourseQuizDTO::fromEntity)
                .toList();
    }

    public CourseQuizDTO findById(Integer id) {
        return CourseQuizDTO.fromEntity(
                repo.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Quiz no encontrado"))
        );
    }

    public CourseQuizDTO create(CourseQuizDTO dto) {
        Course course = courseRepo.findById(dto.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado"));

        CourseQuiz entity = CourseQuizDTO.toEntity(dto);
        entity.setCourse(course);

        CourseQuiz saved = repo.save(entity);
        return CourseQuizDTO.fromEntity(saved);
    }

    public CourseQuizDTO update(Integer id, CourseQuizDTO dto) {
        CourseQuiz entity = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Quiz no encontrado"));

        Course course = courseRepo.findById(dto.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado"));

        entity.setCourse(course);
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setQuizType(dto.getQuizType());
        entity.setCreatedAt(dto.getCreatedAt());

        CourseQuiz updated = repo.save(entity);
        return CourseQuizDTO.fromEntity(updated);
    }

    public void delete(Integer id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("Quiz no encontrado");
        }
        repo.deleteById(id);
    }
}