package com.rlimbu.microservices.limitservices.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Limit {
    int minimum;
    int maximum;

}
