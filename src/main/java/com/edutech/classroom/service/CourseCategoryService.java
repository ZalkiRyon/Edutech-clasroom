package com.edutech.classroom.service;

import com.edutech.classroom.dto.CourseCategoryDTO;
import com.edutech.classroom.entity.CourseCategory;
import com.edutech.classroom.exception.ResourceNotFoundException;
import com.edutech.classroom.repository.CourseCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseCategoryService {
    private final CourseCategoryRepository repo;

    public List<CourseCategoryDTO> findAll() {
        return repo.findAll().stream()
                .map(CourseCategoryDTO::fromEntity)
                .toList();
    }

    public CourseCategoryDTO findById(Integer id){
        return CourseCategoryDTO.fromEntity(
                repo.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Categoria no encontrada"))
        );
    }

    public CourseCategoryDTO create(CourseCategoryDTO dto){
        CourseCategory entity = CourseCategoryDTO.toEntity(dto);
        CourseCategory saved = repo.save(entity);
        return CourseCategoryDTO.fromEntity(saved);
    }
    public CourseCategoryDTO update(Integer id, CourseCategoryDTO dto) {
        var entity = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria no encontrada"));
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        var updated = repo.save(entity);
        return CourseCategoryDTO.fromEntity(updated);
    }

    public void delete(Integer id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("Categoria no encontrada");
        }
        repo.deleteById(id);
    }

}