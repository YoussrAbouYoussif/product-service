package com.udin.tech.task.product_service.api;

import com.udin.tech.task.product_service.model.request.CreateProductRequest;
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
}
