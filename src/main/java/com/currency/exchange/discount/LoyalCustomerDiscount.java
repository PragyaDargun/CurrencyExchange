package com.currency.exchange.discount;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Loyal customer discount:
 *   5% for tenure of 24 months or more.
 */
@Service(LoyalCustomerDiscount.BEAN_ID)
public class LoyalCustomerDiscount implements Discount {

    /**
     * Bean ID.
     */
    public static final String BEAN_ID = "LoyalCustomerDiscount";

    /**
     * DIVIDEND.
     */
    private static final int DIVIDEND = 100;

    /**
     * Tenure In Months.
     */
    @Value("${currency.exchange.customer.tenure.minimum.months}")
    private int tenureInMonths;

    /**
     * Tenure Discount.
     */
    @Value("${currency.exchange.customer.tenure.discount}")
    private int tenureDiscount;

    /**
     * Abstract method to calculate the discount
     *
     * @param originalAmount : Double
     * @return Double : discounted amount
     */
    @Override
    public Double applyDiscount(Double originalAmount) {
        if (originalAmount >= tenureInMonths) {
            double discount = originalAmount * tenureDiscount / DIVIDEND;
            return (originalAmount - discount);
        }
        return originalAmount;
    }
}
