package edu.t1.pract5.dao.model;

public enum ProductType {
    ACCOUNT("Счет"),
    CARD("Карта");

    private final String name;

    ProductType(String typeName) {
        this.name = typeName;
    }

    public String getName() {
        return name;
    }
}
