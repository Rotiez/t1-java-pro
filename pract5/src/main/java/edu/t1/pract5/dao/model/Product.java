package edu.t1.pract5.dao.model;

import edu.t1.pract5.annotation.Table;

import java.math.BigDecimal;

@Table(name = "products")
public class Product {

    private Long id;
    private Long userId;
    private String account;
    private BigDecimal balance;
    private ProductType productType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public Product(Long id, String account, BigDecimal balance, ProductType productType) {
        this.id = id;
        this.account = account;
        this.balance = balance;
        this.productType = productType;
    }
}