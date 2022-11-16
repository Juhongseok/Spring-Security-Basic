package com.jhs.springsecuritybasic.chap06.repository;

import com.jhs.springsecuritybasic.chap06.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
