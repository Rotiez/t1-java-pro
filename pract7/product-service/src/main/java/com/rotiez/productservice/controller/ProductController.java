package com.rotiez.productservice.controller;

import com.rotiez.productservice.dto.ProductDto;
import com.rotiez.productservice.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{userId}")
    public List<ProductDto> getAllByUserId(@PathVariable("userId") long userId) {
        return productService.findByUserId(userId);
    }

    @GetMapping()
    public ProductDto getProduct(@RequestParam("id") long id) {
        return productService.findById(id);
    }

    @PutMapping()
    public void updateProduct(@RequestBody ProductDto productDto) {
        productService.updateProduct(productDto);
    }
}
