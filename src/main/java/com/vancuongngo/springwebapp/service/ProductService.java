package com.vancuongngo.springwebapp.service;

import com.vancuongngo.springwebapp.repository.model.Product;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {
    Iterable<Product> listAllProducts();

    Product getProductById(Integer id);

    Product saveProduct(Product product);

    void deleteProduct(Integer id);
}
