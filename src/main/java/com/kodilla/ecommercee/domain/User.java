package com.kodilla.ecommercee.domain;
import lombok.*;

import javax.persistence.*;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Users")
public class User {

    @Id
    @GeneratedValue
    @NonNull
    @Column(unique = true)
    private Long userId;

    @NonNull
    @Column
    private String userName;

    @NonNull
    @Column
    private Integer status;

    @NonNull
    @Column
    private Long userKey;

}
