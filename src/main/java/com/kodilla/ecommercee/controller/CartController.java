package com.kodilla.ecommercee.controller;

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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/carts")
@CrossOrigin("*")
public class CartController {

    private List<ProductDto> sampleCart;

    private List<ProductDto> products;

    public CartController() {
        products = getSampleData();
        sampleCart = new ArrayList<>(Arrays.asList(products.get(0), products.get(2), products.get(5)));
    }

    private List<ProductDto> getSampleData() {
        String[] names = {"kurtka zimowa", "płaszcz", "buty", "rękawiczki", "sandały", "zegarek", "złote kolczyki"};
        String description = "Pellentesque tempus interdum quam ut rhoncus. Donec ullamcorper turpis dolor.";
        int[] prices = {100, 150, 100, 50, 80, 250, 300};
        long[] groupIds = {1, 1, 4, 2, 4, 3, 3};
        List<ProductDto> sampleProducts = new ArrayList<>();
        for (int id = 0; id < names.length; id++) {
            sampleProducts.add(new ProductDto(
                    (long) (id + 1),
                    names[id],
                    description,
                    new BigDecimal(prices[id]),
                    groupIds[id]
            ));
        }
        return sampleProducts;
    }

    @GetMapping("{id}/products")
    public List<ProductDto> getProductsFromCart(@PathVariable Long id) {
        return sampleCart;
    }

    private List<ProductDto> getSampleProducts() {
        return new ArrayList<>(
                Arrays.asList(
                        new ProductDto(1L, "kurtka", "Pellentesque tempus interdum.", new BigDecimal(100), 1L),
                        new ProductDto(2L, "płaszcz", "Pellentesque tempus interdum.", new BigDecimal(150), 1L),
                        new ProductDto(3L, "buty", "Pellentesque tempus interdum.", new BigDecimal(80), 2L)
                )
        );
    }

    @PostMapping
    public CartDto createCart() {
        return new CartDto(5L, new ArrayList<>());
    }

    @PutMapping("/{id}/products/{productId}")
    public List<ProductDto> addProductToCart(@PathVariable Long id, @PathVariable Long productId) {
        ProductDto product = products.stream()
                .filter(productDto -> productDto.getId().equals(productId))
                .collect(Collectors.toList()).get(0);
        sampleCart.add(product);
        return sampleCart;
    }

    @DeleteMapping("/{id}/products/{productId}")
    public List<ProductDto> removeProductFromCart(@PathVariable Long id, @PathVariable Long productId) {
        sampleCart = sampleCart.stream()
                .filter(productDto -> !productDto.getId().equals(productId))
                .collect(Collectors.toList());
        return sampleCart;
    }

    @PostMapping("/{id}/orders")
    public OrderDto createOrder(@PathVariable Long id, @RequestBody OrderDto order) {
        return order;
    }

}
