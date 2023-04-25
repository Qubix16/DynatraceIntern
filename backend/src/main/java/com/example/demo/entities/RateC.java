package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RateC extends Rate{

    @JsonProperty("ask")
    private double ask;

    @JsonProperty("bid")
    private double bid;
}
