package com.vancuongngo.springwebapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product extends AbstractDomainClass {

    private String productId;

    private String description;

    private String imageUrl;

    private BigDecimal price;
}