package com.kodilla.ecommercee.dto;

import com.kodilla.ecommercee.domain.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long userId;
    private String userName;
    private int status;
    private long userKey;
    private List<OrderDto> ordersAsBuyer = new ArrayList<>();
    private List<OrderDto> ordersAsSeller = new ArrayList<>();
}
