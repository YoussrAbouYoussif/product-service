package com.tech.task.product_service.mapper;

import com.tech.task.product_service.dao.domain.Product;
import com.tech.task.product_service.model.request.CreateProductRequest;
import com.tech.task.product_service.model.response.ProductResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductsMapper {

    Product createProductToProduct(CreateProductRequest request);

    ProductResponse productToProductResponse(Product product);

    List<ProductResponse> productListtoProductResponseList(List<Product> products);
}




