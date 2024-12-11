package edu.t1.limitservice.dto.client;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PaymentInitResponseDto {
    private Long id;
    private Long userId;
    private BigDecimal amount;
    private LocalDateTime createdAt;
}
