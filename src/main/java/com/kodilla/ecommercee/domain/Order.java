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
@Entity
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
            name = "JOIN_PRODUCT_EMPLOYEE",
            joinColumns = {@JoinColumn(name = "id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "id", referencedColumnName = "id")}
    )
    private List<Product> products;

    @NotNull
    private String status;

    @NotNull
    private LocalDateTime deliveryDate;
}
