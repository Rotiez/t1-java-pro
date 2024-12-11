package edu.t1.limitservice.dto.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentInitRequestDto {
    private BigDecimal amount;
    private String account;
    private String productType;
}
