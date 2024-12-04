package com.rotiez.paymentservice.dao.model;

import com.rotiez.paymentservice.annotation.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table(name = "payments")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    private Long id;
    private Long userId;
    private BigDecimal amount;
    private LocalDateTime createdAt;
}