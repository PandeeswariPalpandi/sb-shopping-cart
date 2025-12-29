package com.springboot.shoppingcart.users.mapper;

import com.springboot.shoppingcart.users.dto.RegisterUserRequest;
import com.springboot.shoppingcart.users.dto.UpdateUserRequest;
import com.springboot.shoppingcart.users.dto.UserDto;
import com.springboot.shoppingcart.users.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
    User toEntity(RegisterUserRequest request);
    void update(UpdateUserRequest request, @MappingTarget User user);
}
