package com.jhs.springsecuritybasic.chap06.service;

import com.jhs.springsecuritybasic.chap06.model.Product;
import com.jhs.springsecuritybasic.chap06.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> findAll(){
        return productRepository.findAll();
    }
}
