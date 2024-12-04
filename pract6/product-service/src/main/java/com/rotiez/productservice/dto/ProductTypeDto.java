package com.rotiez.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductTypeDto {
    private String name;
    private String description;
}
