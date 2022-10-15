package com.aaronr92.cars.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.springframework.data.annotation.ReadOnlyProperty;

import javax.persistence.*;

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

    @ManyToOne
    @JoinColumn(name = "manufacturer_id", nullable = false)
    @JsonBackReference
    @ReadOnlyProperty
    private CarManufacturer manufacturer;
}