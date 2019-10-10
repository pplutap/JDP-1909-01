package com.kodilla.ecommercee.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
//import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity(name="ShoppingOrder")
public class Order  {

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
    private List<Product> products = new ArrayList<>();

    @NotNull
    private StatusEnum status;

    @NotNull
    private LocalDateTime deliveryDate;

    @Builder
    public Order(User buyer, User seller, LocalDateTime purchaseDate, List<Product> products, StatusEnum status, LocalDateTime deliveryDate){
        this.buyer = buyer;
        this.seller=seller;
        this.purchaseDate=purchaseDate;
        this.products= products;
        this.status=status;
        this.deliveryDate=deliveryDate;
    }
}
