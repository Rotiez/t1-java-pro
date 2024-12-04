package com.rotiez.productservice.service;

import com.rotiez.productservice.dao.repository.ProductRepository;
import com.rotiez.productservice.dto.ProductDto;
import com.rotiez.productservice.dto.mapper.ProductMapper;
import com.rotiez.productservice.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public List<ProductDto> findByUserId(long userId) {
        return productRepository.findAllByUserId(userId).stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    public ProductDto findById(long id) {
        var product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product with id %d not found".formatted(id)));
        return productMapper.toDto(product);
    }

    public void updateProduct(ProductDto productDto) {
        productRepository.upsert(productMapper.toEntity(productDto));
    }
}
