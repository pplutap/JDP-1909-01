package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.dto.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Orders")
public class Order {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "orderId")
    private Long id;


    @ManyToOne
    @JoinColumn(name = "UserID")
    @Column(name = "buyerId")
    private Long buyerId;

    @ManyToOne
    @JoinColumn(name = "UserID")
    @Column(name = "sellerId")
    private Long sellerId;

    @Column(name = "purchaseDate")
    private LocalDateTime purchaseDate;

    @Column(name = "products")
    private List<ProductDto> products;

    @Column(name = " status")
    private String status;

    @Column(name = "deliveryDate")
    private LocalDateTime deliveryDate;
}
