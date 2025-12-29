package com.springboot.shoppingcart.payments.stripe;

import com.springboot.shoppingcart.orders.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PaymentResult {
    private Long orderId;
    private PaymentStatus paymentStatus;
}
