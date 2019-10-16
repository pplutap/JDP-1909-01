package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.dto.CartDto;
import com.kodilla.ecommercee.dto.OrderDto;
import com.kodilla.ecommercee.dto.ProductDto;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.mapper.OrderMapper;
import com.kodilla.ecommercee.service.OrderService;
import com.kodilla.ecommercee.service.UserService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/orders")
@CrossOrigin("*")
public class OrderController {

    private final OrderMapper orderMapper;
    private final CartMapper cartMapper;
    private final OrderService orderService;
    private final UserService userService;

    public OrderController(final OrderMapper orderMapper, final CartMapper cartMapper,
                           final OrderService orderService, final UserService userService) {
        this.orderMapper = orderMapper;
        this.cartMapper = cartMapper;
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping
    public List<OrderDto> getOrders() {
        return orderMapper.mapToOrderDtoList(orderService.getAllOrders());
    }

    @GetMapping("/{id}")
    public OrderDto getOrderById(@PathVariable Long id) {
        return orderMapper.mapToOrderDto(orderService.getOrder(id));
    }

    @GetMapping("{id}/products/")
    public List<ProductDto> getOrderedProducts(@PathVariable Long id) {
        return null;
    }

    @PutMapping("{id}/products/{productId}")
    public List<ProductDto> addProductToOrder(@PathVariable Long id, @PathVariable Long productId) {
        return null;
    }

    @DeleteMapping("{id}/products/{productId}")
    public List<ProductDto> removeProductFroOrder(@PathVariable Long id, @PathVariable Long productId) {
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public OrderDto createOrder(@RequestBody CartDto cartDto, @RequestParam Long buyerId, @RequestParam Long sellerId,
                                @RequestParam Integer daysToDeliver) {
        Cart cart = cartMapper.mapToCart(cartDto);
        User buyer = userService.getUser(buyerId);
        User seller = userService.getUser(sellerId);
        return orderMapper.mapToOrderDto(orderService.createOrder(cart, buyer, seller, daysToDeliver));
    }

    @PutMapping(consumes = APPLICATION_JSON_VALUE)
    public OrderDto updateOrder(@RequestBody OrderDto orderDto) {
        return orderMapper.mapToOrderDto(orderService.updateOrder(orderMapper.mapToOrder(orderDto, Collections.emptyList())));
    }

}
