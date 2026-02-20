package com.example.assignment.service;

import com.example.assignment.dto.ProductRequest;
import com.example.assignment.dto.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    Page<ProductResponse> getAll(Pageable pageable);

    ProductResponse getById(Long id);

    ProductResponse create(ProductRequest req, String user);

    ProductResponse update(Long id, ProductRequest req, String user);

    void delete(Long id);
}
