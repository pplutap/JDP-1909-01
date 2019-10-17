package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.dto.OrderDto;
import com.kodilla.ecommercee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    private final UserService userService;

    @Autowired
    public OrderMapper(final UserService userService) {
        this.userService = userService;
    }

    public Order mapToOrder(final OrderDto orderDto, final List<Product> products) {
        User buyer = userService.getUserByName(orderDto.getBuyerName());
        User seller = userService.getUserByName(orderDto.getSellerName());
        return new Order(
                orderDto.getId(),
                buyer,
                seller,
                orderDto.getPurchaseDate(),
                products,
                orderDto.getStatus(),
                orderDto.getDeliveryDate()
        );
    }

    public List<OrderDto> mapToOrderDtoList(final List<Order> orders) {
        return orders.stream()
                .map(this::mapToOrderDto)
                .collect(Collectors.toList());
    }

    public OrderDto mapToOrderDto(final Order order) {
        return new OrderDto(
                order.getId(),
                order.getBuyer().getUserName(),
                order.getSeller().getUserName(),
                order.getPurchaseDate(),
                order.getStatus(),
                order.getDeliveryDate(),
                calculateProductsValue(order.getProducts())
        );
    }

    private BigDecimal calculateProductsValue(final List<Product> products) {
        BigDecimal sum = products.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return sum;
    }

}
