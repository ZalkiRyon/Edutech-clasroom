package com.edutech.classroom.dto;

import com.edutech.classroom.entity.Payment;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
public class PaymentDTO {
    private Integer id;

    @NotNull
    private Integer userId;

    @NotNull
    private BigDecimal amount;

    @NotNull
    private Instant paymentDate;

    @NotNull
    @Size(max = 100)
    private String paymentMethod;

    @NotNull
    @Size(max = 200)
    private String paymentInstitution;

    @NotNull
    @Size(max = 200)
    private String transactionId;

    @NotNull
    @Size(max = 20)
    private String status;

    public static PaymentDTO fromEntity(Payment entity) {
        PaymentDTO dto = new PaymentDTO();
        dto.setId(entity.getId());
        dto.setUserId(entity.getUser().getId());
        dto.setAmount(entity.getAmount());
        dto.setPaymentDate(entity.getPaymentDate());
        dto.setPaymentMethod(entity.getPaymentMethod());
        dto.setPaymentInstitution(entity.getPaymentInstitution());
        dto.setTransactionId(entity.getTransactionId());
        dto.setStatus(entity.getStatus());
        return dto;
    }

    public static Payment toEntity(PaymentDTO dto) {
        Payment entity = new Payment();
        entity.setId(dto.getId());
        // user debe setearse en el servicio
        entity.setAmount(dto.getAmount());
        entity.setPaymentDate(dto.getPaymentDate());
        entity.setPaymentMethod(dto.getPaymentMethod());
        entity.setPaymentInstitution(dto.getPaymentInstitution());
        entity.setTransactionId(dto.getTransactionId());
        entity.setStatus(dto.getStatus());
        return entity;
    }
}