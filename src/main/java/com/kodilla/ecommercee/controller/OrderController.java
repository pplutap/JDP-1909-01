package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.StatusEnum;
import com.kodilla.ecommercee.dto.CartDto;
import com.kodilla.ecommercee.dto.OrderDto;
import com.kodilla.ecommercee.dto.ProductDto;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/orders")
@CrossOrigin("*")
public class OrderController {

    @GetMapping
    public List<OrderDto> getOrders() {
        return generateOrders();
    }

    private List<OrderDto> generateOrders() {
        List<OrderDto> orders = new ArrayList<>();
        LocalDateTime purchaseDate = LocalDateTime.of(2019, Month.SEPTEMBER, 1, 10, 30, 0);
        LocalDateTime deliveryDate = purchaseDate.plusDays(5);
        String productIds = "1";
        for (long i = 1; i < 5; i++) {
            orders.add(new OrderDto(i, 1L, 2L, purchaseDate, productIds, StatusEnum.DELIVERED, deliveryDate,
                    new BigDecimal(i * 100)));
            productIds += "," + (i + 1);
        }
        return orders;
    }

    @GetMapping("/{orderId}")
    public OrderDto getOrderById(@PathVariable Long orderId) {
        return generateOrders().stream()
                .filter(order -> order.getId().equals(orderId))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Order not found"));
    }

    @DeleteMapping("/{orderId}")
    public void deleteOrder(@PathVariable Long orderId) {
        OrderDto orderToDelete = generateOrders().stream()
                .filter(product -> product.getId().equals(orderId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Order non-existent"));
        generateOrders().remove(orderToDelete);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public OrderDto createOrder(@RequestBody CartDto cartDto) {
        long maxId = generateOrders().stream()
                .map(OrderDto::getId)
                .max(Long::compareTo)
                .orElse(0L);
        String productIds = cartDto.getProducts().stream()
                .map(productDto -> productDto.getId().toString())
                .collect(Collectors.joining(","));
        return new OrderDto((maxId + 1), 1L, 2L, LocalDateTime.now(), productIds,
                StatusEnum.DELIVERED, LocalDateTime.now().plusDays(5), orderValue(cartDto.getProducts()));
    }

    private BigDecimal orderValue(List<ProductDto> products) {
        return products.stream()
                .map(ProductDto::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @PutMapping(consumes = APPLICATION_JSON_VALUE)
    public OrderDto updateOrder(@RequestBody OrderDto orderDto) {
        if (orderDto.getId() == null) {
            throw new RuntimeException("Order id can't be null.");
        }
        return orderDto;
    }

}
