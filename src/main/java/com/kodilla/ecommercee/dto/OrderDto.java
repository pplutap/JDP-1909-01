package com.kodilla.ecommercee.dto;

import com.kodilla.ecommercee.domain.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private Long id;
    private Long buyerId;
    private Long sellerId;
    private LocalDate purchaseDate;
    private StatusEnum status;
    private LocalDate deliveryDate;
    private BigDecimal orderValue;

}
