package edu.t1.pract5.controller;

import edu.t1.pract5.dao.model.Product;
import edu.t1.pract5.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}/by-user")
    public List<Product> getAllByUserId(@PathVariable("id") long userId) {
        return productService.findByUserId(userId);
    }

    @GetMapping()
    public Product getProduct(@RequestParam("id") long id) {
        return productService.findById(id);
    }
}
