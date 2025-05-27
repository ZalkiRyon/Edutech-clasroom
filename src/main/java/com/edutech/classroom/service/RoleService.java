package com.edutech.classroom.service;

import com.edutech.classroom.dto.RoleDTO;
import com.edutech.classroom.entity.Role;
import com.edutech.classroom.exception.ResourceNotFoundException;
import com.edutech.classroom.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository repo;

    public List<RoleDTO> findAll() {
        return repo.findAll().stream()
                .map(RoleDTO::fromEntity)
                .toList();
    }

    public RoleDTO findById(Integer id) {
        return RoleDTO.fromEntity(
                repo.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Rol no encontrado"))
        );
    }

    public RoleDTO create(RoleDTO dto) {
        Role entity = RoleDTO.toEntity(dto);
        Role saved = repo.save(entity);
        return RoleDTO.fromEntity(saved);
    }

    public RoleDTO update(Integer id, RoleDTO dto) {
        Role entity = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rol no encontrado"));

        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());

        Role updated = repo.save(entity);
        return RoleDTO.fromEntity(updated);
    }

    public void delete(Integer id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("Rol no encontrado");
        }
        repo.deleteById(id);
    }
}