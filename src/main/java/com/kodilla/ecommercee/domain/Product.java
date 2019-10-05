package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class Product {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String name;

    private String description;

    @NotNull
    private BigDecimal price;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "groupId")
    private Group group;

    @ManyToMany(cascade = CascadeType.PERSIST, mappedBy = "products")
    private List<Order> orders;

    public void clearId() {
        this.id = null;
    }
}
