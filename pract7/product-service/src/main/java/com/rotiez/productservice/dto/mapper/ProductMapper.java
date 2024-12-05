package com.rotiez.productservice.dto.mapper;

import com.rotiez.productservice.dao.model.Product;
import com.rotiez.productservice.dao.model.ProductType;
import com.rotiez.productservice.dto.ProductDto;
import com.rotiez.productservice.dto.ProductTypeDto;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public ProductDto toDto(Product product) {
        return new ProductDto(
            product.getId(),
            product.getUserId(),
            product.getAccount(),
            product.getBalance(),
            new ProductTypeDto(
                product.getProductType().name(),
                product.getProductType().getName()
            )
        );
    }

    public Product toEntity(ProductDto dto) {
        return new Product(
            dto.getId(),
            dto.getUserId(),
            dto.getAccount(),
            dto.getBalance(),
            ProductType.valueOf(dto.getProductType().getName())
        );
    }
}
