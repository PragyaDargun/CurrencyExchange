package com.currency.exchange.dto.request;

import com.currency.exchange.enums.CustomerType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Request object.
 */
@Getter
@Setter
@NoArgsConstructor
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
     * Constructor for defensive copying.
     * @param fromCurrency baseCurrency
     * @param toCurrency targetCurrency
     * @param customer customerType
     * @param customerTenure customerTenureInMonths
     * @param itemsList List of items
     */
    public ExchangeRateRequest(final String fromCurrency,
            final String toCurrency,
            final CustomerType customer,
            final Integer customerTenure,
            final List<Items> itemsList) {
        this.baseCurrency = fromCurrency;
        this.targetCurrency = toCurrency;
        this.customerType = customer;
        this.customerTenureInMonths = customerTenure;
        this.items = new ArrayList<>(itemsList);
    }

    /**
     * Method to get Items list.
     *
     * @return List of items.
     */
    public List<Items> getItems() {
        return Collections.unmodifiableList(items);
    }

    /**
     * Setter Method.
     * @param itemList - List of items.
     */
    public void setItems(final List<Items> itemList) {
        items = Collections.unmodifiableList(itemList);
    }

}
