package com.tech.task.product_service.service;

import com.tech.task.product_service.model.request.CreateProductRequest;
import com.tech.task.product_service.model.request.UpdateProductRequest;
import com.tech.task.product_service.model.response.ProductResponse;

import java.util.List;

public interface ProductService {

    ProductResponse createProduct(CreateProductRequest request);

    List<ProductResponse> getAllProducts();

    ProductResponse getProductById(Long id);

    ProductResponse updateProduct(Long id, UpdateProductRequest request);

    void deleteProduct(Long id);
}
