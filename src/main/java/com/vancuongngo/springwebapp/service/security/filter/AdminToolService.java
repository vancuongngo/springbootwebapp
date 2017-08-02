package com.vancuongngo.springwebapp.service.security.filter;

import com.vancuongngo.springwebapp.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminToolService {

    private ProductRepository productRepository;

    public AdminToolService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public long fetchAllProducts() {
        return productRepository.count();
    }
}
