package edu.t1.limitservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class PaymentResponseDto {
    private Long id;
    private Long userId;
    private BigDecimal amount;
    private LocalDateTime createdAt;
}
