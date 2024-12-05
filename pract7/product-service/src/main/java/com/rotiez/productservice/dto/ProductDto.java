package com.rotiez.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ProductDto {
    private Long id;
    private Long userId;
    private String account;
    private BigDecimal balance;
    private ProductTypeDto productType;
}
