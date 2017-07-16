package com.vancuongngo.springwebapp.service;

import com.vancuongngo.springwebapp.model.Product;
import com.vancuongngo.springwebapp.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
public class ProductService {
    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void saveProducts() {
        Product product = Product.builder()
                .description("this is description")
                .productId("p1")
                .imageUrl("url")
                .price(BigDecimal.valueOf(1234))
                .build();

        productRepository.save(product);
        log.info(String.valueOf(productRepository.count()));
    }
}
