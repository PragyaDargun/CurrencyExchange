package com.currency.exchange.service;

import com.currency.exchange.dto.request.ExchangeRateRequest;
import com.currency.exchange.dto.response.ExchangeRateResponse;

/**
 * ExchangeRateService Interface.
 * It contains abstract method to
 * calculate the total bill amount in target currency.
 */
public interface ExchangeRateService {

    /**
     * abstract method.
     * @param exchangeRateRequest - Items, base and target currency
     * @return ExchangeRateResponse - total bill amount
     */
     ExchangeRateResponse calculateExchangeRate(
             ExchangeRateRequest exchangeRateRequest);

}
