package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RateA extends Rate{

    @JsonProperty("mid")
    private double mid;
}
