package com.udin.tech.task.product_service.service;

import com.udin.tech.task.product_service.dao.domain.Product;
import com.udin.tech.task.product_service.model.request.CreateProductRequest;
import com.udin.tech.task.product_service.model.response.ProductResponse;

public interface ProductService {

    ProductResponse createProduct(CreateProductRequest request);
}
