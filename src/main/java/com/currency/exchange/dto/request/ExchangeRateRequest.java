package com.currency.exchange.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
     * User.
     */
    @NotNull
    private User user;

    /**
     * Items purchased.
     */
    @Valid
    private List<@NotNull Items> items;

    /**
     * Constructor for defensive copying.
     * @param fromCurrency baseCurrency
     * @param toCurrency targetCurrency
     * @param userObj User Obj
     * @param itemsList List of items
     */
    public ExchangeRateRequest(final String fromCurrency,
            final String toCurrency,
            final User userObj,
            final List<Items> itemsList) {
        this.baseCurrency = fromCurrency;
        this.targetCurrency = toCurrency;
        this.user = userObj;
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
