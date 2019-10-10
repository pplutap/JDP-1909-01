package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
