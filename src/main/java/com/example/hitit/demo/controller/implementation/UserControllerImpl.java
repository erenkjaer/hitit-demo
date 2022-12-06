package com.example.hitit.demo.controller.implementation;

import com.example.hitit.demo.controller.interfaces.UserController;
import com.example.hitit.demo.dtos.UserDto;
import com.example.hitit.demo.service.implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/users")
public class UserControllerImpl implements UserController {

    private final UserServiceImpl userService;
    private static final String REDIRECTTOUSER = "redirect:/users/showUsers";
    @Autowired
    public UserControllerImpl(UserServiceImpl userService) {
        this.userService = userService;
    }


    @Override
    @GetMapping("/showUsers")
    public String showUsers(Model model){
        model.addAttribute("users",userService.getUsers());
        return "list-user";
    }

    @Override
    @PostMapping("/addUser")
    public String addUser(UserDto user){

        userService.createUser(user);
        return REDIRECTTOUSER;
    }

    @Override
    @GetMapping("/updateUser")
    public String updateUser(UserDto user){
        Long id = userService.getUserIdByEmail(user.getEmail());
        userService.updateUser(id,user);
        return REDIRECTTOUSER;
    }

    @Override
    @DeleteMapping("/remove/{email}")
    public String deleteUser(@PathVariable("email") String email){
        Long id = userService.getUserIdByEmail(email);
        userService.deleteUser(id);
        return REDIRECTTOUSER;
    }


}
