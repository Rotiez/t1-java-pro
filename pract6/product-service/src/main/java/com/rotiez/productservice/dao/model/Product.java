package com.rotiez.productservice.dao.model;

import com.rotiez.productservice.annotation.Table;
import lombok.*;

import java.math.BigDecimal;

@Table(name = "products")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private Long id;
    private Long userId;
    private String account;
    private BigDecimal balance;
    private ProductType productType;
}