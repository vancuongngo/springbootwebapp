package com.vancuongngo.springwebapp.controller.ajax;

import com.vancuongngo.springwebapp.annotation.IsFlower;
import com.vancuongngo.springwebapp.annotation.IsFurniture;
import com.vancuongngo.springwebapp.dto.Flower;
import com.vancuongngo.springwebapp.dto.Furniture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "ajaxProductController")
@RequestMapping(value = "/ajax/product/")
public class ProductController {

    @GetMapping(value = "/furniture")
    public Furniture checkFurniture(@IsFurniture Furniture furniture) {
        furniture.setName(furniture.getName() + " new");
        return furniture;
    }

    @GetMapping(value = "/flower")
    public Flower checkFlower(@IsFlower Flower flower) {
        flower.setName(flower.getName() + " new");
        flower.setChecked(true);
        return flower;
    }
}
