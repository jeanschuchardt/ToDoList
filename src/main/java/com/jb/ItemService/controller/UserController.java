package com.jb.ItemService.controller;

import com.jb.ItemService.entity.User;
import com.jb.ItemService.record.SimpleUserResponseDTO;
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
    
    @PostMapping("/api/v1/users")
    public UserResponseDTO createUser(@RequestBody UserRequestDTO userRequest) {
        return userService.createUser(userRequest);
        
    }
    @GetMapping("/api/v1/users/{id}")
    public SimpleUserResponseDTO getUser(@PathVariable int id) {
        User user = userService.getUser(id);
        SimpleUserResponseDTO simpleUserResponseDTO = userService.mapResponse(user);
        return simpleUserResponseDTO;
    }
    
    @PutMapping("/api/v1/users/{id}")
    public SimpleUserResponseDTO updateUser(@PathVariable int id, @RequestBody UserRequestDTO userRequest) {
        User user = userService.updateUser(id, userRequest);
        SimpleUserResponseDTO simpleUserResponseDTO = userService.mapResponse(user);
        return simpleUserResponseDTO;
        
    }
    
}
