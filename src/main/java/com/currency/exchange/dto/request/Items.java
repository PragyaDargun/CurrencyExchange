package com.currency.exchange.dto.request;

import com.currency.exchange.enums.Category;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

/**
 * Item Object.
 */
@Getter
@Setter
public class Items {

    /**
     * Category of Item.
     */
    @NotBlank(message = "category is required")
    private Category category;
    /**
     * Item Name.
     */
    private String itemName;
    /**
     * Price of item.
     */
    @Positive
    private Double itemPrice;

    /**
     * Quantity of item.
     */
    @Positive
    @Min(1)
    private Integer quantity = 1;
}
