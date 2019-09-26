package com.kodilla.ecommercee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
/**
 * OrderDto class still WIP (no specification confirmed as of 22/09/2019)
 * Field products Could be replaced by a ProductWrapper class Wrapper(Product product, Integer quantity)
 * other option is Map<Product,Integer> for the storage of products and their quantity
 */

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private Long id;
    private Long buyerId;
    private Long sellerId;
    private LocalDateTime purchaseDate;
    private List<ProductDto> products;
    private String status;
    private LocalDateTime deliveryDate;
    private BigDecimal orderValue;

    public OrderDto(long maxId, long l, long l1, LocalDateTime now, List<ProductDto> products, String awaiting_payment, LocalDateTime plusDays) {
        this.id = id;
        this.buyerId = buyerId;
        this.sellerId = sellerId;
        this.purchaseDate = purchaseDate;
        this.products = products;
        this.status = status;
        this.deliveryDate = deliveryDate;
        this.orderValue = orderValue;
    }

}
