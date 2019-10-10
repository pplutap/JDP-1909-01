package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.StatusEnum;
import com.kodilla.ecommercee.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql({
        "/sql/groups.sql",
        "/sql/products.sql",
        "/sql/users.sql",
        "/sql/orders.sql"
})
@Transactional
public class OrderRepositoryTestSuite {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void testCreateNewOrderIfGivenValidOrderObject() {
        //Given
        Product product1 = productRepository.getOne(11L);
        Product product2 = productRepository.getOne(12L);
        User buyer = userRepository.getOne(21L);
        User seller = userRepository.getOne(22L);
        Order newOrder = Order.builder()
                .buyer(buyer)
                .seller(seller)
                .purchaseDate(LocalDateTime.of(2019, 9, 1, 12, 15))
                .products(Arrays.asList(product1, product2))
                .status(StatusEnum.DELIVERED)
                .deliveryDate(LocalDateTime.of(2019, 9, 1, 12, 15))
                .build();

        //When
        long ordersCountBeforeCreation = orderRepository.count();
        Order createdOrder = orderRepository.save(newOrder);
        long ordersCountAfterCreation = orderRepository.count();
        List<Order> retrievedOrders = orderRepository.findAll();

        //Then
        assertThat(ordersCountAfterCreation, is(equalTo(ordersCountBeforeCreation + 1)));
        assertThat(retrievedOrders, hasItem(createdOrder));
    }

    @Test
    public void testThrowExceptionIfGivenInvalidOrderObjectToSave() {
        //Given
        Order invalidOrder = Order.builder()
                .products(new ArrayList<>())
                .build();
        Exception exception = null;

        //When
        try {
            orderRepository.save(invalidOrder);
            entityManager.flush();
        } catch (Exception e) {
            exception = e;
        }

        //Then
        assertThat(exception, is(notNullValue()));
    }

    @Test
    public void testGetAllOrders() {
        //Given
        Order order31 = orderRepository.getOne(31L);
        Order order32 = orderRepository.getOne(32L);
        Order order33 = orderRepository.getOne(33L);
        Order order34 = orderRepository.getOne(34L);
        Order order35 = orderRepository.getOne(35L);

        //When
        List<Order> retrievedOrders = orderRepository.findAll();
        int orderCount = retrievedOrders.size();

        //Then
        assertThat(orderCount, is(5));
        assertThat(retrievedOrders, containsInAnyOrder(order31, order32, order33, order34, order35));
    }

    @Test
    public void testGetOrderIfGivenValidId() {
        //Given
        Order order = orderRepository.getOne(31L);

        //When
        Optional<Order> retrievedOrder = orderRepository.findById(31L);

        //Then
        assertThat(retrievedOrder.isPresent(), is(true));
        assertThat(retrievedOrder.get(), is(order));
    }

    @Test
    public void testUpdateOrderIfGivenValidOrderObject() {
        //Given
        Order order = orderRepository.getOne(31L);
        order.setStatus(StatusEnum.CANCELED);

        //When
        Order updatedOrder = orderRepository.save(order);

        //Then
        assertThat(updatedOrder.getId(), is(31L));
        assertThat(updatedOrder.getStatus(), is(StatusEnum.CANCELED));
    }

    @Test
    public void testDeleteOrderIfGivenValidOrderId() {
        //Given
        Order order32 = orderRepository.getOne(32L);
        Order order33 = orderRepository.getOne(33L);
        Order order34 = orderRepository.getOne(34L);
        Order order35 = orderRepository.getOne(35L);

        //When
        orderRepository.deleteById(31L);
        List<Order> retrievedOrders = orderRepository.findAll();

        //Then
        assertThat(retrievedOrders, containsInAnyOrder(order32, order33, order34, order35));
    }

}