package com.springboot.shoppingcart.exception;

public class CartEmptyException extends RuntimeException {
    public CartEmptyException() {
        super("Cat is empty");
    }
}
