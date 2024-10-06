package com.currency.exchange.dto.request;

import com.currency.exchange.enums.CustomerType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Request object.
 */
@Getter
public class ExchangeRateRequest {

    /**
     * Base currency.
     */
    @Valid
    @NotBlank(message = "baseCurrency is required")
    private String baseCurrency;

    /**
     * Target Currency.
     */
    @Valid
    @NotBlank(message = "targetCurrency is required")
    private String targetCurrency;

    /**
     * Customer Type.
     */
    private CustomerType customerType = CustomerType.NONE;

    /**
     * Tenure of customer.
     */
    @Positive
    private Integer customerTenureInMonths;

    /**
     * Items purchased.
     */
    @Valid
    private List<@NotNull Items> items;

    /**
     * Method to get Items list.
     * @return List of items.
     */
    public List<Items> getItems() {
        return new ArrayList<>(items);
    }

}
