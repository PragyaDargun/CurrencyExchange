package com.currency.exchange.dto.request;

import com.currency.exchange.enums.CustomerType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * User Class.
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    /**
     * User ID
     */
    private String userId;
    /**
     * CustomerType.
     */
    private CustomerType customerType;
    /**
     * Customer joiningDate.
     */
    private LocalDate joiningDate;

}
