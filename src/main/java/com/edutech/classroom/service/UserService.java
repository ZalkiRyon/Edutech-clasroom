package com.edutech.classroom.service;

import com.edutech.classroom.dto.UserDTO;
import com.edutech.classroom.entity.User;
import com.edutech.classroom.entity.Role;
import com.edutech.classroom.exception.ResourceNotFoundException;
import com.edutech.classroom.repository.UserRepository;
import com.edutech.classroom.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repo;
    private final RoleRepository roleRepo;

    public List<UserDTO> findAll() {
        return repo.findAll().stream()
                .map(UserDTO::fromEntity)
                .toList();
    }

    public UserDTO findById(Integer id) {
        return UserDTO.fromEntity(
                repo.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"))
        );
    }

    public UserDTO create(UserDTO dto) {
        Role role = roleRepo.findById(dto.getRoleId())
                .orElseThrow(() -> new ResourceNotFoundException("Rol no encontrado"));

        User entity = UserDTO.toEntity(dto);
        entity.setRole(role);

        User saved = repo.save(entity);
        return UserDTO.fromEntity(saved);
    }

    public UserDTO update(Integer id, UserDTO dto) {
        User entity = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        Role role = roleRepo.findById(dto.getRoleId())
                .orElseThrow(() -> new ResourceNotFoundException("Rol no encontrado"));

        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());
        entity.setPasswordHash(dto.getPasswordHash());
        entity.setRole(role);
        entity.setIsActive(dto.getIsActive());
        entity.setCreatedAt(dto.getCreatedAt());
        entity.setUpdatedAt(dto.getUpdatedAt());

        User updated = repo.save(entity);
        return UserDTO.fromEntity(updated);
    }

    public void delete(Integer id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("Usuario no encontrado");
        }
        repo.deleteById(id);
    }
}