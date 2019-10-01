package com.kodilla.ecommercee.domain;
import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue
    @NonNull
    private Long id;

    @NonNull
    @Column(unique = true)
    private String userName;

    @NonNull
    private Integer status;

    @NonNull
    @Column(unique = true)
    private Long userKey;

}
