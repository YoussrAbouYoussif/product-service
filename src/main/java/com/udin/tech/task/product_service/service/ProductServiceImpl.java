package com.udin.tech.task.product_service.service;

import com.udin.tech.task.product_service.dao.ProductRepository;
import com.udin.tech.task.product_service.dao.domain.Product;
import com.udin.tech.task.product_service.mapper.Mapper;
import com.udin.tech.task.product_service.model.request.CreateProductRequest;
import com.udin.tech.task.product_service.model.response.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private Mapper mapper;

    @Override
    public ProductResponse createProduct(CreateProductRequest request) {
        Product createdProduct = productRepository.save(mapper.map(request, Product.class));
        return mapper.map(createdProduct, ProductResponse.class);
    }
}
