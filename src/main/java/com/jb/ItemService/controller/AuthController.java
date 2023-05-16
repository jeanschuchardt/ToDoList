package com.jb.ItemService.controller;

import com.jb.ItemService.exception.ApiRequestException;
import com.jb.ItemService.record.AuthenticationRequestDTO;
import com.jb.ItemService.record.UserResponseDTO;
import com.jb.ItemService.repository.UserRepository;
import com.jb.ItemService.service.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/authenticate")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    
    @Operation(summary = "Authenticate user",  tags = {"public endpoint"} )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "JWT token returned",
                    content = @Content ),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content) })
    @PostMapping("/")
    public String authenticate(@RequestBody AuthenticationRequestDTO request) {
        try {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.user(), request.password()));
    
            var user = userRepository.findByEmailAndIsArchived(request.user(), false).orElseThrow();
            var jwtToken = jwtService.generateToken(user);
            return jwtToken;
        }catch (Exception e){
            throw new ApiRequestException("User or password invalid", HttpStatus.BAD_REQUEST);
        }
        
    }
    
}
