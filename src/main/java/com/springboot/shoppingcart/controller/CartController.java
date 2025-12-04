package com.springboot.shoppingcart.controller;

import com.springboot.shoppingcart.dto.CartDto;
import com.springboot.shoppingcart.service.CartService;
import com.springboot.shoppingcart.utills.AddItemToCardRequest;
import com.springboot.shoppingcart.utills.UpdateItemToCardRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/carts")
@Tag(name = "Carts")
public class CartController {

    private final CartService cartService;

    @PostMapping
    @Operation(summary = "Create a new cart")
    public ResponseEntity<?> createCart() {
        var CardDto = cartService.createCart();
        return ResponseEntity.created(null).body(CardDto);
    }

    @PostMapping("/{id}/items")
    @Operation(summary = "adds a Product to the cart")
    public ResponseEntity<?> addItemToCart(
            @Parameter(description = "CartID")
            @PathVariable UUID id,
            @Parameter(description = "ProductID")
            @RequestBody AddItemToCardRequest request) {
        return cartService.addItemToCart(id, request.getProductId());
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<?> getCartItems(@PathVariable UUID cartId) {
        return cartService.getCartItems(cartId);
    }

    @PutMapping("/{cartId}/items/{productId}")
    public ResponseEntity<?> updateCart(@PathVariable UUID cartId, @PathVariable Long productId,
                                        @Valid @RequestBody UpdateItemToCardRequest request) {
        return cartService.updateCart(cartId, productId, request.getQuantity());
    }

    @DeleteMapping("/{cartId}/items/{productId}")
    public ResponseEntity<?> deleteCart(@PathVariable UUID cartId, @PathVariable Long productId) {
        return cartService.deleteItemInCart(cartId, productId);
    }

    @DeleteMapping("/{cartId}/items")
    public ResponseEntity<?> clearCart(@PathVariable UUID cartId) {
        return cartService.clearCart(cartId);
    }
}
