package com.example.assignment.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProductRequest {
    @NotBlank
    private String productName;
}

