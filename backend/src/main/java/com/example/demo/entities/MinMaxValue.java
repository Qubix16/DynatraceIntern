package com.example.demo.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MinMaxValue {

    public final double min;

    public final double max;

    public MinMaxValue(double min, double max) {
        this.min = min;
        this.max = max;
    }
}
