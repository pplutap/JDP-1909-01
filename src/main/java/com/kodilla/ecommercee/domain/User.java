package com.kodilla.ecommercee.domain;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Column(unique = true)
    private String userName;

    @NotNull
    private Integer status;

    @NotNull
    @Column(unique = true)
    private Long userKey;

}
