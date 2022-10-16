package com.aaronr92.cars.entity;

import com.aaronr92.cars.CarDto;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.ReadOnlyProperty;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Getter @Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Car {
    @Id
    @GeneratedValue(generator = "carGen")
    @SequenceGenerator(name = "carGen", sequenceName = "carSeq", allocationSize = 1)
    @ReadOnlyProperty
    private Long id;

    private String model;

    private String name;

    private String description;

    private int price;

    private int power;

    @JsonProperty("release_year")
    @Pattern(regexp = "^(19|20)[\\d]{2}$")
    private int releaseYear;

    @JsonProperty("is_electric")
    private boolean isElectric;

    @ManyToOne
    @JoinColumn(name = "manufacturer_id", nullable = false)
    @JsonManagedReference
    private CarManufacturer manufacturer;

    public static Car fromCarDto(CarDto carDto) {
        return Car.builder()
                .model(carDto.getModel())
                .name(carDto.getName())
                .description(carDto.getDescription())
                .price(carDto.getPrice())
                .power(carDto.getPower())
                .releaseYear(carDto.getReleaseYear())
                .isElectric(carDto.isElectric())
                .build();
    }
}