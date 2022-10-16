package com.aaronr92.cars.entity;

import com.aaronr92.cars.CarDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.ReadOnlyProperty;

import javax.persistence.*;
import java.time.Year;

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

    private String name;

    private String description;

    private int price;

    private int power;

    @JsonProperty("release_year")
    private Year releaseYear;

    @ManyToOne
    @JoinColumn(name = "manufacturer_id", nullable = false)
    @JsonBackReference
    @ReadOnlyProperty
    private CarManufacturer manufacturer;

    public static Car fromCarDto(CarDto carDto) {
        return Car.builder()
                .name(carDto.getName())
                .description(carDto.getDescription())
                .price(carDto.getPrice())
                .power(carDto.getPower())
                .releaseYear(carDto.getReleaseYear())
                .build();
    }
}