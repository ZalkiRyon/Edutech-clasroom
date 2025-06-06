package com.edutech.classroom.service;

import com.edutech.classroom.dto.PaymentDTO;
import com.edutech.classroom.entity.Payment;
import com.edutech.classroom.entity.User;
import com.edutech.classroom.exception.ResourceNotFoundException;
import com.edutech.classroom.repository.PaymentRepository;
import com.edutech.classroom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository repo;
    private final UserRepository userRepo;

    public List<PaymentDTO> findAll() {
        return repo.findAll().stream()
                .map(PaymentDTO::fromEntity)
                .toList();
    }

    public PaymentDTO findById(Integer id) {
        return PaymentDTO.fromEntity(
                repo.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Pago no encontrado"))
        );
    }

    public PaymentDTO create(PaymentDTO dto) {
        User user = userRepo.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        Payment entity = PaymentDTO.toEntity(dto);
        entity.setUser(user);
        if (entity.getPaymentDate() == null) {
            entity.setPaymentDate(java.time.Instant.now());
        }

        Payment saved = repo.save(entity);
        return PaymentDTO.fromEntity(saved);
    }

    public PaymentDTO update(Integer id, PaymentDTO dto) {
        Payment entity = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pago no encontrado"));

        User user = userRepo.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        entity.setUser(user);
        entity.setAmount(dto.getAmount());

        entity.setPaymentMethod(dto.getPaymentMethod());
        entity.setPaymentInstitution(dto.getPaymentInstitution());
        entity.setTransactionId(dto.getTransactionId());
        entity.setStatus(dto.getStatus());

        Payment updated = repo.save(entity);
        return PaymentDTO.fromEntity(updated);
    }

    public void delete(Integer id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("Pago no encontrado");
        }
        repo.deleteById(id);
    }
}