package com.springboot.shoppingcart.payments.stripe;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CheckoutSession {
    private String checkoutUrl;
}
