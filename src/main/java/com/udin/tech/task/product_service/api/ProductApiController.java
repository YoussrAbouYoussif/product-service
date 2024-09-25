package com.udin.tech.task.product_service.api;

import com.udin.tech.task.product_service.exception.NullCriteriaException;
import com.udin.tech.task.product_service.model.request.CreateProductRequest;
import com.udin.tech.task.product_service.model.request.UpdateProductRequest;
import com.udin.tech.task.product_service.model.response.ProductResponse;
import com.udin.tech.task.product_service.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductApiController {

    @Autowired
    private ProductService productService;

    // Create a new product
    @Operation(summary = "Create new product")
    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody CreateProductRequest product) {
        return ResponseEntity.ok(productService.createProduct(product));
    }

    // Get all products
    @Operation(summary = "Get all Products")
    @GetMapping
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }

    // Get product by ID
    @Operation(summary = "Get Product by ID")
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id) {
        ProductResponse productResponse = productService.getProductById(id);
        return ResponseEntity.ok(productResponse);
    }

    // Update product by ID
    @Operation(summary = "Update Product by ID")
    @ApiResponse(responseCode = "200", description = "Product found")
    @ApiResponse(responseCode = "400", description = "Null Criteria")
    @ApiResponse(responseCode = "404", description = "Product not found")
    @Parameter(name = "Update Request")
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

    @Operation(summary = "Delete Product by ID")
    @ApiResponse(responseCode = "200", description = "Product found")
    @ApiResponse(responseCode = "404", description = "Product not found")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        if (id == null) {
            throw new NullCriteriaException("Product ID cannot be null.");
        }

        productService.deleteProduct(id);

        return ResponseEntity.ok().build();
    }
}
