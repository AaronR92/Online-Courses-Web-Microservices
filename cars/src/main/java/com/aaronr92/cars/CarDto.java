package com.aaronr92.cars;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarDto {

    @NotBlank
    private String model;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotNull
    private int price;

    @NotNull
    private int power;

    @JsonProperty("release_year")
    @Pattern(regexp = "/^(19|20)[\\d]{2}$/")
    private int releaseYear;

    @NotNull
    @JsonProperty("is_electric")
    private boolean isElectric;

    @NotBlank
    private String manufacturer;
}