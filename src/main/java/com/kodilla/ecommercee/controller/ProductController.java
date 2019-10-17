package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.ProductDto;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/products")
@CrossOrigin("*")
public class ProductController {

    @GetMapping
    public List<ProductDto> getAllProducts(@RequestParam(required = false) Long groupId) {
        if (groupId == null) {
            return getSampleData();
        }
        return getSampleData().stream()
                .filter(productDto -> productDto.getGroupId().equals(groupId))
                .collect(Collectors.toList());
    }

    private List<ProductDto> getSampleData() {
        String[] names = {"kurtka zimowa", "płaszcz", "buty", "rękawiczki", "sandały", "zegarek", "złote kolczyki"};
        String description = "Pellentesque tempus interdum quam ut rhoncus. Donec ullamcorper turpis dolor.";
        int[] prices = {100, 150, 100, 50, 80, 250, 300};
        long[] groupIds = {1, 1, 4, 2, 4, 3, 3};
        List<ProductDto> sampleProducts = new ArrayList<>();
        for (int id = 0; id < names.length; id++) {
            sampleProducts.add(new ProductDto(
                    (long) (id + 1),
                    names[id],
                    description,
                    new BigDecimal(prices[id]),
                    groupIds[id]
            ));
        }
        return sampleProducts;
    }

    @GetMapping("/{id}")
    public ProductDto getProduct(@PathVariable Long id) {
        return getSampleData().get(0);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ProductDto createProduct(@RequestBody ProductDto product) {
        return product;
    }

    @PutMapping(consumes = APPLICATION_JSON_VALUE)
    public ProductDto updateProduct(@RequestBody ProductDto product) {
        return product;
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
    }

}
