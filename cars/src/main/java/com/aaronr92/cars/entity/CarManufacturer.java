package com.aaronr92.cars.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.data.annotation.ReadOnlyProperty;

import javax.persistence.*;
import java.util.Set;

@Getter @Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "Car_Manufacturer")
public class CarManufacturer {
    @Id
    @GeneratedValue(generator = "manufacturerGen")
    @SequenceGenerator(name = "manufacturerGen", sequenceName = "manufacturerSeq", allocationSize = 1)
    @ReadOnlyProperty
    private Long id;

    private String name;

    @OneToMany(mappedBy = "manufacturer")
    @JsonManagedReference
    @ReadOnlyProperty
    @ToString.Exclude
    private Set<Car> cars;

    public void addCar(Car car) {
        cars.add(car);
    }
}
