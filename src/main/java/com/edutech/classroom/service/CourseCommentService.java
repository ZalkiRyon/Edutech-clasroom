package com.edutech.classroom.service;

import java.time.Instant;
import java.util.List;

import org.springframework.stereotype.Service;

import com.edutech.classroom.dto.CourseCommentDTO;
import com.edutech.classroom.entity.Course;
import com.edutech.classroom.entity.CourseComment;
import com.edutech.classroom.entity.User;
import com.edutech.classroom.exception.ResourceNotFoundException;
import com.edutech.classroom.repository.CourseCommentRepository;
import com.edutech.classroom.repository.CourseRepository;
import com.edutech.classroom.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseCommentService {
    private final CourseCommentRepository repo;
    private final CourseRepository courseRepo;
    private final UserRepository userRepo;

    public List<CourseCommentDTO> findAll() {
        return repo.findAll().stream()
                .map(CourseCommentDTO::fromEntity)
                .toList();
    }

    public CourseCommentDTO findById(Integer id) {
        return CourseCommentDTO.fromEntity(
                repo.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Comentario no encontrado"))
        );
    }

    public CourseCommentDTO create(CourseCommentDTO dto) {
        Course course = courseRepo.findById(dto.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado"));
        User user = userRepo.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        CourseComment entity = CourseCommentDTO.toEntity(dto);
        entity.setCourse(course);
        entity.setUser(user);

        // Asignar createdAt si es null
        if (entity.getCreatedAt() == null) {
            entity.setCreatedAt(Instant.now());
        }

        CourseComment saved = repo.save(entity);
        return CourseCommentDTO.fromEntity(saved);
    }

    public CourseCommentDTO update(Integer id, CourseCommentDTO dto) {
        CourseComment entity = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comentario no encontrado"));

        Course course = courseRepo.findById(dto.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado"));
        User user = userRepo.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        entity.setCourse(course);
        entity.setUser(user);
        entity.setCommentText(dto.getCommentText());
        entity.setRating(dto.getRating());
        // No modificar createdAt en update

        CourseComment updated = repo.save(entity);
        return CourseCommentDTO.fromEntity(updated);
    }

    public void delete(Integer id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("Comentario no encontrado");
        }
        repo.deleteById(id);
    }
}