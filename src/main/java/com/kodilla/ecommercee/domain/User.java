package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Column(unique = true)
    private String userName;

    @NotNull
    private String password;

    @NotNull
    private Integer status;

    @NotNull
    @Column(unique = true)
    private Long userKey;

    private String sessionKey;

    private LocalDateTime sessionKeyExpireAt;

}
