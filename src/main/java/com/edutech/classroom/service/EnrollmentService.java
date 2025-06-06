package com.edutech.classroom.service;

import com.edutech.classroom.dto.EnrollmentDTO;
import com.edutech.classroom.entity.Enrollment;
import com.edutech.classroom.entity.Course;
import com.edutech.classroom.entity.User;
import com.edutech.classroom.exception.ResourceNotFoundException;
import com.edutech.classroom.repository.EnrollmentRepository;
import com.edutech.classroom.repository.CourseRepository;
import com.edutech.classroom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrollmentService {
    private final EnrollmentRepository repo;
    private final CourseRepository courseRepo;
    private final UserRepository userRepo;

    public List<EnrollmentDTO> findAll() {
        return repo.findAll().stream()
                .map(EnrollmentDTO::fromEntity)
                .toList();
    }

    public EnrollmentDTO findById(Integer id) {
        return EnrollmentDTO.fromEntity(
                repo.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Inscripción no encontrada"))
        );
    }

    public EnrollmentDTO create(EnrollmentDTO dto) {
        Course course = courseRepo.findById(dto.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado"));
        User student = userRepo.findById(dto.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Estudiante no encontrado"));

        Enrollment entity = EnrollmentDTO.toEntity(dto);
        entity.setCourse(course);
        entity.setStudent(student);
        if (entity.getEnrolledAt() == null) {
        entity.setEnrolledAt(java.time.Instant.now());
    }

        Enrollment saved = repo.save(entity);
        return EnrollmentDTO.fromEntity(saved);
    }

    public EnrollmentDTO update(Integer id, EnrollmentDTO dto) {
        Enrollment entity = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inscripción no encontrada"));

        Course course = courseRepo.findById(dto.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado"));
        User student = userRepo.findById(dto.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Estudiante no encontrado"));

        entity.setCourse(course);
        entity.setStudent(student);
       
        entity.setStatus(dto.getStatus());

        Enrollment updated = repo.save(entity);
        return EnrollmentDTO.fromEntity(updated);
    }

    public void delete(Integer id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("Inscripción no encontrada");
        }
        repo.deleteById(id);
    }
}