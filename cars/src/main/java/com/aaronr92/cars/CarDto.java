package com.aaronr92.cars;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
import java.time.Year;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarDto implements Serializable {
    private String name;
    private String description;
    private int price;
    private int power;
    @JsonProperty("release_year")
    private Year releaseYear;
    private String manufacturer;
}