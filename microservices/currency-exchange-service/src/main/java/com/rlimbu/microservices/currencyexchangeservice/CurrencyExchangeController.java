package com.rlimbu.microservices.currencyexchangeservice;

import com.rlimbu.microservices.currencyexchangeservice.CurrencyExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {

    @Autowired
    private Environment environment;

    @Autowired
    private CurrencyExchangeRepository currencyExchangeRepository;;
    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange exchangeCurrency(@PathVariable String from, @PathVariable String to) {
        String port = environment.getProperty("local.server.port");

        //CurrencyExchange currencyExchange = new CurrencyExchange(1000L, "USD", "NPR", 130.5, port);
        CurrencyExchange currencyExchange = currencyExchangeRepository.findByFromAndTo(from, to);
        currencyExchange.setEnvironment("port:"+port);
        if(currencyExchange == null) {
            throw new RuntimeException("Unable to find data for "+from+" to "+to);
        }
        return currencyExchange;
    }

}
