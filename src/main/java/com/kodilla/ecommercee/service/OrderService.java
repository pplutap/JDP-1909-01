package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.exception.NotFoundException;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public OrderService(final OrderRepository orderRepository, final UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrder(final Long id) {
        return orderRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Order with id=" + id + " doesn't exist.")
        );
    }

    public void deleteOrder(final Long id) {
        orderRepository.deleteById(id);
    }

    public Order createOrder(final Cart cart, final Long buyerId, final Long sellerId, final Integer daysToDeliver) {
        User buyer = userRepository.findById(buyerId).orElseThrow(
                () -> new RuntimeException("User with id=" + buyerId + " doesn't exist.")
        );
        User seller = userRepository.findById(sellerId).orElseThrow(
                () -> new RuntimeException("User with id=" + sellerId + " doesn't exist.")
        );
        Order newOrder = Order.builder()
                .buyer(buyer)
                .seller(seller)
                .purchaseDate(LocalDate.now())
        return orderRepository.save(newOrder);
    }

}
