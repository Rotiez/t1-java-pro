package com.rotiez.paymentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class PaymentCommitDto {
    private Long productId;
    private BigDecimal amount;
}
