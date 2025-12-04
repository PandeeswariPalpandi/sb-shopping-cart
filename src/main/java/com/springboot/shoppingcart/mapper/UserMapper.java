package com.springboot.shoppingcart.mapper;

import com.springboot.shoppingcart.utills.RegisterUserRequest;
import com.springboot.shoppingcart.utills.UpdateUserRequest;
import com.springboot.shoppingcart.dto.UserDto;
import com.springboot.shoppingcart.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toUserDto(User user);
    User toUser(RegisterUserRequest request);
    void update(UpdateUserRequest request, @MappingTarget User user);
}
