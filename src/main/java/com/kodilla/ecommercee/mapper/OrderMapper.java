package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.dto.OrderDto;
import com.kodilla.ecommercee.dto.ProductDto;
import com.kodilla.ecommercee.exception.NotFoundException;
import com.kodilla.ecommercee.repository.GroupRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import com.kodilla.ecommercee.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    private final UserRepository userRepository; //zmienić na serwisy
    private final GroupRepository groupRepository; //do wyrzucenia jak już będzie mapper do produktu
    private final OrderService orderService;

    @Autowired
    public OrderMapper(final UserRepository userRepository, final GroupRepository groupRepository,
                       final OrderService orderService) {
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
        this.orderService = orderService;
    }

    public Order mapToOrder(final OrderDto orderDto, final List<ProductDto> productDtos) {
        User buyer = userRepository.findById(orderDto.getBuyerId()).orElseThrow(
                () -> new RuntimeException("User with id=" + orderDto.getBuyerId() + " doesn't exist.")
        );
        User seller = userRepository.findById(orderDto.getSellerId()).orElseThrow(
                () -> new RuntimeException("User with id=" + orderDto.getSellerId() + " doesn't exist.")
        );
        List<Product> products = productDtos.stream()
                .map(productDto -> new Product(
                        productDto.getId(),
                        productDto.getName(),
                        productDto.getDescription(),
                        productDto.getPrice(),
                        groupRepository.findById(productDto.getGroupId()).orElseThrow(
                                () -> new NotFoundException("Group with id=" + productDto.getGroupId() + " doesn't " +
                                        "exist.")
                        )
                ))
                .collect(Collectors.toList());
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
                order.getBuyer().getId(),
                order.getSeller().getId(),
                order.getPurchaseDate(),
                order.getStatus(),
                order.getDeliveryDate(),
                calculateOrderValue(order)
        );
    }

    private BigDecimal calculateOrderValue(final Order order) {
        return order.getProducts().stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
