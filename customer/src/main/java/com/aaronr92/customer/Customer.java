package com.aaronr92.customer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.ReadOnlyProperty;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
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

    private String firstName;

    private String secondName;

    private String username;

    private String email;

    @ElementCollection
    @JsonIgnore
    private List<Long> courses;
}
