package com.currency.exchange.discount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Employee Discount class.
 */
@Service(EmployeeDiscount.BEAN_ID)
public class EmployeeDiscount implements Discount{

    /**
     * Bean ID.
     */
    public static final String BEAN_ID = "EmployeeDiscount";
    /**
     * DIVIDEND.
     */
    private static final int DIVIDEND = 100;

    /**
     * Employee discount.
     */
    @Value("${currency.exchange.employee.discount}")
    private int employeeDiscount;

//    @Autowired
//    private Environment environment;


    /**
     * Abstract method to calculate the discount for Employees.
     *
     * @param originalAmount : Double
     * @return Double : discounted amount
     */
    @Override
    public Double applyDiscount(Double originalAmount) {
        double discount = originalAmount * ((double) employeeDiscount/DIVIDEND);
        return (originalAmount - discount);
    }
}
