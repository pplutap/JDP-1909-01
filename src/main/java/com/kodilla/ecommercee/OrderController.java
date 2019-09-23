package com.kodilla.ecommercee;

import com.kodilla.ecommercee.dto.CartDto;
import com.kodilla.ecommercee.dto.OrderDto;
import com.kodilla.ecommercee.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/orders")
public class OrderController {

    ProductController productController = new ProductController();
    private List<OrderDto> orders;

    public OrderController() {
        generateOrders();
        System.out.println();
    }

    private List<OrderDto> generateOrders() {
        orders = new ArrayList<>();
        LocalDateTime purchaseDate = LocalDateTime.of(2019, Month.SEPTEMBER, 1, 10, 30, 0);
        LocalDateTime deliveryDate = purchaseDate.plusDays(5);
        List<ProductDto> products = productController.getAllProducts();
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


    @RequestMapping(method = RequestMethod.GET, value = "getOrders")
    public List<OrderDto> getOrders(){
        return orders;
    }

    @RequestMapping(method = RequestMethod.GET, value = "getOrder")
    public OrderDto getOrderById(@RequestParam(name = "id") Long orderId) {
        return orders.stream()
                .filter(order -> order.getId().equals(orderId))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Order not found"));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteOrder")
    public void deleteOrder(@RequestParam(name = "id") Long orderId){
        OrderDto orderToDelete = orders.stream()
                .filter(product -> product.getId().equals(orderId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Order non-existent"));
        orders.remove(orderToDelete);
    }

    @RequestMapping(method = RequestMethod.POST, value = "createOrder", consumes = APPLICATION_JSON_VALUE)
    public void createOrder(@RequestBody CartDto cartDto) {
        long maxId = orders.stream()
                .map(OrderDto::getId)
                .max(Long::compareTo)
                .orElse(0L);
        orders.add(new OrderDto(maxId, 1L, 2L, LocalDateTime.now(), cartDto.getProducts(),
                "AWAITING PAYMENT", LocalDateTime.now().plusDays(5)));
    }
}
