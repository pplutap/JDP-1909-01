package com.kodilla.ecommercee.dto;

import java.time.LocalDateTime;
import java.util.List;

public class OrderDto {

    private Long id;
    private Long buyerId;
    private Long sellerId;
    private LocalDateTime purchaseDate;
    private List<ProductDto> products;
    private String status;
    private LocalDateTime deliveryDate;

    public OrderDto() {
    }
    /**
     * OrderDto class still WIP (no specification confirmed as of 22/09/2019)
     * @param products Could be replaced by a ProductWrapper class Wrapper(Product product, Integer quantity)
     * other option is Map<Product,Integer> for the storage of products and their quantity
     */
    public OrderDto(Long id, Long buyerId, Long sellerId, LocalDateTime purchaseDate, List<ProductDto> products, String status, LocalDateTime deliveryDate) {
        this.id = id;
        this.buyerId = buyerId;
        this.sellerId = sellerId;
        this.purchaseDate = purchaseDate;
        this.products = products;
        this.status = status;
        this.deliveryDate = deliveryDate;
    }

    public Long getId() {
        return id;
    }

    public Long getBuyerId() {
        return buyerId;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public List<ProductDto> getProducts() {
        return products;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getDeliveryDate() {
        return deliveryDate;
    }
}
