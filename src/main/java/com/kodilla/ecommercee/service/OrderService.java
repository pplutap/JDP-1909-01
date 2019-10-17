package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.Email;
import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.StatusEnum;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.exception.BadRequestException;
import com.kodilla.ecommercee.exception.NotFoundException;
import com.kodilla.ecommercee.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class OrderService {

    private final String SUBJECT = "Potwierdzenie złożenia zamówienia nr ";
    private final String BUYER_MESSAGE = "Drogi <<<buyerName>>>\nPotwierdzamy złożenie zamówienia nr <<<orderNo>>> " +
            "u <<<sellerName>>>.\nEcommercee App";
    private final String SELLER_MESSAGE = "Drogi <<<sellerName>>>\n<<<buyerName>>> właśnie złożył u " +
            "Ciebie zamówienie nr <<<orderNo>>>.\nEcommercee App";

    private final OrderRepository orderRepository;
    private final EmailService emailService;

    @Autowired
    public OrderService(final OrderRepository orderRepository, final EmailService emailService) {
        this.orderRepository = orderRepository;
        this.emailService = emailService;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrder(final Long id) {
        return orderRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Order with id=" + id + " doesn't exist.")
        );
    }

    public List<Product> getOrderProducts(final Long id) {
        return getOrder(id).getProducts();
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
        Order persistedOrder = orderRepository.save(newOrder);
        Email buyerEmail = Email.builder()
                .mailTo(persistedOrder.getBuyer().getEmailAddress())
                .subject(SUBJECT + persistedOrder.getId())
                .message(BUYER_MESSAGE
                        .replace("<<<buyerName>>>", persistedOrder.getBuyer().getUserName())
                        .replace("<<<orderNo>>>", persistedOrder.getId().toString())
                        .replace("<<<sellerName>>>", persistedOrder.getSeller().getUserName())
                )
                .build();
        Email sellerEmail = Email.builder()
                .mailTo(persistedOrder.getBuyer().getEmailAddress())
                .subject(SUBJECT + persistedOrder.getId())
                .message(SELLER_MESSAGE
                        .replace("<<<buyerName>>>", persistedOrder.getBuyer().getUserName())
                        .replace("<<<orderNo>>>", persistedOrder.getId().toString())
                        .replace("<<<sellerName>>>", persistedOrder.getSeller().getUserName())
                )
                .build();
        emailService.send(buyerEmail);
        emailService.send(sellerEmail);
        return persistedOrder;
    }

    public Order updateOrder(final Long orderId, final StatusEnum status, final LocalDate deliveryDate) {
        if (orderId == null) {
            throw new BadRequestException("Id of updated order must not be null.");
        }
        Order persistedOrder = getOrder(orderId);
        persistedOrder.setStatus(status);
        persistedOrder.setDeliveryDate(deliveryDate);
        return orderRepository.save(persistedOrder);
    }

    public List<Product> addProductToOrder(final Long orderId, final Product product) {
        Order persistedOrder = getOrder(orderId);
        persistedOrder.getProducts().add(product);
        return orderRepository.save(persistedOrder).getProducts();
    }

    public List<Product> removeProductFromOrder(final Long orderId, final Long productId) {
        Order persistedOrder = getOrder(orderId);
        persistedOrder.getProducts().removeIf(
                product -> product.getId().equals(productId)
        );
        return orderRepository.save(persistedOrder).getProducts();
    }
}
