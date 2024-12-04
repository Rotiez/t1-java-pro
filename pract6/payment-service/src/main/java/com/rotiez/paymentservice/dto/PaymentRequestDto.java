package com.rotiez.paymentservice.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentRequestDto {
    private BigDecimal amount;
    private String account;
    private String productType;
}
