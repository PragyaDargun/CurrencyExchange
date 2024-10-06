package com.currency.exchange.controller;

import com.currency.exchange.dto.request.ExchangeRateRequest;
import com.currency.exchange.dto.response.ExchangeRateResponse;
import com.currency.exchange.service.ExchangeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * CurrencyExchangeController.
 */
@RestController
public class CurrencyExchangeController {

    /**
     * ExchangeRateService.
     */
    @Autowired private ExchangeRateService exchangeRateService;

    /**
     * This method accepts items and base, target currency
     * and returns the total bill amount in target currency.
     *
     * @param exchangeRateRequest - Request
     * @return ExchangeRateResponse Response
     */
    @PostMapping(value = "/calculate",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ExchangeRateResponse calculate(
            @RequestBody final ExchangeRateRequest exchangeRateRequest) {
        return exchangeRateService.calculateExchangeRate(exchangeRateRequest);
    }

}
