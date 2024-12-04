package com.rotiez.productservice.dao.mapper;

import com.rotiez.productservice.dao.model.Product;
import com.rotiez.productservice.dao.model.ProductType;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ProductRowMapper implements RowMapper<Product> {
    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Product.builder()
            .id(rs.getLong("id"))
            .userId(rs.getLong("user_id"))
            .account(rs.getString("account"))
            .balance(rs.getBigDecimal("balance"))
            .productType(ProductType.valueOf(rs.getString("product_type")))
        .build();
    }
}
