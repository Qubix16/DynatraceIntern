package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public abstract class Rate {
    @JsonProperty("no")
    protected String id;

    @JsonProperty("effectiveDate")
    protected String effectiveDate;
}
