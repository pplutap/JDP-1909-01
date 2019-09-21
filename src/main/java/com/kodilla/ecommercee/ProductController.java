package com.kodilla.ecommercee;

import com.kodilla.ecommercee.dto.ProductDto;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/products")
public class ProductController {

    private List<ProductDto> sampleProducts = new ArrayList<>();

    public ProductController() {
        initializeSampleProducts();
    }

    private void initializeSampleProducts() {
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

    @GetMapping
    public List<ProductDto> getAllProducts() {
        return sampleProducts;
    }

    @GetMapping("/{id}")
    public ProductDto getProduct(@PathVariable Long id) {
        return sampleProducts.stream()
                .filter(productDto -> productDto.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product not found."));
    }

    @PostMapping
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        long maxId = sampleProducts.stream()
                .map(ProductDto::getId)
                .max(Long::compareTo)
                .orElse(0L);
        productDto.setId(maxId + 1);
        sampleProducts.add(productDto);
        return productDto;
    }

    @PutMapping
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
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

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        ProductDto deletedProductDto = sampleProducts.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product not found."));
        sampleProducts.remove(deletedProductDto);
    }

}
