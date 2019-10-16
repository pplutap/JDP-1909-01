package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.dto.ProductDto;
import com.kodilla.ecommercee.service.GroupService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    private final GroupService groupService;

    public ProductMapper(final GroupService groupService) {
        this.groupService = groupService;
    }

    public List<Product> mapToProductList(final List<ProductDto> productDtos) {
        return productDtos.stream()
                .map(this::mapToProduct)
                .collect(Collectors.toList());
    }

    public Product mapToProduct(final ProductDto productDto) {
        Group group = groupService.getGroup(productDto.getGroupId());
        return new Product(
                productDto.getId(),
                productDto.getName(),
                productDto.getDescription(),
                productDto.getPrice(),
                group
        );
    }

}
