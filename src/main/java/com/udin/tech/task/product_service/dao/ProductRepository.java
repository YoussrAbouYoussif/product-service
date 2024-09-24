package com.udin.tech.task.product_service.dao;

import com.udin.tech.task.product_service.dao.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
