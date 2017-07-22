package com.vancuongngo.springwebapp.bootstrap;

import com.vancuongngo.springwebapp.model.Product;
import com.vancuongngo.springwebapp.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/*
* How to run code on start up with Spring Boot: https://springframework.guru/running-code-on-spring-boot-startup/
* */
@Component
@Slf4j
public class ProductLoader implements ApplicationListener<ContextRefreshedEvent> {

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        Product book = new Product();
        book.setDescription("Tôi Của Mùa Hè Năm Ấy");
        book.setPrice(new BigDecimal("79"));
        book.setImageUrl("https://tiki.vn/toi-cua-mua-he-nam-ay-p720085.html");
        book.setProductId("1");
        productRepository.save(book);

        log.info("Saved Book - id: " + book.getId());

        Product book2 = new Product();
        book2.setDescription("Thời Gian Trôi Mãi");
        book2.setPrice(new BigDecimal("72"));
        book2.setImageUrl("https://tiki.vn/thoi-gian-troi-mai-p343327.html");
        book2.setProductId("2");
        productRepository.save(book2);

        log.info("Saved Book - id:" + book2.getId());
    }
}