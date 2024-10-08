package com.currency.exchange.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Item Object.
 */
@Getter
@Setter
@Builder
public class Items {

    /**
     * Category of Item.
     */
    @NotBlank(message = "categoryId is required")
    private int categoryId;
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
    private Integer quantity;

}
