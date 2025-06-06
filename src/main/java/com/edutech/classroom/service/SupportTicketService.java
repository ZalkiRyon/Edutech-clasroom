package com.edutech.classroom.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.edutech.classroom.dto.SupportTicketDTO;
import com.edutech.classroom.entity.SupportTicket;
import com.edutech.classroom.entity.User;
import com.edutech.classroom.exception.ResourceNotFoundException;
import com.edutech.classroom.repository.SupportTicketRepository;
import com.edutech.classroom.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SupportTicketService {
    private final SupportTicketRepository repo;
    private final UserRepository userRepo;

    public List<SupportTicketDTO> findAll() {
        return repo.findAll().stream()
                .map(SupportTicketDTO::fromEntity)
                .toList();
    }

    public SupportTicketDTO findById(Integer id) {
        return SupportTicketDTO.fromEntity(
                repo.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Ticket no encontrado"))
        );
    }

    public SupportTicketDTO create(SupportTicketDTO dto) {
        User user = userRepo.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        User supportUser = null;
        if (dto.getSupportUserId() != null) {
            supportUser = userRepo.findById(dto.getSupportUserId())
                    .orElseThrow(() -> new ResourceNotFoundException("Usuario de soporte no encontrado"));
        }

        SupportTicket entity = SupportTicketDTO.toEntity(dto);
        entity.setUser(user);
        entity.setSupportUser(supportUser);
        if (entity.getCreatedAt() == null) {
            entity.setCreatedAt(java.time.Instant.now());
        }
        entity.setClosedAt(null);

        SupportTicket saved = repo.save(entity);
        return SupportTicketDTO.fromEntity(saved);
    }

    public SupportTicketDTO update(Integer id, SupportTicketDTO dto) {
        SupportTicket entity = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket no encontrado"));

        User user = userRepo.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        User supportUser = null;
        if (dto.getSupportUserId() != null) {
            supportUser = userRepo.findById(dto.getSupportUserId())
                    .orElseThrow(() -> new ResourceNotFoundException("Usuario de soporte no encontrado"));
        }

        entity.setUser(user);
        entity.setSupportUser(supportUser);
        entity.setSubject(dto.getSubject());
        entity.setDescription(dto.getDescription());
        entity.setStatus(dto.getStatus());
        
        

        if (dto.getClosedAt() != null) {
            entity.setClosedAt(dto.getClosedAt());
        }

        SupportTicket updated = repo.save(entity);
        return SupportTicketDTO.fromEntity(updated);
    }

    public void delete(Integer id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("Ticket no encontrado");
        }
        repo.deleteById(id);
    }
}