package com.aaronr92.customer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    private Long id;

    private String model;

    private String name;

    private String description;

    private int price;

    private int power;

    private int releaseYear;

    @JsonProperty("is_electric")
    private boolean isElectric;

    private String manufacturer;
}