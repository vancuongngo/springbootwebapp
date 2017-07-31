package com.vancuongngo.springwebapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Flower {
    private String name;
    private int numberOfPetal;
    private boolean isChecked;

    public Flower(String name, int numberOfPetal) {
        this.name = name;
        this.numberOfPetal = numberOfPetal;
    }
}
