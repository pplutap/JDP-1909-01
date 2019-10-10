package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.Product;
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
        Order newOrder = new Order(
                null,
                buyer,
                seller,
                LocalDateTime.of(2019, 9, 1, 12, 15),
                Arrays.asList(product1, product2),
                "AWAITING PAYMENT",
                LocalDateTime.of(2019, 9, 1, 12, 15)
        );

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
    public void testThrowExceptionIfGivenInvalidOrderObject() {
        //Given
        Order invalidOrder = new Order(null, null, null, null, new ArrayList<>(), null, null);

        //When
        Exception exception = null;
        try {
            orderRepository.save(invalidOrder);
            entityManager.flush();
        } catch (Exception e) {
            exception = e;
        }

        //Then
        assertThat(exception, is(notNullValue()));
    }

}