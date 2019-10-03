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
    private Long id;

    @ManyToOne
    @JoinColumn(name = "buyerId")
    private User buyer;

    @ManyToOne
    @JoinColumn(name = "sellerId")
    private User seller;

    @Column
    private LocalDateTime purchaseDate;

    @OneToMany
    @Column
    private List<Product> products;

    @Column
    private String status;

    @Column(name = "deliveryDate")
    private LocalDateTime deliveryDate;
}
