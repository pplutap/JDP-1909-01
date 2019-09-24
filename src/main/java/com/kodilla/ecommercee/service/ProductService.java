package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.dto.ProductDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private List<ProductDto> sampleProducts = new ArrayList<>();

    public ProductService() {
        String[] names = {"kurtka zimowa", "płaszcz", "buty", "rękawiczki", "sandały", "zegarek", "złote kolczyki",
                "krawat", "szelki", "pasek", "koszulka", "bezrękawnik", "apaszka"};
        String descripiton = "Pellentesque tempus interdum quam ut rhoncus. Donec ullamcorper turpis dolor. Donec " +
                "euismod pretium eros et eleifend. Aliquam vulputate faucibus lorem non auctor. Vivamus erat turpis, " +
                "molestie a nisl non, scelerisque luctus enim. Nunc mi mi, laoreet ac mollis nec, pharetra sit amet " +
                "tortor. Vivamus a bibendum purus.";
        int[] prices = {100, 150, 100, 50, 80, 250, 300, 50, 40, 100, 50, 30, 20};
        long[] groupIds = {1, 1, 4, 2, 4, 3, 3, 2, 2, 2, 1, 1, 2};
        for (int id = 0; id < names.length; id++) {
            sampleProducts.add(new ProductDto(
                    (long) (id + 1),
                    names[id],
                    descripiton,
                    new BigDecimal(prices[id]),
                    groupIds[id]
            ));
        }
    }

    public List<ProductDto> getProducts() {
        return sampleProducts;
    }

    public ProductDto getProduct(final Long id) {
        return sampleProducts.stream()
                .filter(productDto -> productDto.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product not found."));
    }

    public ProductDto createProduct(final ProductDto productDto) {
        long maxId = sampleProducts.stream()
                .map(ProductDto::getId)
                .max(Long::compareTo)
                .orElse(0L);
        productDto.setId(maxId + 1);
        sampleProducts.add(productDto);
        return productDto;
    }

    public ProductDto updateProduct(final ProductDto productDto) {
        if (productDto.getId() == null) {
            throw new RuntimeException("Product id can't be null.");
        }
        ProductDto oldProductDto = sampleProducts.stream()
                .filter(product -> product.getId().equals(productDto.getId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product not found."));
        sampleProducts.remove(oldProductDto);
        sampleProducts.add(productDto);
        return productDto;
    }

    public void deleteProduct(final Long id) {
        ProductDto deletedProductDto = sampleProducts.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product not found."));
        sampleProducts.remove(deletedProductDto);
    }

}
