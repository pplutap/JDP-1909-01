package com.kodilla.ecommercee;

import com.kodilla.ecommercee.dto.CartDto;
import com.kodilla.ecommercee.dto.OrderDto;
import com.kodilla.ecommercee.dto.ProductDto;
import com.kodilla.ecommercee.service.OrderService;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/orders")
public class OrderController {

    public OrderController() {
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<OrderDto> getOrders(){
        return OrderService.getOrders();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{orderId}")
    public OrderDto getOrderById(@PathVariable Long orderId) {
        return OrderService.getOrders().stream()
                .filter(order -> order.getId().equals(orderId))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Order not found"));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{orderId}")
    public void deleteOrder(@PathVariable Long orderId){
        OrderDto orderToDelete = OrderService.getOrders().stream()
                .filter(product -> product.getId().equals(orderId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Order non-existent"));
        OrderService.getOrders().remove(orderToDelete);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
    public OrderDto createOrder(@RequestBody CartDto cartDto) {
        long maxId = OrderService.getOrders().stream()
                .map(OrderDto::getId)
                .max(Long::compareTo)
                .orElse(0L);
        OrderDto newOrder = new OrderDto((maxId+1), 1L, 2L, LocalDateTime.now(), cartDto.getProducts(),
                "AWAITING PAYMENT", LocalDateTime.now().plusDays(5));
        OrderService.getOrders().add(newOrder);
        return newOrder;
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = APPLICATION_JSON_VALUE)
    public OrderDto updateOrder(@RequestBody OrderDto orderDto) {
        if (orderDto.getId() == null) {
            throw new RuntimeException("Order id can't be null.");
        }
        OrderDto oldProductDto = OrderService.getOrders().stream()
                .filter(order -> order.getId().equals(orderDto.getId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Order non-existent."));
        OrderService.getOrders().remove(oldProductDto);
        OrderService.getOrders().add(orderDto);
        return orderDto;
    }
}
