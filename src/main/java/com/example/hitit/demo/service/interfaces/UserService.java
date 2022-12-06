package com.example.hitit.demo.service.interfaces;

import com.example.hitit.demo.dtos.UserDto;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto user);
    List<UserDto> getUsers();
    UserDto getUser(Long id);
    UserDto updateUser(Long id, UserDto user);
    Boolean deleteUser(Long id);
    Long getUserIdByEmail(String email);
}


