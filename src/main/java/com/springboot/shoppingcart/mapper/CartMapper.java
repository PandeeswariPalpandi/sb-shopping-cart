package com.springboot.shoppingcart.mapper;

import com.springboot.shoppingcart.dto.CartDto;
import com.springboot.shoppingcart.dto.CartItemDto;
import com.springboot.shoppingcart.entities.Cart;
import com.springboot.shoppingcart.entities.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartMapper {

    @Mapping(target = "cartItems", source = "cartItems")
    @Mapping(target = "totalPrice", expression = "java(cart.getTotalPrice())")
    CartDto toDto(Cart cart);

    @Mapping(target = "totalPrice", expression = "java(cartItem.getTotalPrice())")
    CartItemDto toDto(CartItem cartItem);
}
