package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;
import java.math.BigDecimal;
import java.util.Optional;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ProductRepositoryTestSuite {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private GroupRepository groupRepository;

    @PersistenceContext
    EntityManager entityManager;

    @Test
    public void testSavingNewProductObject() {
        //Given
        Group group = groupRepository.save(new Group(null, "test group_1"));

        Product testProduct1 = new Product(
                null,
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
    }

    @Test
    public void testGettingSelectedProductObject(){
        //Given
        Group group = groupRepository.save(new Group(null,"test_group_2"));

        Product testProduct1 = new Product(
                null,
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
    }

    @Test
    public void testUpdatingProductObject(){
        //Given
        Group group = groupRepository.save(new Group(null, "test_group_3"));

        Product testProduct1 = new Product(
                null,
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
    }

    @Test
    public void testDeletingProductObject(){
        //Given
        Group group = groupRepository.save(new Group(null, "test_group_4"));

        Product testProduct1 = new Product(
                null,
                "BTR 401",
                "transporter opancerzony",
                new BigDecimal("10000"),
                group);
        Product testProduct2 = productRepository.save(testProduct1);
        //When
        long id = testProduct2.getId();
        Optional<Product> selectedProduct = productRepository.findById(id);
        boolean beforeDeleting = selectedProduct.isPresent();
        productRepository.deleteById(id);
        Optional<Product> deletedProduct = productRepository.findById(id);
        boolean afterDeleting = deletedProduct.isPresent();
        //Then
        Assert.assertTrue(beforeDeleting);
        Assert.assertFalse(afterDeleting);
    }

    @Test(expected = ConstraintViolationException.class)
    public void testSavingInvalidProductObject(){
        //Given
        Product product = new Product(
                null,
                null,
                "invalid object",
                null,
                null);

        productRepository.save(product);
        //When
        entityManager.flush();
        //Then
    }
}
