package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.CartDto;
import com.kodilla.ecommercee.dto.OrderDto;
import com.kodilla.ecommercee.dto.ProductDto;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/v1/carts")
public class CartController {

    @GetMapping("{id}/products")
    public List<ProductDto> getProductsFromCart(@PathVariable Long id) {
        return getSampleProducts();
    }

    private List<ProductDto> getSampleProducts() {
        return new ArrayList<>(
                Arrays.asList(
                        new ProductDto(1L, "kurtka", "Pellentesque tempus interdum.", new BigDecimal(100), 1L),
                        new ProductDto(1L, "płaszcz", "Pellentesque tempus interdum.", new BigDecimal(150), 1L),
                        new ProductDto(1L, "buty", "Pellentesque tempus interdum.", new BigDecimal(80), 2L)
                )
        );
    }

    @PostMapping
    public CartDto createCart() {
        return new CartDto(5L, new ArrayList<>());
    }

    @PutMapping("/{id}/products/{productId}")
    public List<ProductDto> addProductToCart(@PathVariable Long id, @PathVariable Long productId) {
        return getSampleProducts();
    }

    @DeleteMapping("/{id}/products/{productId}")
    public List<ProductDto> removeProductFromCart(@PathVariable Long id, @PathVariable Long productId) {
        return getSampleProducts();
    }

    @PostMapping("/{id}/orders")
    public OrderDto createOrder(@PathVariable Long id, @RequestBody OrderDto order) {
        return order;
    }

}
