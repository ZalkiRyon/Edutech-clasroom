package com.edutech.classroom.service;

import com.edutech.classroom.dto.CourseContentDTO;
import com.edutech.classroom.entity.CourseContent;
import com.edutech.classroom.entity.Course;
import com.edutech.classroom.exception.ResourceNotFoundException;
import com.edutech.classroom.repository.CourseContentRepository;
import com.edutech.classroom.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseContentService {
    private final CourseContentRepository repo;
    private final CourseRepository courseRepo;

    public List<CourseContentDTO> findAll() {
        return repo.findAll().stream()
                .map(CourseContentDTO::fromEntity)
                .toList();
    }

    public CourseContentDTO findById(Integer id) {
        return CourseContentDTO.fromEntity(
                repo.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Contenido no encontrado"))
        );
    }

    public CourseContentDTO create(CourseContentDTO dto) {
        Course course = courseRepo.findById(dto.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado"));

        CourseContent entity = CourseContentDTO.toEntity(dto);
        entity.setCourse(course);

        CourseContent saved = repo.save(entity);
        return CourseContentDTO.fromEntity(saved);
    }

    public CourseContentDTO update(Integer id, CourseContentDTO dto) {
        CourseContent entity = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contenido no encontrado"));

        Course course = courseRepo.findById(dto.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado"));

        entity.setCourse(course);
        entity.setTitle(dto.getTitle());
        entity.setContentType(dto.getContentType());
        entity.setUrl(dto.getUrl());
        entity.setOrderIndex(dto.getOrderIndex());

        CourseContent updated = repo.save(entity);
        return CourseContentDTO.fromEntity(updated);
    }

    public void delete(Integer id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("Contenido no encontrado");
        }
        repo.deleteById(id);
    }
}