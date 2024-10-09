package com.currency.exchange.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Item Categories.
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum Category {
    /**
     * Grocery.
     */
    GROCERIES(1),
    /**
     * Fashion.
     */
    FASHION,
    /**
     * Electronics.
     */
    ELECTRONICS,
    /**
     * Entertainment.
     */
    ENTERTAINMENT,
    /**
     * House and Garden.
     */
    HOME_AND_GARDEN,
    /**
     * Fashion.
     */
    FASHION_AND_ACCESSORIES;
    int id;

}
