package com.currency.exchange.discount;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Value-based discount: 5% if the total is greater than 99.
 */
@Service(ValueBaseDiscount.BEAN_ID)
public class ValueBaseDiscount implements PercentageDiscount {

    /**
     * Bean ID.
     */
    public static final String BEAN_ID = "ValueBaseDiscount";

    /**
     * DIVIDEND.
     */
    private static final int DIVIDEND = 100;

    /**
     * Discount on every bill after a specific amount.
     */
    @Value("${currency.exchange.discount.eligible.minimum.bill.amount}")
    private int minimumBillAmount;
    /**
     * bill discount.
     */
    @Value("${currency.exchange.discount.eligible.minimum.bill}")
    private int valueBasedDiscount;

    /**
     * Abstract method to calculate the discount
     *
     * @param originalAmount : Double
     * @return Double : discounted amount
     */
    @Override
    public Double applyDiscount(Double originalAmount) {
        final double count = Math.floor(
                originalAmount / minimumBillAmount);
        if (count >= 1) {
            double discount = valueBasedDiscount * count;
            return (originalAmount - discount);
        }
        return originalAmount;
    }
}
