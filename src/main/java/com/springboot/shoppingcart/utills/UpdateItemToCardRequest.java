package com.springboot.shoppingcart.utills;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class UpdateItemToCardRequest {

    @Min(value = 1, message = "Minimum Quantity should be 1")
    @Max(value = 100, message = "Maximum Quantity should be 100")
    private Integer quantity;
}
