package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "ShoppingOrder")
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
    private LocalDate purchaseDate;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "JOIN_ORDER_PRODUCT",
            joinColumns = {@JoinColumn(name = "orderId", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "productId", referencedColumnName = "id")}
    )
    private List<Product> products = new ArrayList<>();

    @NotNull
    private StatusEnum status;

    @NotNull
    private LocalDate deliveryDate;

    @Builder
    public Order(User buyer, User seller, LocalDate purchaseDate, List<Product> products, StatusEnum status, LocalDate deliveryDate) {
        this.buyer = buyer;
        this.seller = seller;
        this.purchaseDate = purchaseDate;
        this.products = products;
        this.status = status;
        this.deliveryDate = deliveryDate;
    }

}
