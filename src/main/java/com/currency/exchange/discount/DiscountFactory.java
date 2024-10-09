package com.currency.exchange.discount;

import com.currency.exchange.enums.CustomerType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * DiscountFactory.
 */
@Service
@RequiredArgsConstructor
public class DiscountFactory {

    /**
     * Contain Instance of Discount class with BeanId.
     */
    private final Map<String, PercentageDiscount> discount;

    /**
     * Discount Factory method.
     *
     * @param customerType - Customer Type
     * @param customerTenureInMonths - tenure in months
     * @param totalAmount - amount
     * @return Discount Obj.
     */
    public PercentageDiscount getDiscount(CustomerType customerType,
            int customerTenureInMonths, Double totalAmount) {
        if (CustomerType.EMPLOYEE.equals(customerType)) {
            return discount.get("EmployeeDiscount");
        } else if (CustomerType.MEMBER.equals(customerType)) {
            return discount.get("MemberDiscount");
        } else if (customerTenureInMonths >= 24) {
            return discount.get("LoyalCustomerDiscount");
        }
        return null;  // No discount
    }

}
