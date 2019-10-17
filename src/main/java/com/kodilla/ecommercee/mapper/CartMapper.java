package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.dto.CartDto;
import org.springframework.stereotype.Component;

@Component
public class CartMapper {

    private final ProductMapper productMapper;

    public CartMapper(final ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    public Cart mapToCart(final CartDto cartDto) {
        return new Cart(
                cartDto.getId(),
                productMapper.mapToProductList(cartDto.getProducts())
        );
    }

}
