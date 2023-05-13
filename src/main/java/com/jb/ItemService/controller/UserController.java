package com.jb.ItemService.controller;

import com.jb.ItemService.entity.User;
import com.jb.ItemService.record.UserRequestDTO;
import com.jb.ItemService.record.UserResponseDTO;
import com.jb.ItemService.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    
    private final UserService userService;
    
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @GetMapping("/api/v1/users/{id}")
    public User getUser(@PathVariable int id) {
        return userService.getUser(id);
    }
    
    @PostMapping("/api/v1/users")
    public UserResponseDTO createUser(@RequestBody UserRequestDTO userRequest) {
        return userService.createUser(userRequest);
        
        
    }
    
    @PutMapping("/api/v1/users/{id}")
    public User updateUser(@PathVariable int id, @RequestBody UserRequestDTO userRequest) {
        return userService.updateUser(id, userRequest);
        
    }
    
}
