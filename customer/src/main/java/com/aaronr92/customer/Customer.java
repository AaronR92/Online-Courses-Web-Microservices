package com.aaronr92.customer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.ReadOnlyProperty;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.List;

@Getter @Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Customer {

    @Id
    @GeneratedValue(generator = "customerGen")
    @SequenceGenerator(name = "customerGen", sequenceName = "CustomerSeq", allocationSize = 1)
    @ReadOnlyProperty
    private Long id;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("second_name")
    private String secondName;

    @Pattern(regexp = ".+@.+\\..{2,4}$")
    private String email;

    private String phone;

    @ElementCollection
    @JsonIgnore
    private List<Long> cars;

    public void addCar(Long carId) {
        cars.add(carId);
    }

    public void removeCar(Long carId) {
        cars.remove(carId);
    }
}
