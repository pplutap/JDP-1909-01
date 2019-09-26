package com.kodilla.ecommercee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CartDto {

    private Long id;
    private List<ProductDto> products;

    public void setId(Long id) {
        this.id = id;
    }

}
