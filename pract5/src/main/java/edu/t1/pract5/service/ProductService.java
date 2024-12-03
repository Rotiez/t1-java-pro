package edu.t1.pract5.service;

import edu.t1.pract5.dao.ProductRepository;
import edu.t1.pract5.dao.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findByUserId(long userId) {
        return productRepository.findAllByUserId(userId);
    }

    public Product findById(long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Product with id %d not found".formatted(id))
                );
    }
}
