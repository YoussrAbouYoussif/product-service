package com.tech.task.product_service.api;

import com.tech.task.product_service.dao.ProductRepository;
import com.tech.task.product_service.model.request.CreateProductRequest;
import com.tech.task.product_service.model.response.ProductResponse;
import com.tech.task.product_service.service.ProductService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class ProductApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    public void setUp() {
        productRepository.deleteAll();
    }

    @AfterEach
    public void tearDown() {
        productRepository.deleteAll();
    }

    @Test
    public void testCreateProduct() throws Exception {
        CreateProductRequest createProductRequest = new CreateProductRequest();
        createProductRequest.setName("Laptop");
        createProductRequest.setDescription("HP Laptop");

        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Laptop\", \"description\": \"HP Laptop\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Laptop"))
                .andExpect(jsonPath("$.description").value("HP Laptop"));
    }

    @Test
    void testGetAllProducts() throws Exception {
        CreateProductRequest req1 = new CreateProductRequest();
        req1.setName("Laptop");
        req1.setDescription("HP Laptop");
        productService.createProduct(req1);

        CreateProductRequest req2 = new CreateProductRequest();
        req2.setName("Iphone");
        req2.setDescription("Iphone 15");
        ProductResponse res2 = productService.createProduct(req2);

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Laptop"))
                .andExpect(jsonPath("$[1].name").value("Iphone"));
    }

    @Test
    void testGetProductById() throws Exception {
        CreateProductRequest req1 = new CreateProductRequest();
        req1.setName("Laptop");
        req1.setDescription("HP Laptop");
        ProductResponse res1 = productService.createProduct(req1);

        CreateProductRequest req2 = new CreateProductRequest();
        req2.setName("Iphone");
        req2.setDescription("Iphone 15");
        ProductResponse res2 = productService.createProduct(req2);

        mockMvc.perform(get("/products/{id}", res2.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Iphone"));;
    }

    @Test
    void testUpdateProductByIdSuccess() throws Exception {
        CreateProductRequest req1 = new CreateProductRequest();
        req1.setName("Laptop");
        req1.setDescription("HP Laptop");
        ProductResponse res1 = productService.createProduct(req1);

        CreateProductRequest req2 = new CreateProductRequest();
        req2.setName("Iphone");
        req2.setDescription("Iphone 15");
        ProductResponse res2 = productService.createProduct(req2);

        mockMvc.perform(put("/products/{id}", res2.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"description\":\"Iphone 16\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.description").value("Iphone 16"));
    }

    @Test
    void testUpdateProductByIdWithNullCriteria() throws Exception {

        Long id = 0L;

        mockMvc.perform(put("/products/{id}",  id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Update criteria (name and price) cannot be null."));
    }

    @Test
    void testDeleteProductById() throws Exception {
        CreateProductRequest req1 = new CreateProductRequest();
        req1.setName("Laptop");
        req1.setDescription("HP Laptop");
        ProductResponse res1 = productService.createProduct(req1);

        CreateProductRequest req2 = new CreateProductRequest();
        req2.setName("Iphone");
        req2.setDescription("Iphone 15");
        ProductResponse res2 = productService.createProduct(req2);

        mockMvc.perform(delete("/products/{id}", res2.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
