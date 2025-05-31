package com.edutech.classroom.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.edutech.classroom.entity.DiscountCoupon;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DiscountCouponDTO {
    private Integer id;

    @NotBlank(message = "El código del cupón no puede estar vacío")
    @Size(max = 50, message = "El código del cupón no puede superar los 50 caracteres")
    private String code;

    @NotBlank(message = "La descripción no puede estar vacía")
    @Size(max = 800, message = "La descripción no puede superar los 800 caracteres")
    private String description;

    @NotNull(message = "El porcentaje de descuento es obligatorio")
    private BigDecimal discountPercentage;

    @NotNull(message = "La fecha de inicio de validez es obligatoria")
    private LocalDate validFrom;

    @NotNull(message = "La fecha de fin de validez es obligatoria")
    private LocalDate validUntil;

    @NotNull(message = "El estado de activación es obligatorio")
    private Boolean isActive;

    public static DiscountCouponDTO fromEntity(DiscountCoupon entity) {
        DiscountCouponDTO dto = new DiscountCouponDTO();
        dto.setId(entity.getId());
        dto.setCode(entity.getCode());
        dto.setDescription(entity.getDescription());
        dto.setDiscountPercentage(entity.getDiscountPercentage());
        dto.setValidFrom(entity.getValidFrom());
        dto.setValidUntil(entity.getValidUntil());
        dto.setIsActive(entity.getIsActive());
        return dto;
    }

    public static DiscountCoupon toEntity(DiscountCouponDTO dto) {
        DiscountCoupon entity = new DiscountCoupon();
        entity.setId(dto.getId());
        entity.setCode(dto.getCode());
        entity.setDescription(dto.getDescription());
        entity.setDiscountPercentage(dto.getDiscountPercentage());
        entity.setValidFrom(dto.getValidFrom());
        entity.setValidUntil(dto.getValidUntil());
        entity.setIsActive(dto.getIsActive());
        return entity;
    }
}