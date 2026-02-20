package com.example.assignment.repository;

import com.example.assignment.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByProductId(Long productId);
}

