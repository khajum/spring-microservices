package com.rlimbu.microservices.limitservices.controller;

import com.rlimbu.microservices.limitservices.bean.Limit;
import com.rlimbu.microservices.limitservices.configuration.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsController {

    @Autowired
    private Configuration configuration;
    @GetMapping("/limits")
    public Limit getLimits() {
        //return new Limit(1, 1000);
        return new Limit(configuration.getMinimum(), configuration.getMaximum());
    }
}
