package com.udin.tech.task.product_service.api;

import com.udin.tech.task.product_service.exception.NullCriteriaException;
import com.udin.tech.task.product_service.model.request.CreateProductRequest;
import com.udin.tech.task.product_service.model.request.UpdateProductRequest;
import com.udin.tech.task.product_service.model.response.ProductResponse;
import com.udin.tech.task.product_service.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductApiController {

    @Autowired
    private ProductService productService;

    // Create a new product
    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody CreateProductRequest product) {
        return ResponseEntity.ok(productService.createProduct(product));
    }

    // Get all products
    @GetMapping
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }

    // Get product by ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id) {
        ProductResponse productResponse = productService.getProductById(id);
        return ResponseEntity.ok(productResponse);
    }

    // Update product by ID
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long id, @RequestBody UpdateProductRequest request) {
        if (id == null) {
            throw new NullCriteriaException("Product ID cannot be null.");
        }

        if (request == null) {
            throw new NullCriteriaException("Update request cannot be empty.");
        }

        if ((request.getName() == null || request.getName().isEmpty()) && (request.getDescription() == null || request.getDescription().isEmpty())) {
            throw new NullCriteriaException("Update criteria (name and price) cannot be null.");
        }

        ProductResponse productResponse = productService.updateProduct(id, request);
        return ResponseEntity.ok(productResponse);
    }
}
