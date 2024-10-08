package com.currency.exchange.discount;

/**
 * Discount Parent Class.
 */
public interface PercentageDiscount {

    /**
     * Abstract method to calculate the discount
     * @param originalAmount : Double
     * @return Double : discounted amount
     */
    Double applyDiscount(Double originalAmount);
}
