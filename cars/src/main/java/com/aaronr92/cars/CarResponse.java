package com.aaronr92.cars;

import com.aaronr92.cars.entity.Car;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarResponse {
    private Long id;
    private String model;
    private String name;
    private String description;
    private int price;
    private int power;
    @JsonProperty("release_date")
    private int releaseDate;
    @JsonProperty("is_electric")
    private boolean isElectric;
    private String manufacturer;

    public static CarResponse carToCarResponse(Car car) {
        return CarResponse.builder()
                .id(car.getId())
                .model(car.getModel())
                .name(car.getName())
                .description(car.getDescription())
                .price(car.getPrice())
                .power(car.getPrice())
                .releaseDate(car.getReleaseYear())
                .isElectric(car.isElectric())
                .manufacturer(car.getManufacturer().getName())
                .build();
    }
}
