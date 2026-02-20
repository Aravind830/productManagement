package com.example.assignment.service.impl;

import com.example.assignment.dto.ProductRequest;
import com.example.assignment.dto.ProductResponse;
import com.example.assignment.entity.Product;
import com.example.assignment.exception.ResourceNotFoundException;
import com.example.assignment.repository.ProductRepository;
import com.example.assignment.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repo;

    /**
     *
     * @param pageable
     * @return
     */
    public Page<ProductResponse> getAll(Pageable pageable) {
        return repo.findAll( pageable)
                .map(p -> new ProductResponse(p.getId(), p.getProductName()));
    }

    /**
     *
     * @param id
     * @return
     */
    public ProductResponse getById(Long id) {
        Product p = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        return new ProductResponse(p.getId(), p.getProductName());
    }

    /**
     *
     * @param req
     * @param user
     * @return
     */
    public ProductResponse create(ProductRequest req, String user) {
        Product p = new Product();
        p.setProductName(req.getProductName());
        p.setCreatedBy(user);
        p.setCreatedOn(LocalDateTime.now());
        repo.save(p);
        return new ProductResponse(p.getId(), p.getProductName());
    }

    /**
     *
     * @param id
     * @param req
     * @param user
     * @return
     */
    public ProductResponse update(Long id, ProductRequest req, String user) {
        Product p = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        p.setProductName(req.getProductName());
        p.setModifiedBy(user);
        p.setModifiedOn(LocalDateTime.now());
        repo.save(p);
        return new ProductResponse(p.getId(), p.getProductName());
    }

    /**
     *
     * @param id
     */
    public void delete(Long id) {
        repo.deleteById(id);
    }
}

