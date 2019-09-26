package com.kodilla.ecommercee.dto;

import java.util.List;

public class CartDto {

    private Long id;
    private List<ProductDto> products;

    public CartDto() {
    }

    public CartDto(Long id, List<ProductDto> products) {
        this.id = id;
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ProductDto> getProducts() {
        return products;
    }

}
