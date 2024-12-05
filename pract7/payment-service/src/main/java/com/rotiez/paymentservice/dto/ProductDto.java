package com.rotiez.paymentservice.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDto implements Cloneable {
    private Long id;
    private Long userId;
    private String account;
    private BigDecimal balance;
    private ProductTypeDto productType;

    @Override
    public ProductDto clone() {
        try {
            return (ProductDto) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Cloning not supported", e);
        }
    }
}
