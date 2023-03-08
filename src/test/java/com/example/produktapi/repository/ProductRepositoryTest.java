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
    @DisplayName("testar findByCategory() och söka alla produkter i en kategori och testar om det är rätt produkt")
    void whenSearchingForAnExistingCategory_thenReturnAllProductsInCategoryAndCheckItIsTheSameProductCategory() {
        //given
        String title = "En dator";
        String category = underTest.findAllCategories().get(0); // assigns category of index 0 ("electronics") from findAllCategories() to the variable category
        Product product = new Product(title,
                23000.0,
                category,
                "Bra o ha",
                "url");
        String title2 = "En dator";
        String category2 = "test category";
        Product product2 = new Product(title,
                23000.0,
                category,
                "Bra o ha",
                "url");
        underTest.save(product);
        //when
        List<Product> listProduct = underTest.findByCategory(category);//returns all the products in the "electronics" category as a list
        //then
        Assertions.assertTrue(listProduct.contains(product));
        Assertions.assertFalse(listProduct.contains(product2));
        Assertions.assertEquals(category, listProduct.get(listProduct.lastIndexOf(product)).getCategory());//checks if the category of the product created is the same as the last product in the listProduct list returned
    }


    @Test
    @DisplayName("testar findByCategory() och att söka alla produkter i en kategori och testar om det returneras 6 produkter")
    void whenSearchingForAnExistingCategory_thenReturnAllProductsInCategoryAndSizeShouldBeSix() {
        //given
String category = "electronics";
        //when
        List<Product> listProduct = underTest.findByCategory(category);//returns all the products in the "electronics" category as a list
        //then
        assertEquals(6,listProduct.size());
        assertNotEquals(7,listProduct.size());
    }
    @Test
    @DisplayName("testar findByCategory() med en kategori som inte existerar")
    void whenSearchingForANonExistingCategory_thenReturnFalse() {
        //given
        String title = "En dator";
        String category = "electronics";
        Product product = new Product(title,
                23000.0,
                "test category",
                "Bra o ha",
                "url");

        //when
        List<Product> listProduct = underTest.findByCategory(category);//returns all the products in the "electronics" category as a list should be 0
        System.out.println(listProduct);
        //then
        Assertions.assertFalse(listProduct.contains(product));
    }
    @Test
    @DisplayName("Testar findByTitle() normalflöde")
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
    @DisplayName("Testar findByTitle() felflöde")
    void whenSearchingForANonexistingTitle_thenReturnEmptyOptional() {
        //given
        String title = "Non existing title";

        // when
        Optional<Product> optionalProduct = underTest.findByTitle(title);
        //then

        Assertions.assertAll(
                ()->assertTrue(optionalProduct.isEmpty()),
                ()->assertFalse(optionalProduct.isPresent()),
                ()->assertThrows(NoSuchElementException.class, ()->optionalProduct.get()));

    }
}