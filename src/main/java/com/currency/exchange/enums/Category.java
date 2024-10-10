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
    FASHION(2),
    /**
     * Electronics.
     */
    ELECTRONICS(3),
    /**
     * Entertainment.
     */
    ENTERTAINMENT(4),
    /**
     * House and Garden.
     */
    HOME_AND_GARDEN(5),
    /**
     * Fashion.
     */
    FASHION_AND_ACCESSORIES(6);

    /**
     * CategoryId.
     */
    int id;

}
