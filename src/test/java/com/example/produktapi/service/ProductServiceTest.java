package com.example.produktapi.service;

import com.example.produktapi.exception.BadRequestException;
import com.example.produktapi.exception.EntityNotFoundException;
import com.example.produktapi.model.Product;
import com.example.produktapi.repository.ProductRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.internal.verification.NoMoreInteractions;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)

class ProductServiceTest {

    @Mock
private ProductRepository repository;

    @InjectMocks
private ProductService underTest;

    @Captor
ArgumentCaptor<Product> productCaptor;

    @Captor
ArgumentCaptor<Integer> idCaptor;

    @Test
    @DisplayName("getAllProducts()")
    void GetAllProducts_thenExactlyOneInteractionWithRepositoryMethodFindAll() {
        //when
        underTest.getAllProducts();
        //then
        verify(repository,times(1)).findAll();
        verifyNoMoreInteractions(repository);
    }

    @Test
    @DisplayName("getAllCategories()")
    void whenGetAllCategories_thenExactlyOneInteractionWithRepositoryMethodFindAllCategories() {
        //when
        underTest.getAllCategories();

        //then
        verify(repository,times(1)).findAllCategories();
        verifyNoMoreInteractions(repository);
    }

    @Test
    @DisplayName("getProductsByCategory()")
    void whenGetProductsByCategory_thenExactlyOneInteractionWithRepositoryMethodFindByCategory() {
        //given
        String category = "electronics";
        String titel = "vår test titel";
        Product product = new Product(titel,4000.00,category,"","");
        //when
        underTest.getProductsByCategory(product.getCategory());
        //then
        verify(repository,times(1)).findByCategory(category);
        verifyNoMoreInteractions(repository);
    }

    @Test
    @DisplayName("testar getProductById/ normalflöde")
    void whenTryingToGetProductById_thenReturnProduct() {
        //given
        String titel = "vår test titel";
        Product product = new Product(titel,4000.00,"","","");
        given(repository.findById(product.getId())).willReturn(Optional.of(product));
        //when
        Product product2 = underTest.getProductById(any());

        //then
        assertEquals(product, product2);
    }
    @Test
    @DisplayName("testar getProductById/ felflöde")
    void whenTryingToGetProductByIdWithWrongId_thenReturnException() {
        //given
        String titel = "vår test titel";
        Product product = new Product(titel,4000.00,"","","");
        given(repository.findById(product.getId())).willReturn(Optional.empty());


        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                //when
                ()->underTest.getProductById(product.getId()));
                //then
        assertEquals("Produkt med id "+ product.getId()+ " hittades inte",exception.getMessage());
    }
    @Test
    @DisplayName("addProduct test / normalflöde")
    void whenAddingAProduct_thenSaveMethodShouldBeCalled() {
        //given
        Product product = new Product("Rätt objekt som sparas",4000.00,"","","");

        //when

        underTest.addProduct(product);

        //then

        verify(repository).save(productCaptor.capture());
        assertEquals(product, productCaptor.getValue());
    }
@Test
@DisplayName("addProduct test / fel titel felflöde")
void whenAddingproductWithDuplicateTitle_thenThrowError(){
        //given
    String titel = "vår test titel";
    Product product = new Product(titel,34.0,"","","");
    given(repository.findByTitle(titel)).willReturn(Optional.of(product));


    //then
    BadRequestException exception = assertThrows(BadRequestException.class,
            //when
            ()->underTest.addProduct(product));
    verify(repository, times(1)).findByTitle(titel);
    verify(repository,times(0)).save(any());
    assertEquals("En produkt med titeln: " + titel + " finns redan",exception.getMessage());
}
    @Test
    @DisplayName("updateProduct test normalflöde")
    void whenUpdatingAProduct_thenSaveMethodShouldBeCalled() {
        //given
        String titel = "Objekt som ska updateras";
        Product product = new Product(titel,4000.00,"","","");
        given(repository.findById(product.getId())).willReturn(Optional.of(product));

        //when
        underTest.updateProduct(product, product.getId());
        //then
        verify(repository,times(1)).save(productCaptor.capture());

        assertEquals(product, productCaptor.getValue());

    }
    @Test
    @DisplayName("updateProduct test / fel id")
    void whenUpdatingProductWithWrongId_thenThrowError(){
        //given
        String titel = "Objekt som ska updateras";
        Product product = new Product(titel,34.0,"","","");
        given(repository.findById(product.getId())).willReturn(Optional.empty());

        //when
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,

        //then
        ()-> underTest.updateProduct(product, product.getId()));
        verify(repository, times(1)).findById(product.getId());
        verify(repository,times(0)).save(any());
        assertEquals("Produkt med id "+ product.getId()+ " hittades inte",exception.getMessage());
    }

    @Test
    @DisplayName("deleteProduct test normalflöde")
    void whenDeletingAProduct_thenDeleteByIdMethodShouldBeCalled() {
        //given
        String titel = "Objekt som ska updateras";
        Product product = new Product(titel,4000.00,"","","");
        //int id = 1;
        //product.setId(id);

        given(repository.findById(product.getId())).willReturn(Optional.of(product));
        //when
        underTest.deleteProduct(product.getId());

        //then
        verify(repository,times(1)).deleteById(idCaptor.capture());
        assertEquals(product.getId(), idCaptor.getValue());
    }
    @Test
    @DisplayName("deleteProduct test / fel id / felflöde")
    void whenDeletingProductWithWrongId_thenThrowError(){
        //given
        String titel = "Objekt som ska updateras";
        Product product = new Product(titel,34.0,"","","");
        given(repository.findById(product.getId())).willReturn(Optional.empty());

        //when
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,

        //then
        ()-> underTest.deleteProduct(product.getId()));
        verify(repository, times(1)).findById(product.getId());
        assertEquals("Produkt med id "+ product.getId()+ " hittades inte",exception.getMessage());
        verify(repository,times(0)).deleteById(anyInt());

    }

}