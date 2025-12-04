package com.springboot.shoppingcart.service;

import com.springboot.shoppingcart.exception.DuplicateUserException;
import com.springboot.shoppingcart.exception.UserNotFoundException;
import com.springboot.shoppingcart.utills.ChangePasswordRequest;
import com.springboot.shoppingcart.utills.RegisterUserRequest;
import com.springboot.shoppingcart.utills.UpdateUserRequest;
import com.springboot.shoppingcart.dto.UserDto;
import com.springboot.shoppingcart.mapper.UserMapper;
import com.springboot.shoppingcart.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.Set;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public Iterable<UserDto> getAllUsers(String sortBy) {
        if (!Set.of("name", "email").contains(sortBy))
            sortBy = "name";

        return userRepository.findAll(Sort.by(sortBy))
                .stream()
                .map(userMapper::toUserDto)
                .toList();
    }

    public UserDto getUser(Long userId) {
        var user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        return userMapper.toUserDto(user);
    }

    public UserDto registerUser(RegisterUserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateUserException();
        }

        var user = userMapper.toUser(request);
        user.setPassword(user.getPassword());
        userRepository.save(user);

        return userMapper.toUserDto(user);
    }

    public UserDto updateUser(Long userId, UpdateUserRequest request) {
        var user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        userMapper.update(request, user);
        userRepository.save(user);

        return userMapper.toUserDto(user);
    }

    public void deleteUser(Long userId) {
        var user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        userRepository.delete(user);
    }

    public void changePassword(Long userId, ChangePasswordRequest request) throws AccessDeniedException {
        var user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);

        if (!request.getOldPassword().equals(user.getPassword())) {
            throw new AccessDeniedException("Password does not match");
        }

        user.setPassword(request.getNewPassword());
        userRepository.save(user);
    }
}
