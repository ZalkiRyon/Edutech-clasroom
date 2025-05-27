package com.edutech.classroom.dto;

import com.edutech.classroom.entity.DiscountCoupon;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class DiscountCouponDTO {
    private Integer id;

    @NotNull
    @Size(max = 50)
    private String code;

    @NotNull
    @Size(max = 800)
    private String description;

    @NotNull
    private BigDecimal discountPercentage;

    @NotNull
    private LocalDate validFrom;

    @NotNull
    private LocalDate validUntil;

    @NotNull
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