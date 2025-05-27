package com.edutech.classroom.service;

import com.edutech.classroom.dto.DiscountCouponDTO;
import com.edutech.classroom.entity.DiscountCoupon;
import com.edutech.classroom.exception.ResourceNotFoundException;
import com.edutech.classroom.repository.DiscountCouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiscountCouponService {
    private final DiscountCouponRepository repo;

    public List<DiscountCouponDTO> findAll() {
        return repo.findAll().stream()
                .map(DiscountCouponDTO::fromEntity)
                .toList();
    }

    public DiscountCouponDTO findById(Integer id) {
        return DiscountCouponDTO.fromEntity(
                repo.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Cupón no encontrado"))
        );
    }

    public DiscountCouponDTO create(DiscountCouponDTO dto) {
        DiscountCoupon entity = DiscountCouponDTO.toEntity(dto);
        DiscountCoupon saved = repo.save(entity);
        return DiscountCouponDTO.fromEntity(saved);
    }

    public DiscountCouponDTO update(Integer id, DiscountCouponDTO dto) {
        DiscountCoupon entity = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cupón no encontrado"));

        entity.setCode(dto.getCode());
        entity.setDescription(dto.getDescription());
        entity.setDiscountPercentage(dto.getDiscountPercentage());
        entity.setValidFrom(dto.getValidFrom());
        entity.setValidUntil(dto.getValidUntil());
        entity.setIsActive(dto.getIsActive());

        DiscountCoupon updated = repo.save(entity);
        return DiscountCouponDTO.fromEntity(updated);
    }

    public void delete(Integer id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("Cupón no encontrado");
        }
        repo.deleteById(id);
    }
}