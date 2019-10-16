package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.StatusEnum;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.exception.BadRequestException;
import com.kodilla.ecommercee.exception.NotFoundException;
import com.kodilla.ecommercee.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(final OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
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

    public Order createOrder(final Cart cart, final User buyer, final User seller, final Integer daysToDeliver) {
        Order newOrder = Order.builder()
                .buyer(buyer)
                .seller(seller)
                .purchaseDate(LocalDate.now())
                .products(cart.getProducts())
                .status(StatusEnum.REGISTERED)
                .deliveryDate(LocalDate.now().plus(daysToDeliver, DAYS))
                .build();
        return orderRepository.save(newOrder);
    }

    public Order updateOrder(final Order order) {
        if (order.getId() == null) {
            throw new BadRequestException("Id of updated order must not be null.");
        }
        Order persistedOrder = getOrder(order.getId());
        persistedOrder.setStatus(order.getStatus());
        persistedOrder.setDeliveryDate(order.getDeliveryDate());
        return orderRepository.save(persistedOrder);
    }

}
