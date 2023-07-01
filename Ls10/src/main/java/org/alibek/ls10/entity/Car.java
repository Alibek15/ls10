package org.alibek.ls10.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Car {
    private Long id;

    private String name;

    private Double engineVolume;

    private Double price;


}
