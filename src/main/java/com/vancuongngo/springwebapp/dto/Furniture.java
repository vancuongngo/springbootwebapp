package com.vancuongngo.springwebapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Furniture {
    private String name;
    private String usage;
    private boolean isChecked;

    public Furniture(String name, String usage) {
        this.name = name;
        this.usage = usage;
    }
}
