package com.tech.task.product_service.service;

import com.tech.task.product_service.dao.ProductRepository;
import com.tech.task.product_service.exception.ProductNotFoundException;
import com.tech.task.product_service.mapper.ProductsMapper;
import com.tech.task.product_service.model.request.CreateProductRequest;
import com.tech.task.product_service.model.request.UpdateProductRequest;
import com.tech.task.product_service.model.response.ProductResponse;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class ProductServiceImplTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductsMapper mapper;

    @Autowired
    private ProductService productService;

    @BeforeEach
    void setUp() {
        productRepository.deleteAll();
    }

    @AfterEach
    public void tearDown() {
        productRepository.deleteAll();
    }

    @Test
    void testCreateProductSuccess() {
        CreateProductRequest createProductRequest = new CreateProductRequest();
        createProductRequest.setName("Laptop");
        createProductRequest.setDescription("HP Laptop");

        ProductResponse response = productService.createProduct(createProductRequest);

        assertThat(response).isNotNull();
        assertThat(response.getName()).isEqualTo("Laptop");
        assertThat(response.getDescription()).isEqualTo("HP Laptop");
    }

    @Test
    void testCreateProductFailure() {
        CreateProductRequest createProductRequest = new CreateProductRequest();
        createProductRequest.setName(null);
        createProductRequest.setDescription("HP Laptop");

        ConstraintViolationException exception = assertThrows(ConstraintViolationException.class, () -> {
            productService.createProduct(createProductRequest);
        });

        assertThat(exception.getMessage()).contains("Name should not be empty.");
    }

    @Test
    void testGetAllProducts() {
        CreateProductRequest request1 = new CreateProductRequest();
        request1.setName("Laptop");
        request1.setDescription("HP Laptop");
        productService.createProduct(request1);

        CreateProductRequest request2 = new CreateProductRequest();
        request2.setName("Iphone");
        request2.setDescription("Iphone 15");
        productService.createProduct(request2);

        List<ProductResponse> products = productService.getAllProducts();

        assertThat(products).hasSize(2);
        assertThat(products).extracting(ProductResponse::getName).containsExactlyInAnyOrder("Laptop", "Iphone");
    }

    @Test
    void testGetProductByIdSuccess() {
        CreateProductRequest request1 = new CreateProductRequest();
        request1.setName("Laptop");
        request1.setDescription("HP Laptop");
        ProductResponse res1 = productService.createProduct(request1);

        CreateProductRequest request2 = new CreateProductRequest();
        request2.setName("Iphone");
        request2.setDescription("Iphone 15");
        ProductResponse res2 = productService.createProduct(request2);

        ProductResponse productResponse = productService.getProductById(res2.getId());

        assertEquals(res2.getId(), productResponse.getId());
        assertEquals(res2.getName(), productResponse.getName());
    }

    @Test
    void testGetProductByIdFailure() {
        Long nonExistentId = 999L;
        ProductNotFoundException exception = assertThrows(ProductNotFoundException.class, () -> {
            productService.getProductById(nonExistentId);
        });

        assertEquals("Product not found with id: " + nonExistentId, exception.getMessage());
    }

    @Test
    void testUpdateProductByIdSuccess() {
        CreateProductRequest request1 = new CreateProductRequest();
        request1.setName("Laptop");
        request1.setDescription("HP Laptop");
        ProductResponse res1 = productService.createProduct(request1);

        CreateProductRequest request2 = new CreateProductRequest();
        request2.setName("Iphone");
        request2.setDescription("Iphone 15");
        ProductResponse res2 = productService.createProduct(request2);

        UpdateProductRequest updateReq = new UpdateProductRequest();
        updateReq.setDescription("Iphone 16");
        ProductResponse productResponse = productService.updateProduct(res2.getId(), updateReq);

        assertEquals(productResponse.getId(), res2.getId());
        assertEquals(productResponse.getName(), res2.getName());
        assertEquals(productResponse.getDescription(), updateReq.getDescription());
    }

    @Test
    void testUpdateProductByIdFailure() {
        UpdateProductRequest updateReq = new UpdateProductRequest();
        updateReq.setDescription("Iphone 16");

        Long nonExistentId = 999L;
        ProductNotFoundException exception = assertThrows(ProductNotFoundException.class, () -> {
            productService.updateProduct(nonExistentId, updateReq);
        });

        assertEquals("Product not found with id: " + nonExistentId, exception.getMessage());
    }

    @Test
    void testDeleteProductByIdSuccess() {
        CreateProductRequest request1 = new CreateProductRequest();
        request1.setName("Laptop");
        request1.setDescription("HP Laptop");
        ProductResponse res1 = productService.createProduct(request1);

        CreateProductRequest request2 = new CreateProductRequest();
        request2.setName("Iphone");
        request2.setDescription("Iphone 15");
        ProductResponse res2 = productService.createProduct(request2);

        productService.deleteProduct(res2.getId());

        assertFalse(productRepository.existsById(res2.getId()));
    }

    @Test
    public void testDeleteProductByIdFailure() {
        Long productId = 999L;

        Exception exception = assertThrows(ProductNotFoundException.class, () -> {
            productService.deleteProduct(productId);
        });

        // Verify the exception message
        assertEquals("Product not found with id: " + productId, exception.getMessage());
    }
}