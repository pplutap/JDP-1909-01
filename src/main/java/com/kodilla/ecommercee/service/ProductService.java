package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Product getProduct(final Long id){
        return productRepository.findById(id).orElse(null);
    }

    public Product saveProduct(final Product product){
        return productRepository.save(product);
    }

    public void deleteProduct(final Long id){
        productRepository.deleteById(id);
    }

}
