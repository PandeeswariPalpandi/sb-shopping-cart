package com.springboot.shoppingcart.carts.mapper;

import com.springboot.shoppingcart.carts.dto.CartDto;
import com.springboot.shoppingcart.carts.dto.CartItemDto;
import com.springboot.shoppingcart.carts.entity.Cart;
import com.springboot.shoppingcart.carts.entity.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartMapper {
    @Mapping(target = "items", source = "items")
    @Mapping(target = "totalPrice", expression = "java(cart.getTotalPrice())")
    CartDto toDto(Cart cart);

    @Mapping(target = "totalPrice", expression = "java(cartItem.getTotalPrice())")
    CartItemDto toDto(CartItem cartItem);
}
