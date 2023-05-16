package com.jb.ItemService.controller;

import com.jb.ItemService.entity.User;
import com.jb.ItemService.record.SimpleUserResponseDTO;
import com.jb.ItemService.record.UserRequestDTO;
import com.jb.ItemService.record.UserResponseDTO;
import com.jb.ItemService.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    
    private final UserService userService;
    
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @Operation(summary = "Create a new user",  tags = {"public endpoint"} )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User created",
                    content = @Content ),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content) })
    @PostMapping("/api/v1/users")
    public UserResponseDTO createUser(@RequestBody UserRequestDTO userRequest) {
        return userService.createUser(userRequest);
        
    }
    
    @Operation(summary = "Get an user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User returned",
                    content = @Content ),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content)})
    @GetMapping("/api/v1/users/{id}")
    public SimpleUserResponseDTO getUser(@PathVariable int id) {
        User user = userService.getUser(id);
        SimpleUserResponseDTO simpleUserResponseDTO = userService.mapResponse(user);
        return simpleUserResponseDTO;
    }
    
    @Operation(summary = "Update an user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User returned",
                    content = @Content ),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content)})
    @PutMapping("/api/v1/users/{id}")
    public SimpleUserResponseDTO updateUser(@PathVariable int id, @RequestBody UserRequestDTO userRequest) {
        User user = userService.updateUser(id, userRequest);
        SimpleUserResponseDTO simpleUserResponseDTO = userService.mapResponse(user);
        return simpleUserResponseDTO;
        
    }
    
}
