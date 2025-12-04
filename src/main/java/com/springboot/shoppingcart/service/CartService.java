package com.springboot.shoppingcart.service;

import com.springboot.shoppingcart.dto.CartDto;
import com.springboot.shoppingcart.dto.CartItemDto;
import com.springboot.shoppingcart.entities.Cart;
import com.springboot.shoppingcart.exception.CartEmptyException;
import com.springboot.shoppingcart.exception.CartNotFoundException;
import com.springboot.shoppingcart.exception.ProductNotFoundException;
import com.springboot.shoppingcart.mapper.CartMapper;
import com.springboot.shoppingcart.repositories.CartRepository;
import com.springboot.shoppingcart.repositories.ProductRepository;
import com.springboot.shoppingcart.utills.AddItemToCardRequest;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class CartService {

    private CartRepository cartRepository;
    private CartMapper cardMapper;
    private ProductRepository productRepository;

    public CartDto createCart() {
        var cart = new Cart();

        cartRepository.save(cart);

        return cardMapper.toDto(cart);
    }

    @Transactional
    public ResponseEntity<?> addItemToCart(UUID cartId, Long productId) {
        var cart = cartRepository.getCartWithItems(cartId).orElse(null);
        if (cart == null) {
            return new ResponseEntity<>(new CartNotFoundException().getMessage(), HttpStatus.NOT_FOUND);
        }

        var product = productRepository.findById(productId).orElse(null);
        if (product == null) {
            return new ResponseEntity<>(new ProductNotFoundException().getMessage(), HttpStatus.BAD_REQUEST);
        }

        var cartItem = cart.addItem(product);
        cartRepository.save(cart);

        return ResponseEntity.ok().body(cardMapper.toDto(cartItem));
    }

    public ResponseEntity<?> getCartItems(UUID cartId) {
        var cart = cartRepository.getCartWithItems(cartId).orElse(null);
        if (cart == null) {
            return new ResponseEntity<>(new CartNotFoundException().getMessage(), HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok().body(cardMapper.toDto(cart));
    }

    public ResponseEntity<?> updateCart(UUID cartId, Long productId, Integer quantity) {
        var cart = cartRepository.getCartWithItems(cartId).orElse(null);
        if (cart == null) {
            return new ResponseEntity<>(new CartNotFoundException().getMessage(), HttpStatus.NOT_FOUND);
        }

        var product = productRepository.findById(productId).orElse(null);
        if (product == null) {
            return new ResponseEntity<>(new ProductNotFoundException().getMessage(), HttpStatus.BAD_REQUEST);
        }

        var cartItem = cart.getItem(productId);
        if (cartItem == null) {
            return new ResponseEntity<>(new CartEmptyException().getMessage(), HttpStatus.NOT_FOUND);
        }

        cartItem.setQuantity(quantity);
        cartRepository.save(cart);
        return ResponseEntity.ok().body(cardMapper.toDto(cart));
    }

    public ResponseEntity<?> deleteItemInCart(UUID cartId, Long productId) {
        var cart = cartRepository.getCartWithItems(cartId).orElse(null);
        if (cart == null) {
            return new ResponseEntity<>(new CartNotFoundException().getMessage(), HttpStatus.NOT_FOUND);
        }

        var product = productRepository.findById(productId).orElse(null);
        if (product == null) {
            return new ResponseEntity<>(new ProductNotFoundException().getMessage(), HttpStatus.BAD_REQUEST);
        }

        cart.removeItem(productId);
        cartRepository.save(cart);

        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<?> clearCart(UUID cartId) {
        var cart = cartRepository.getCartWithItems(cartId).orElse(null);
        if (cart == null) {
            return new ResponseEntity<>(new CartNotFoundException().getMessage(), HttpStatus.NOT_FOUND);
        }

        cart.clear();
        cartRepository.save(cart);
        return ResponseEntity.noContent().build();
    }
}
