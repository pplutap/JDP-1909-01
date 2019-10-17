package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.dto.CartDto;
import com.kodilla.ecommercee.dto.OrderDto;
import com.kodilla.ecommercee.dto.ProductDto;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.mapper.OrderMapper;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.service.OrderService;
import com.kodilla.ecommercee.service.ProductService;
import com.kodilla.ecommercee.service.UserService;
import lombok.RequiredArgsConstructor;
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

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/orders")
@CrossOrigin("*")
@RequiredArgsConstructor
public class OrderController {

    private final OrderMapper orderMapper;
    private final CartMapper cartMapper;
    private final ProductMapper productMapper;
    private final OrderService orderService;
    private final UserService userService;
    private final ProductService productService;

    @GetMapping
    public List<OrderDto> getOrders() {
        return orderMapper.mapToOrderDtoList(orderService.getAllOrders());
    }

    @GetMapping("/{id}")
    public OrderDto getOrderById(@PathVariable Long id) {
        return orderMapper.mapToOrderDto(orderService.getOrder(id));
    }

    @GetMapping("{id}/products")
    public List<ProductDto> getOrderedProducts(@PathVariable Long id) {
        return productMapper.mapToProductDtoList(orderService.getOrderProducts(id));
    }

    @PutMapping("{id}/products/{productId}")
    public List<ProductDto> addProductToOrder(@PathVariable Long id, @PathVariable Long productId) {
        Product product = productService.getProduct(productId);
        return productMapper.mapToProductDtoList(orderService.addProductToOrder(id, product));
    }

    @DeleteMapping("{id}/products/{productId}")
    public List<ProductDto> removeProductFroOrder(@PathVariable Long id, @PathVariable Long productId) {
        return productMapper.mapToProductDtoList(orderService.removeProductFromOrder(id, productId));
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public OrderDto createOrder(@RequestBody CartDto cartDto, @RequestParam String buyerName,
                                @RequestParam String sellerName, @RequestParam Integer daysToDeliver) {
        Cart cart = cartMapper.mapToCart(cartDto);
        User buyer = userService.getUserByName(buyerName);
        User seller = userService.getUserByName(sellerName);
        return orderMapper.mapToOrderDto(orderService.createOrder(cart, buyer, seller, daysToDeliver));
    }

    @PutMapping(consumes = APPLICATION_JSON_VALUE)
    public OrderDto updateOrder(@RequestBody OrderDto orderDto) {
        return orderMapper.mapToOrderDto(orderService.updateOrder(
                orderDto.getId(),
                orderDto.getStatus(),
                orderDto.getDeliveryDate()
        ));
    }

}
