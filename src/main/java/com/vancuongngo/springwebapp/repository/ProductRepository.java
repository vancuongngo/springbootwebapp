package com.vancuongngo.springwebapp.repository;

import com.vancuongngo.springwebapp.repository.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {
}
