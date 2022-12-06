package com.example.hitit.demo.controller.interfaces;

import com.example.hitit.demo.dtos.UserDto;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

public interface UserController {
    String showUsers(Model model);
    String addUser(UserDto user);
    String updateUser(UserDto user);
    String deleteUser(@PathVariable("email") String email);
}
