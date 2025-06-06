package com.edutech.classroom.dto;

import java.math.BigDecimal;
import java.time.Instant;

import com.edutech.classroom.entity.Payment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PaymentDTO {
    private Integer id;

    @NotNull(message = "El ID del usuario es obligatorio")
    private Integer userId;

    @NotNull(message = "El monto es obligatorio")
    private BigDecimal amount;


    private Instant paymentDate;

    @NotBlank(message = "El método de pago no puede estar vacío")
    @Size(max = 100, message = "El método de pago no puede superar los 100 caracteres")
    private String paymentMethod;

    @NotBlank(message = "La institución de pago no puede estar vacía")
    @Size(max = 200, message = "La institución de pago no puede superar los 200 caracteres")
    private String paymentInstitution;

    @NotBlank(message = "El ID de transacción no puede estar vacío")
    @Size(max = 200, message = "El ID de transacción no puede superar los 200 caracteres")
    private String transactionId;

    @NotBlank(message = "El estado no puede estar vacío")
    @Size(max = 20, message = "El estado no puede superar los 20 caracteres")
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