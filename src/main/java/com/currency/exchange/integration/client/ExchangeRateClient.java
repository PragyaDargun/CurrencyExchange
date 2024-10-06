package com.currency.exchange.integration.client;

import com.currency.exchange.dto.response.ExchangeRateResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Exchange rate client.
 */
@FeignClient(name = "exchangeRateClient")
public interface ExchangeRateClient {

    /**
     * To convert the total bill amount in desired currency.
     *
     * @param baseCurrency - Base Currency.
     * @param target       - target currency.
     * @return ExchangeRateResponse - Bill amount
     */
    @GetMapping("/pair/{base}/{target}/{amount}")
    ExchangeRateResponse calculateExchangeRate(
                 @PathVariable(name = "base") String baseCurrency,
                 @PathVariable(name = "target") String target);

}
