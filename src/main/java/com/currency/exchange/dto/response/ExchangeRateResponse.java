package com.currency.exchange.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

/**
 * ExchangeRateResponse Response..
 */
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class ExchangeRateResponse {

    /**
     * Result : Success or failure.
     */
    private String result;
    /**
     * Base currency.
     */
    @JsonProperty("base_code")
    private String baseCurrency;
    /**
     * Target Currency.
     */
    @JsonProperty("target_code")
    private String targetCurrency;
    /**
     * Conversion Rate.
     */
    @JsonProperty("conversion_rate")
    private Double conversionRate;
    /**
     * Total Bill Amount after conversion.
     */
    @JsonProperty("conversion_result")
    private Double totalBill;
    /**
     * errorType : Reason of error.
     */
    @JsonProperty("error-type")
    private String errorType;



}
