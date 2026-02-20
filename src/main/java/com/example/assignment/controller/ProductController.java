package com.example.assignment.controller;

import com.example.assignment.dto.ProductRequest;
import com.example.assignment.dto.ProductResponse;
import com.example.assignment.service.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Tag(name="Product API")
public class ProductController {

    private final ProductService service;

    @GetMapping
    public Page<ProductResponse> getAll(
            @PageableDefault(size=10) Pageable pageable) {
        return service.getAll(pageable);
    }

    @GetMapping("/{id}")
    public ProductResponse get(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public ProductResponse create(
            @Valid @RequestBody ProductRequest req,
            Authentication auth) {
        return service.create(req, auth.getName());
    }

    @PutMapping("/{id}")
    public ProductResponse update(
            @PathVariable Long id,
            @Valid @RequestBody ProductRequest req,
            Authentication auth) {
        return service.update(id, req, auth.getName());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}

