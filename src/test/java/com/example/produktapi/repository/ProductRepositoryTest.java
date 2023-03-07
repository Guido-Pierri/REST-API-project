package com.example.produktapi.repository;

import com.example.produktapi.model.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@DataJpaTest // annotation when testing a repository
class ProductRepositoryTest {
    @Autowired //Automatic dependency injection(Spring bean autowiring) like a constructor?
    private ProductRepository underTest;
    @AfterEach
    void tearDown(){
        underTest.deleteAll();
    }
    @Test
    void testingOurRepository(){
    List<Product> products = underTest.findAll();
    Assertions.assertFalse(products.isEmpty());
}
    @Test
    @DisplayName("testar ")
    void whenSearchingForAnExistingCategory_thenReturnAllProductsInCategory() {
        //given
        String title = "En dator";
        String category = underTest.findAllCategories().get(0); // assigns category of index 0 ("electronics") from findAllCategories() to the variable category
        Product product = new Product(title,
                23000.0,
                category,
                "Bra o ha",
                "url");
        underTest.save(product);
        //when
        List<Product> listProduct = underTest.findByCategory(category);
        //then
        Assertions.assertTrue(listProduct.contains(product));
        Assertions.assertFalse(listProduct.isEmpty());
        Assertions.assertEquals(category, listProduct.get(listProduct.lastIndexOf(product)).getCategory());
    }

    @Test
    void whenSearchingForAnExistingTitle_thenReturnThatProduct() {
        //given
        String title = "En dator";
        Product product = new Product(
                title,
                23000.0,
                "electronics",
                "Bra o ha",
                "url");
        underTest.save(product);
        //when
        Optional<Product> optionalProduct = underTest.findByTitle(title);

        //then
        Assertions.assertAll(
                ()-> assertTrue(optionalProduct.isPresent()),
                ()->assertFalse(optionalProduct.isEmpty()),
                ()->assertEquals(title, optionalProduct.get().getTitle())
        );
    }
    @Test
    void whenSearchingForANonexistingTitle_thenReturnEmptyOptional() {
        //given
        String title = "Non existing title";

        // when
        Optional<Product> optionalProduct = underTest.findByTitle(title);
        //then
        Assertions.assertFalse(optionalProduct.isPresent());
        Assertions.assertTrue((optionalProduct.isEmpty()));
        Assertions.assertAll(
                ()->assertTrue(optionalProduct.isEmpty()),
                ()->assertFalse(optionalProduct.isPresent()),
                ()->assertThrows(NoSuchElementException.class, ()->optionalProduct.get()));

    }
}