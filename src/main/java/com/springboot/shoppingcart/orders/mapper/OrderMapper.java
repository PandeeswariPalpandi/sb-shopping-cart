package com.springboot.shoppingcart.orders.mapper;

import com.springboot.shoppingcart.orders.entity.Order;
import com.springboot.shoppingcart.orders.dto.OrderDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDto toDto(Order order);
}
