package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.StatusEnum;
import com.kodilla.ecommercee.dto.CartDto;
import com.kodilla.ecommercee.dto.OrderDto;
import com.kodilla.ecommercee.dto.ProductDto;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    public OrderController() {
    }

    private List<ProductDto> initializeSampleProducts() {
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

    private List<OrderDto> generateOrders() {
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
            orders.add(new OrderDto(orderId, 1L, 2L, purchaseDate, productsList, StatusEnum.DELIVERED, deliveryDate, orderValue(productsList)));
        }
        return orders;
    }

    private BigDecimal orderValue(List<ProductDto> products) {
        return products.stream()
                .map(ProductDto::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<OrderDto> getOrders(){
        return generateOrders();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{orderId}")
    public OrderDto getOrderById(@PathVariable Long orderId){
        return generateOrders().stream()
                .filter(order -> order.getId().equals(orderId))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Order not found"));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{orderId}")
    public void deleteOrder(@PathVariable Long orderId) {
        OrderDto orderToDelete = generateOrders().stream()
                .filter(product -> product.getId().equals(orderId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Order non-existent"));
        generateOrders().remove(orderToDelete);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
    public OrderDto createOrder(@RequestBody CartDto cartDto) {
        long maxId = generateOrders().stream()
                .map(OrderDto::getId)
                .max(Long::compareTo)
                .orElse(0L);
        OrderDto newOrder = new OrderDto((maxId+1), 1L, 2L, LocalDateTime.now(), cartDto.getProducts(),
                StatusEnum.DELIVERED, LocalDateTime.now().plusDays(5), orderValue(cartDto.getProducts()));
        generateOrders().add(newOrder);
        return newOrder;
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = APPLICATION_JSON_VALUE)
    public OrderDto updateOrder(@RequestBody OrderDto orderDto) {
        if (orderDto.getId() == null) {
            throw new RuntimeException("Order id can't be null.");
        }
        OrderDto oldProductDto = generateOrders().stream()
                .filter(order -> order.getId().equals(orderDto.getId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Order non-existent."));
        generateOrders().remove(oldProductDto);
        generateOrders().add(orderDto);
        return orderDto;
    }
}
