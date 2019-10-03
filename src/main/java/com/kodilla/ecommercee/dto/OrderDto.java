package com.kodilla.ecommercee.dto;

import com.kodilla.ecommercee.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private Long id;
    private User buyer;
    private User seller;
    private LocalDateTime purchaseDate;
    private List<ProductDto> products;
    private String status;
    private LocalDateTime deliveryDate;
    private BigDecimal orderValue;
}
