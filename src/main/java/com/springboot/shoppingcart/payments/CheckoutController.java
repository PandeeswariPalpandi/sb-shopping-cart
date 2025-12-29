package com.springboot.shoppingcart.payments;

import com.springboot.shoppingcart.carts.exception.CartEmptyException;
import com.springboot.shoppingcart.carts.exception.CartNotFoundException;
import com.springboot.shoppingcart.common.ErrorDto;
import com.springboot.shoppingcart.orders.repository.OrderRepository;
import com.springboot.shoppingcart.payments.dto.CheckoutRequest;
import com.springboot.shoppingcart.payments.dto.CheckoutResponse;
import com.springboot.shoppingcart.payments.exception.PaymentException;
import com.springboot.shoppingcart.payments.service.CheckoutService;
import com.springboot.shoppingcart.payments.stripe.WebhookRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/checkout")
public class CheckoutController {
    private final CheckoutService checkoutService;
    private final OrderRepository orderRepository;

    @PostMapping
    public CheckoutResponse checkout(@Valid @RequestBody CheckoutRequest request) {
        return checkoutService.checkout(request);
    }

    @PostMapping("/webhook")
    public void handleWebhook(
        @RequestHeader Map<String, String> headers,
        @RequestBody String payload
    ) {
        checkoutService.handleWebhookEvent(new WebhookRequest(headers, payload));
    }

    @ExceptionHandler(PaymentException.class)
    public ResponseEntity<?> handlePaymentException() {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorDto("Error creating a checkout session"));
    }


    @ExceptionHandler({CartNotFoundException.class, CartEmptyException.class})
    public ResponseEntity<ErrorDto> handleException(Exception ex) {
        return ResponseEntity.badRequest().body(new ErrorDto(ex.getMessage()));
    }
}
