package com.udin.tech.task.product_service.service;

import com.udin.tech.task.product_service.dao.ProductRepository;
import com.udin.tech.task.product_service.dao.domain.Product;
import com.udin.tech.task.product_service.mapper.ProductsMapper;
import com.udin.tech.task.product_service.model.request.CreateProductRequest;
import com.udin.tech.task.product_service.model.response.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductsMapper mapper;

    @Override
    public ProductResponse createProduct(CreateProductRequest request) {
        Product createdProduct = productRepository.save(mapper.createProductToProduct(request));
        return mapper.productToProductResponse(createdProduct);
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        return mapper.productListtoProductResponseList(productRepository.findAll());
    }
}
