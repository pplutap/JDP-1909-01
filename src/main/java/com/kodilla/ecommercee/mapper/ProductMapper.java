package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.controller.GroupController;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    @Autowired
    GroupController groupController;

    public Product mapToProduct(final ProductDto productDto){
        return new Product(
                productDto.getId(),
                productDto.getName(),
                productDto.getDescription(),
                productDto.getPrice(),
                groupController.getGroup(productDto.getGroupId()));
    }

    public ProductDto mapToTaskDto(final Product product){
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getGroup().getId());
    }

    public List<ProductDto> mapToTaskDtoList(final List<Product> taskList){
        return taskList.stream()
                .map(p -> new ProductDto(p.getId(),p.getName(),p.getDescription(),p.getPrice(),p.getGroup().getId()))
                .collect(Collectors.toList());
    }
    //    private Long id;
    //    private String name;
    //    private String description;
    //    private BigDecimal price;
    //    private Long groupId;

    //    @Id
    //    @GeneratedValue
    //    private Long id;
    //
    //    @NotNull
    //    @Column(unique = true)
    //    private String name;
    //
    //    @Column(length = 2000)
    //    private String description;
    //
    //    @NotNull
    //    private BigDecimal price;
    //
    //    @NotNull
    //    @ManyToOne
    //    @JoinColumn(name = "groupId")
    //    private Group group;
}
