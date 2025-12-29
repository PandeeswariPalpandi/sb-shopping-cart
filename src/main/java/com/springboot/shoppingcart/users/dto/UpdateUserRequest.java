package com.springboot.shoppingcart.users.dto;

import lombok.Data;

@Data
public class UpdateUserRequest {
    public String name;
    public String email;
}
