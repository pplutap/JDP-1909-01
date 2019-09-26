package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.dto.OrderDto;
import com.kodilla.ecommercee.dto.ProductDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class OrderService {

    private static List<OrderDto> orders;

    public OrderService() {
        orders = generateOrders();
    }

    public static List<OrderDto> getOrders() {
        if (orders == null) {
            orders = generateOrders();
        }
        return orders;
    }

    private static List<ProductDto> initializeSampleProducts() {
        List<ProductDto> products = new ArrayList<>();
        String[] names = {"Ubrania", "Kostmetyki", "Samochdy", "Elektronika"};
        String descripiton = "Dummy item description.";
        for (int id = 0; id < names.length; id++) {
            products.add(new ProductDto(
                    (long) (id + 1),
                    names[id],
                    descripiton,
                    new BigDecimal(id*100),
                    (long)id
            ));
        }
        return products;
    }

    private static List<OrderDto> generateOrders() {
        List<OrderDto> orders = new ArrayList<>();
        LocalDateTime purchaseDate = LocalDateTime.of(2019, Month.SEPTEMBER, 1, 10, 30, 0);
        LocalDateTime deliveryDate = purchaseDate.plusDays(5);
        List<ProductDto> products = initializeSampleProducts();
        for (int i = 0; i < products.size(); i++) {
            List<ProductDto> productsList = new ArrayList<>();
            Long orderId = Long.valueOf(i + 1);
            for (int j = 0; j <= i; j++) {
                productsList.add(products.get(j));

            }
            orders.add(new OrderDto(orderId, 1L, 2L, purchaseDate, productsList, "DELIVERED", deliveryDate));
        }
        return orders;
    }
}
