package com.springboot.shoppingcart.payments.stripe;

import com.springboot.shoppingcart.orders.entity.Order;

import java.util.Optional;

public interface PaymentGateway {
    CheckoutSession createCheckoutSession(Order order);
    Optional<PaymentResult> parseWebhookRequest(WebhookRequest request);
}
