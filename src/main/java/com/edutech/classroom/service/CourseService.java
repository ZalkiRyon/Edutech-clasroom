package com.edutech.classroom.service;

import com.edutech.classroom.dto.CourseDTO;
import com.edutech.classroom.entity.Course;
import com.edutech.classroom.entity.CourseCategory;
import com.edutech.classroom.entity.User;
import com.edutech.classroom.exception.ResourceNotFoundException;
import com.edutech.classroom.repository.CourseCategoryRepository;
import com.edutech.classroom.repository.CourseRepository;
import com.edutech.classroom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository repo;
    private final CourseCategoryRepository categoryRepo;
    private final UserRepository userRepo;

    public List<CourseDTO> findAll() {
        return repo.findAll().stream()
                .map(CourseDTO::fromEntity)
                .toList();
    }

    public CourseDTO findById(Integer id) {
        return CourseDTO.fromEntity(
                repo.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado"))
        );
    }

    public CourseDTO create(CourseDTO dto) {
        CourseCategory category = categoryRepo.findById(dto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada"));
        User manager = userRepo.findById(dto.getManagerId())
                .orElseThrow(() -> new ResourceNotFoundException("Manager no encontrado"));
        User instructor = userRepo.findById(dto.getInstructorId())
                .orElseThrow(() -> new ResourceNotFoundException("Instructor no encontrado"));

        Course entity = CourseDTO.toEntity(dto);
        entity.setCategory(category);
        entity.setManager(manager);
        entity.setInstructor(instructor);

        Course saved = repo.save(entity);
        return CourseDTO.fromEntity(saved);
    }

    public CourseDTO update(Integer id, CourseDTO dto) {
        Course entity = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado"));

        CourseCategory category = categoryRepo.findById(dto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada"));
        User manager = userRepo.findById(dto.getManagerId())
                .orElseThrow(() -> new ResourceNotFoundException("Manager no encontrado"));
        User instructor = userRepo.findById(dto.getInstructorId())
                .orElseThrow(() -> new ResourceNotFoundException("Instructor no encontrado"));

        entity.setCategory(category);
        entity.setManager(manager);
        entity.setInstructor(instructor);
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setPublishDate(dto.getPublishDate());
        entity.setPrice(dto.getPrice());
        entity.setImage(dto.getImage());
        entity.setStatus(dto.getStatus());

        Course updated = repo.save(entity);
        return CourseDTO.fromEntity(updated);
    }

    public void delete(Integer id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("Curso no encontrado");
        }
        repo.deleteById(id);
    }
}