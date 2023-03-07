package com.example.produktapi.service;

import com.example.produktapi.exception.BadRequestException;
import com.example.produktapi.exception.EntityNotFoundException;
import com.example.produktapi.model.Product;
import com.example.produktapi.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.BDDMockito.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)//

class ProductServiceTest {

    @Mock //
private ProductRepository repository;//Mocked repository ProductRepository

    @InjectMocks
private ProductService underTest; //Injects the mocked repository into ProductService tests

    @Captor
ArgumentCaptor<Product> productCaptor;

    @Captor
ArgumentCaptor<Integer> idCaptor;

Product testProduct;
@BeforeEach
        void setup(){
    testProduct = new Product("Testprodukt",25.00,"testKategori","testBeskrivning","");
//testProduct.setId(1);
}
    @Test
    @DisplayName("Testar getAllProducts()")
    void whenTryingToGetAllProducts_thenExactlyOneInteractionWithRepositoryMethodFindAll() {
        //when
        underTest.getAllProducts();
        //then
        verify(repository,times(1)).findAll();
        verifyNoMoreInteractions(repository);
    }
    @Test
    @DisplayName("Testar getAllCategories()")
    void whenTryingToGetAllCategories_thenExactlyOneInteractionWithRepositoryMethodFindAllCategories() {
        //when
        underTest.getAllCategories();
        //then
        verify(repository,times(1)).findAllCategories();
        verifyNoMoreInteractions(repository);
    }
    @Test
    @DisplayName("Testar getProductsByCategory()")
    void whenTryingToGetProductsByCategory_thenExactlyOneInteractionWithRepositoryMethodFindByCategory() {
        //given
    String category = testProduct.getCategory();
        //when
        underTest.getProductsByCategory(testProduct.getCategory());
        //then
        verify(repository,times(1)).findByCategory(category);
        verifyNoMoreInteractions(repository);
    }
    @Test
    @DisplayName("Testar getProductById() normalflöde")
    void whenTryingToGetAProductById_thenReturnProduct() {
        //given
        given(repository.findById(testProduct.getId())).willReturn(Optional.of(testProduct));
        //when
        Product testProduct2 = underTest.getProductById(any());
        //Product product2 = underTest.getProductById(testProduct.getId());
        //then
        assertEquals(testProduct, testProduct2);
        //assertNotEquals(testProduct2,);

    }
    @Test
    @DisplayName("Testar getProductById() felflöde")
    void whenTryingToGetProductByIdWithWrongId_thenReturnException() {
        //given

        given(repository.findById(testProduct.getId())).willReturn(Optional.empty());


        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                //when
                ()->underTest.getProductById(testProduct.getId()));
                //then
        assertEquals("Produkt med id " + testProduct.getId() + " hittades inte",exception.getMessage());
    }
    @Test
    @DisplayName("Testar addProduct() normalflöde")
    void whenAddingAProduct_thenSaveMethodShouldBeCalled() {
        //given

        //when

        underTest.addProduct(testProduct);

        //then

        verify(repository).save(productCaptor.capture());
        assertEquals(testProduct, productCaptor.getValue());
    }
    @Test
    @DisplayName("Testar addProduct med fel titel felflöde")
    void whenAddingAProductWithDuplicateTitle_thenThrowError(){
            //given
        String titel = testProduct.getTitle();

        given(repository.findByTitle(titel)).willReturn(Optional.of(testProduct));//creates an object that returns an Optional

        //then
        BadRequestException exception = assertThrows(BadRequestException.class,
                //when
                ()-> underTest.addProduct(testProduct));
        verify(repository, times(1)).findByTitle(titel);
        verify(repository,times(0)).save(any());
        assertEquals("En produkt med titeln: " + titel + " finns redan",exception.getMessage());
    }
    @Test
    @DisplayName("Testar updateProduct() normalflöde")
    void whenUpdatingAProduct_thenSaveMethodShouldBeCalled() {
        //given
        String titel = testProduct.getTitle();
        given(repository.findById(testProduct.getId())).willReturn(Optional.of(testProduct));

        //when
        underTest.updateProduct(testProduct, testProduct.getId());
        //then
        verify(repository,times(1)).save(productCaptor.capture());

        assertEquals(testProduct, productCaptor.getValue());

    }
    @Test
    @DisplayName("Testar updateProduct() felflöde")
    void whenUpdatingProductWithWrongId_thenThrowError(){
        //given
        String titel = testProduct.getTitle();
        given(repository.findById(testProduct.getId())).willReturn(Optional.empty());

        //when
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,

        //then
        ()-> underTest.updateProduct(testProduct, testProduct.getId()));
        verify(repository, times(1)).findById(testProduct.getId());
        verify(repository,times(0)).save(any());
        assertEquals("Produkt med id "+ testProduct.getId()+ " hittades inte",exception.getMessage());
    }

    @Test
    @DisplayName("Testar deleteProduct() normalflöde")
    void whenDeletingAProduct_thenDeleteByIdMethodShouldBeCalled() {
        //given
        String titel = testProduct.getTitle();

        given(repository.findById(testProduct.getId())).willReturn(Optional.of(testProduct));
        //when
        underTest.deleteProduct(testProduct.getId());

        //then
        verify(repository,times(1)).deleteById(idCaptor.capture());
        assertEquals(testProduct.getId(), idCaptor.getValue());
    }
    @Test
    @DisplayName("Testar deleteProduct fel id / felflöde")
    void whenDeletingProductWithWrongId_thenThrowError(){
        //given
        String titel = testProduct.getTitle();
        given(repository.findById(testProduct.getId())).willReturn(Optional.empty());

        //when
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,

        //then
        ()-> underTest.deleteProduct(testProduct.getId()));
        verify(repository, times(1)).findById(testProduct.getId());
        assertEquals("Produkt med id "+ testProduct.getId()+ " hittades inte",exception.getMessage());
        verify(repository,times(0)).deleteById(anyInt());

    }

}