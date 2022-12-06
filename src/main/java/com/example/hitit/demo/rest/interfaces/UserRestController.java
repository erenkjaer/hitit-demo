package com.example.hitit.demo.rest.interfaces;

import com.example.hitit.demo.dtos.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
public interface UserRestController {
    @GetMapping
    ResponseEntity<List<UserDto>> getUser();
    @GetMapping
    ResponseEntity<UserDto> getUserById (@PathVariable Long id);
    @PostMapping
    ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto);

    @PutMapping
    ResponseEntity<UserDto> updateUser(@PathVariable String email, @RequestBody UserDto userDto);

    @DeleteMapping
    ResponseEntity<Boolean> deleteUser(@PathVariable String email);
}
