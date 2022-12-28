package com.rlimbu.microservices.currencyconversionservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyConversion {
    private Long id;
    private String from;
    private String to;
    private double exchangeRate;
    private BigDecimal quantity;
    private double totalExchangeAmount;
    private String environment;

}
