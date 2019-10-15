package com.kodilla.ecommercee.dto;

import com.kodilla.ecommercee.domain.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private Long id;
    private Long buyerId;
    private Long sellerId;
    private LocalDateTime purchaseDate;
    private String productIds;
    //    private List<ProductDto> products;
    private StatusEnum status;
    private LocalDateTime deliveryDate;
    private BigDecimal orderValue;

}
