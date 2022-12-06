package com.example.hitit.demo.rest.implementation;

import com.example.hitit.demo.dtos.UserDto;
import com.example.hitit.demo.rest.interfaces.UserRestController;
import com.example.hitit.demo.service.interfaces.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path ="api/v1/users")
public class UserRestControllerImpl implements UserRestController {

    private final UserService userService;

    public UserRestControllerImpl(UserService userService) {
        this.userService = userService;
    }


    @Override
    @GetMapping
    public ResponseEntity<List<UserDto>> getUser(){
        List<UserDto> users = userService.getUsers();
        return ResponseEntity.ok(users);
    }

    @Override
    @GetMapping(path = "{userId}")
    public ResponseEntity<UserDto> getUserById (@PathVariable("userId") Long id){
        UserDto foundUser = userService.getUser(id);
        return ResponseEntity.ok(foundUser);
    }
    @Override
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){

        UserDto createdUser = userService.createUser(userDto);
        return ResponseEntity.ok(createdUser);
    }

    @Override
    @PutMapping(path = "/update/{email}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("email") String email,@RequestBody UserDto userDto) {
        Long id = userService.getUserIdByEmail(email);
        UserDto updatedUser = userService.updateUser(id,userDto);
        return ResponseEntity.ok(updatedUser);
    }

    @Override
    @DeleteMapping(path = "/remove/{email}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable("email") String email) {
        Long id = userService.getUserIdByEmail(email);
        Boolean result = userService.deleteUser(id);
        return ResponseEntity.status(200).body(result);
    }

}
