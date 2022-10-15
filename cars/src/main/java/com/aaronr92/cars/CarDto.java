package com.aaronr92.cars;

import lombok.*;

import java.io.Serializable;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarDto implements Serializable {
    private String name;
    private String description;
    private int price;
    private int power;
    private String manufacturer;
}