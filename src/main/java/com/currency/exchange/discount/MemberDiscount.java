package com.currency.exchange.discount;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Member discount of 10%.
 */
@Service(MemberDiscount.BEAN_ID)
public class MemberDiscount implements Discount {

    /**
     * Bean ID.
     */
    public static final String BEAN_ID = "MemberDiscount";

    /**
     * DIVIDEND.
     */
    private static final int DIVIDEND = 100;

    /**
     * Member discount.
     */
    @Value("${currency.exchange.member.discount}")
    private int memberDiscount;

    /**
     * Abstract method to calculate the discount
     *
     * @param originalAmount : Double
     * @return Double : discounted amount
     */
    @Override
    public Double applyDiscount(Double originalAmount) {
        double discount = originalAmount * memberDiscount/DIVIDEND;
        return (originalAmount - discount);
    }
}
