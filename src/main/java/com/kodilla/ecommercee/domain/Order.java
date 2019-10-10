package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity(name = "shopping_order")
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "buyerId")
    private User buyer;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "sellerId")
    private User seller;

    @NotNull
    private LocalDateTime purchaseDate;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "JOIN_ORDER_PRODUCT",
            joinColumns = {@JoinColumn(name = "orderId", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "productId", referencedColumnName = "id")}
    )
    private List<Product> products;

    @NotNull
    private String status;

    @NotNull
    private LocalDateTime deliveryDate;

}
