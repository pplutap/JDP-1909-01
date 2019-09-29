package com.kodilla.ecommercee.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
//@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @NonNull
    @Column(name="UserID", unique = true)
    private Long userId;

    @Column(name = "UserName")
    private String userName;

    @NonNull
    @Column(name = "Status")
    private int status;

    @NonNull
    @Column(name = "UserKey")
    private long userKey;

    @OneToMany (
            targetEntity =  Order.class,
            mappedBy = "buyerId",
            fetch = FetchType.LAZY
    )
    private List<Order> ordersAsBuyer = new ArrayList<>();

    @OneToMany (
            targetEntity =  Order.class,
            mappedBy = "sellerId",
            fetch = FetchType.LAZY
    )
    private List<Order> ordersAsSeller = new ArrayList<>();
}
