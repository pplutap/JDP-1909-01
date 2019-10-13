package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ProductRepositoryTestSuite {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private GroupRepository groupRepository;

    @Test
    public void testSavingNewProductObject() {
        //Given
        Group group = groupRepository.save(new Group(10L, "test group_1"));

        Product testProduct1 = new Product(
                100L,
                "kaftan",
                "kaftan skorzany",
                new BigDecimal("100"),
                group);
        //When
        Product testProduct2 = productRepository.save(testProduct1);
        //Then
        long id = testProduct2.getId();
        Optional<Product> savedProduct = productRepository.findById(id);
        Assert.assertTrue(savedProduct.isPresent());
        //Clean up
        productRepository.deleteAll();
    }

    @Test
    public void testGettingSelectedProductObject(){
        //Given
        Group group = groupRepository.save(new Group(100L,"test_group_2"));

        Product testProduct1 = new Product(
                101L,
                "obcegi",
                "obcegi tytanowe",
                new BigDecimal("10"),
                group);
        //When
        Product testProduct2 = productRepository.save(testProduct1);
        //Then
        long id = testProduct2.getId();
        Optional<Product> selectedProduct = productRepository.findById(id);
        String name = selectedProduct.get().getName();
        Assert.assertEquals("obcegi", name);
        //Clean up
        productRepository.deleteAll();
    }

    @Test
    public void testUpdatingProductObject(){
        //Given
        Group group = groupRepository.save(new Group(1000L, "test_group_3"));

        Product testProduct1 = new Product(
                102L,
                "bavaria",
                "piwo light",
                new BigDecimal("4"),
                group);
        Product testProduct2 = productRepository.save(testProduct1);
        long id = testProduct2.getId();
        testProduct1 = new Product(
                id,
                "bavaria",
                "piwo niskoprocentowe",
                new BigDecimal("5"),
                group);
        productRepository.save(testProduct1);
        //When
        Optional <Product> updatedProduct = productRepository.findById(id);
        //Then
        Assert.assertEquals("piwo niskoprocentowe", updatedProduct.get().getDescription());
        //Clean up
        productRepository.deleteAll();
    }

    @Test
    public void testDeletingProductObject(){
        //Given
        Group group = groupRepository.save(new Group(10000L, "test_group_4"));

        Product testProduct1 = new Product(
                103L,
                "BTR 401",
                "transporter opancerzony",
                new BigDecimal("10000"),
                group);
        Product testProduct2 = productRepository.save(testProduct1);
        //When
        long id = testProduct2.getId();
        Optional<Product> selectedProduct = productRepository.findById(id);
        Assert.assertTrue(selectedProduct.isPresent());
        productRepository.deleteById(id);
        Optional<Product> deletedProduct = productRepository.findById(id);
        Assert.assertFalse(deletedProduct.isPresent());
        //Clean up
        productRepository.deleteAll();
    }
}
