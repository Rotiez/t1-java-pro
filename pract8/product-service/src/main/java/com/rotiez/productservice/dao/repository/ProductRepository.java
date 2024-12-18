package com.rotiez.productservice.dao.repository;

import com.rotiez.productservice.dao.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByUserId(Long userId);
}
